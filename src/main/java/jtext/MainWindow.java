/*
 *     Copyright (C) 2021 Christian Hollinger
 *
 *     This file is part of jText.
 *
 *     jText is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     jText is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with jText. If not, see <https://www.gnu.org/licenses/>.
 */

package jtext;

import jtext.components.JTextMenuBar;


import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.util.Optional;

import static jtext.Constants.USERHOME;


public class MainWindow {
    private File file;
    public File getFile() {
        return file;
    }

    public void reloadFile(File file) throws IOException {
        this.file = file;
        textArea.setText(getFileText(this.file));
        startingText = textArea.getText();
    }

    private String startingText = "";
    public boolean isModified() {
        return startingText.equals(getText());
    }


    public JFrame frame = new JFrame("jText");
    public JTextMenuBar menuBar = new JTextMenuBar(this);
    private JPanel panel;
    private JTextArea textArea;
    public String getText() {
        return textArea.getText();
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
            textArea.setText(getFileText(file));
            startingText = textArea.getText();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage(), "Unable to Open File", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setupWindow() {
        frame.setContentPane(panel);
        frame.setJMenuBar(menuBar);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

}
