package dk.kb.etat.layout;/*
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

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import java.awt.Color;
import java.util.*;


/**
 * Layouts are maps of the full corpus. Limited to 2b images to
 *
 * Image-IDs starts with 1 (0 is reverved for no image)
 */
public class LayoutPool {
    private static Log log = LogFactory.getLog(LayoutPool.class);

    private final int width;
    private final int height;
    private final int imageCount;
    private final int maxLayouts;

    private final Map<String, Layout> layouts = new LinkedHashMap<String, Layout>() {
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, Layout> eldest) {
            return size() >= maxLayouts;
        }
    };

    public enum FILL_STYLE { left_right_rows, zig_zag_rows, center_out}

    // width & height measured in material
    public LayoutPool(int width, int height, int imageCount, int maxLayouts) {
        this.width = width;
        this.height = height;
        this.imageCount = imageCount;
        this.maxLayouts = maxLayouts;
    }

    public Group createGroup(Group parent, String designation, int[] memberIDs) {
        Group group = new Group(this, parent, designation, groups.size() + 1, memberIDs);
        groups.put(group.groupID, group);
        return group;
    }

    public Layout renderLayout(Layout parentLayout, String layoutID, Group parentGroup,  Collection<Group> groups)

    public static class Coordinate {
        public int x;
        public int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
