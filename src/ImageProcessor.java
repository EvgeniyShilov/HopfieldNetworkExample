import java.awt.image.BufferedImage;
import java.util.Random;

public class ImageProcessor {

    public static Image processWith(Image image, PixelProcessor processor) {
        BufferedImage bufferedImage = image.getBufferedImage();
        if (bufferedImage == null) return new Image();
        final int width = bufferedImage.getWidth();
        final int height = bufferedImage.getHeight();
        BufferedImage resultBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                resultBufferedImage.setRGB(i, j, processor.process(bufferedImage.getRGB(i, j)));
            }
        }
        return new Image(resultBufferedImage);
    }

    public static BinaryImage applyNoise(BinaryImage image, double percent) {
        Random random = new Random();
        BinaryImage result = new BinaryImage(image.getWidth(), image.getHeight());
        final int width = image.getWidth();
        final int height = image.getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                byte k = random.nextDouble() <= percent ? (byte)-1 : (byte)1;
                result.set(i, j, (byte)(image.get(i, j) * k));
            }
        }
        return result;
    }
}
