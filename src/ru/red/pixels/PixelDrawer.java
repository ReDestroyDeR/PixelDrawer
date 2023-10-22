package ru.red.pixels;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class PixelDrawer extends Component {

    private static final int SCALE = 10;

    private final transient Path filePath;

    public PixelDrawer(Path filePath) throws IOException {
        this.filePath = filePath;
        var length = (int) Math.sqrt(Files.size(filePath)) * SCALE;
        this.setPreferredSize(new Dimension(length, length));
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        try (var io = new FileInputStream(filePath.toFile())) {

            var length = (int) Math.sqrt(Files.size(filePath)) * SCALE;
            var x = 0;
            var y = 0;

            for (byte[] buf = io.readNBytes(3);
                 buf.length > 0;
                 buf = io.readNBytes(3)) {

                g.setColor(createColor(buf));
                g.fillRect(x, y, SCALE, SCALE);

                // lazy to do shit
                if (x == length) {
                    y += SCALE;
                    x = 0;
                } else {
                    x += SCALE;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Color createColor(byte[] buf) {
        var rgb = Arrays.copyOf(buf, 3);

        return new Color(rgb[0] + 128, rgb[1] + 128, rgb[2] + 128);
    }
}
