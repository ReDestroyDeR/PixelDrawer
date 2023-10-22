package ru.red.pixels;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Path;

public class General {

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame();
        addScrollPane(frame);
        PixelDrawer pixelDrawer = new PixelDrawer(Path.of("/home/red/Downloads/Screenshot 2023-10-22 at 04-47-46 Java making a 'dot_pixel' In swing_awt.png"));
        frame.add(pixelDrawer);
        frame.setVisible(true);
        frame.pack();
    }


    private static void addScrollPane(JFrame frame) {
        var container = new JPanel();
        var scrPane = new JScrollPane(container);
        frame.getContentPane().add(scrPane);
    }

}
