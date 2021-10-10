/*
    This file is part of jText.

    jText is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    jText is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with jText.  If not, see <https://www.gnu.org/licenses/>.
 */


package jtext;

import jtext.components.JTextMenuBar;
import static jtext.components.JTextMenuBar.USER_HOME;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.util.Optional;


public class MainWindow {
    public File file;

    public JFrame frame = new JFrame("jText");
    public JTextMenuBar menuBar = new JTextMenuBar(this);
    private JPanel panel;
    private JTextArea textArea;
    public String getText() {
        return textArea.getText();
    }

    public MainWindow(Optional<File> file) {

        this.file = file.orElse(new File(USER_HOME));
        if (this.file.isFile()) { // If this is a file and not the default directory, attempt to display the file's text.
            try {
                this.textArea.setText(new String(Files.readAllBytes(this.file.toPath())));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        frame.setContentPane(panel);
        frame.setJMenuBar(menuBar);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
