import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {

    private BufferedImage image;

    public Image() {
        image = null;
    }

    public Image(BufferedImage image) {
        this.image = image;
    }

    public Image(String filename) throws IOException {
        image = ImageIO.read(new File(filename));
    }

    public BufferedImage getBufferedImage() {
        return image;
    }

    public Image print() {
        if (image != null) {
            new ImageView(image);
        }
        return this;
    }

    public Image save(String fileName) throws IOException {
        ImageIO.write(image, "png", new File(fileName));
        return this;
    }

    public Image apply(PixelProcessor processor) {
        return ImageProcessor.processWith(this, processor);
    }

    public BinaryImage binaryImage() {
        return new BinaryImage(this.apply(HalftoneProcessor.getInstance()).apply(StupidBinarizationProcessor.getInstance()));
    }
}
