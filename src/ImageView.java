import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageView extends JFrame {

    private BufferedImage image;

    public ImageView(BufferedImage image) {
        this.image = image;
        setBounds(0, 0, image.getWidth(), image.getHeight());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void paint(Graphics graphics) {
        if(image != null) {
            graphics.drawImage(image, 0, 0, this);
        }
    }
}