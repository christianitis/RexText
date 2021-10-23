/*
 *     Copyright (C) 2021 Christian Hollinger
 *
 *     This file is part of RexText.
 *
 *     RexText is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     RexText is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with RexText. If not, see <https://www.gnu.org/licenses/>.
 */

package rextext;

import javax.swing.*;

public class Resources {

    public static final String USERHOME = System.getProperty("user.home");

    public static final record Icons() {
        public static final String NEW24 = "/jlfgr-1_0/toolbarButtonGraphics/general/New24.gif";
        public static final String SAVE24 = "/jlfgr-1_0/toolbarButtonGraphics/general/Save24.gif";
        public static final String SAVEAS24 = "/jlfgr-1_0/toolbarButtonGraphics/general/SaveAs24.gif";
        public static final String OPEN24 = "/jlfgr-1_0/toolbarButtonGraphics/general/Open24.gif";
    }

    public static ImageIcon GET_IMAGE_ICON(String name) { 
        var url = Resources.class.getResource(name);
        return new ImageIcon(url);
    }


}
