/*
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package dk.kb.etat.layout;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;

/**
 * A layout is a full representation of the collection, both coordinate→group and group→imageIDs.
 * A layout also tracks the groups defining it.
 */
public class Layout {
    private static final List<Color> groupColors = new ArrayList<>();
    static {
        groupColors.add(new Color(0, 0, 0)); // Base / no group

        // https://pymolwiki.org/index.php/Colorblindfriendly
        groupColors.add(new Color(230, 159, 0));
        groupColors.add(new Color(86, 180, 233));
        groupColors.add(new Color(0, 158, 115));
        groupColors.add(new Color(240, 228, 66));
        groupColors.add(new Color(213, 94, 0));
        groupColors.add(new Color(204, 121, 167));
        // TODO: Extend the palette and do a proper check to see if there are better palettes out there
    }
    private static final long VALUE_MASK = ~1L >>> 32;
    private static final long GROUP_MASK = ~1L >>> 48; // Last 16 bits = 65k groups max

    private final int width;
    private final int height;
    private final int imageCount;
    private final long[] grid; // imageID | group
    private final Map<Integer, Group> groups = new HashMap<>();

    private static Layout BASE = null; // All imaged 0-maxID

    public Layout(int width, int height, int imageCount) {
        this.width = width;
        this.height = height;
        this.imageCount = imageCount;
        this.grid = new long[width * height];
        checkBase();
    }

    private void checkBase() {
        synchronized (groupColors) {
            if (BASE == null) {
                int[] imageIDs = new int[imageCount];
                for (int i = 0 ; i < imageCount ; i++) {
                    imageIDs[i] = i+1;
                }
                Group baseGroup = new Group(null, "BASE_GROUP", 0, imageIDs, groupColors.get(0));
                BASE = new Layout(width, height, imageCount);
                BASE.groups.put(0, baseGroup);
            }
        }
    }

    public long getID(int x, int y) {
        return grid[x + y * width] >>> 32;
    }

    // null = no group
    public Group getGroup(int x, int y) {
        return groups.get((int) (getValue(x, y) & GROUP_MASK));
    }

    public Color getColor(int x, int y) {
        Group group = getGroup(x, y);
        return group == null ? groupColors.get(0) : group.getColor();
    }

    public long getValue(int x, int y) {
        return grid[x + y * width] & VALUE_MASK;
    }

    public void set(int x, int y, long id, long value) { // Consider checking if id or value exceeds 2^31-1
        grid[x + y * width] = (id << 32) | value;
    }

    public void set(int x, int y, long compound) {
        grid[x + y * width] = compound;
    }

    public long getCompound(int x, int y) {
        return grid[x + y * width];
    }

    public Layout copy() {
        Layout copy = new Layout(width, height, imageCount);
        System.arraycopy(grid, 0, copy.grid, 0, imageCount);
        return copy;
    }

    public Iterator<Integer> getGroupMembers(Group group) {
        final int groupID = group == null ? 0 : group.getGroupID();

        return new Iterator<Integer>() {
            int pos = 0;
            int nextID = -1;

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Not supported");
            }

            @Override
            public void forEachRemaining(Consumer<? super Integer> action) {
                throw new UnsupportedOperationException("Not supported");
            }

            @Override
            public boolean hasNext() {
                while (nextID == -1 && pos < imageCount) {
                    if ((grid[pos] & GROUP_MASK) == groupID) {
                        nextID = (int) (grid[pos] >>> 32);
                    }
                    pos++;
                }
                return nextID != -1;
            }

            @Override
            public Integer next() {
                if (nextID == -1) {
                    throw new IllegalStateException("Next must not be called when hasNext() == false");
                }
                int result = nextID;
                nextID = -1;
                return result;
            }
        };

    }
}
