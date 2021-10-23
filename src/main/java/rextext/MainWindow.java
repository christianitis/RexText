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

import rextext.components.MenuBar;
import javax.swing.*;
import java.io.*;
import java.nio.file.Files;

import static rextext.Resources.USERHOME;

public class MainWindow {

    private File file;
    private JPanel panel;
    private JTextArea textPane;


    public File getFile() {
        return file;
    }

    public void reloadFile(File file) throws IOException {
        this.file = file;
        textPane.setText(getFileText(this.file));
        startingText = textPane.getText();
    }

    private String startingText = "";
    public boolean isModified() {
        return startingText.equals(getText());
    }

    public JFrame frame = new JFrame("RexText");
    public MenuBar menuBar = new MenuBar(this);

    public String getText() {
        return textPane.getText();
    }

    public static String getFileText(File file) throws IOException {
        return new String(Files.readAllBytes(file.toPath()));
    }

    /**
     * The file to open or create.
     * @param file the file to open or create
     */
    public MainWindow(File file) {
        this.file = file;
        loadFile();
    }

    /**
     * Create a new file with the default path.
     */
    public MainWindow() {
        // TODO: 10/12/21 Use config.properties file to allow users to set custom default directory.
        this.file = new File(USERHOME + "/New File.txt");
        setupWindow();
    }

    private void loadFile() {
        try {
            textPane.setText(getFileText(file));
            startingText = textPane.getText();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage(), "Unable to Open File", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setupWindow() {

        // textPane.getDocument().addUndoableEditListener(new UndoManager());
        frame.setContentPane(panel);
        frame.setJMenuBar(menuBar);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 500);
        frame.setVisible(true);

    }

}
