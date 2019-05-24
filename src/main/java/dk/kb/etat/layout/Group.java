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

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import java.awt.*;

/**
 *
 */
public class Group {
    private final Group parent;
    private final String designation;
    private final int groupID;
    private final Color color;
    private final int[] members;

    Group(Group parent, String designation, int groupID, int[] members, Color color) {
        this.parent = parent;
        this.designation = designation;
        this.groupID = groupID;
        this.members = members;
        this.color = color;
    }

    public Group getParent() {
        return parent;
    }

    public String getDesignation() {
        return designation;
    }

    public int getGroupID() {
        return groupID;
    }

    public Color getColor() {
        return color;
    }

    public int[] getMembers() {
        return members;
    }
}
