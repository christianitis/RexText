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

package jtext.components;

import jtext.MainWindow;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

/**
 * A {@link javax.swing.JMenuBar} containing all the {@link javax.swing.JMenu}s and {@link javax.swing.JMenuItem}s for
 * a {@link jtext.MainWindow}
 */
public class JTextMenuBar extends JMenuBar {
    public static final String USER_HOME = System.getProperty("user.home");

    private final MainWindow window;
    public JMenu file_menu = new FileMenu();
    public JMenu edit_menu = new EditMenu();

    public JTextMenuBar(MainWindow window) {
        this.window = window;
        add(file_menu);
    }

    private class FileMenu extends JMenu {
        JMenuItem file_new = new JMenuItem("New");
        JMenuItem file_save = new JMenuItem("Save");
        JMenuItem file_open = new JMenuItem("Open");

        FileMenu() {
            setText("File");

            file_new.addActionListener(actionEvent -> new MainWindow(Optional.empty()));

            file_save.addActionListener(actionEvent -> {
                var fileChooser = new JFileChooser(window.file);
                fileChooser.showSaveDialog(null);
                try {
                    var file = fileChooser.getSelectedFile();
                    var writer = new FileWriter(file);
                    writer.write(window.getText());
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Unable to Save File", JOptionPane.WARNING_MESSAGE);
                    e.printStackTrace();
                }
            });

            file_open.addActionListener(actionEvent -> {
                var fileChooser = new JFileChooser(USER_HOME);
                fileChooser.showOpenDialog(null);
                var file = fileChooser.getSelectedFile();
                assert file.exists();
                new MainWindow(Optional.of(file));
            });

            add(file_new);
            add(file_save);
            add(file_open);

        }
    }

    private class EditMenu extends JMenu {

    }
}
