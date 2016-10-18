import java.awt.image.BufferedImage;
import java.util.Arrays;

public class BinaryImage {

    byte[][] bytes;
    private int width;
    private int height;

    public BinaryImage(Image image) {
        BufferedImage bufferedImage = image.getBufferedImage();
        this.width = bufferedImage.getWidth();
        this.height = bufferedImage.getHeight();
        bytes = new byte[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                bytes[i][j] = getBitFromPixel(bufferedImage.getRGB(i, j));
            }
        }
    }

    public BinaryImage(int width, int height) {
        this.width = width;
        this.height = height;
        bytes = new byte[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                bytes[i][j] = 1;
            }
        }
    }

    public byte get(int w, int h) {
        if (w < 0 || h < 0 || w >= width || h >= height) return 0;
        return bytes[w][h];
    }

    public byte get(int i) {
        return get(i % width, i / width);
    }

    public void set(int i, byte value) {
        set(i % width, i / width, value);
    }

    public void set(int w, int h, byte value) {
        if (w < 0 || h < 0 || w >= width || h >= height) return;
        bytes[w][h] = value;
    }

    private byte getBitFromPixel(int value) {
        return StupidBinarizationProcessor.getInstance().process(
                HalftoneProcessor.getInstance().process(value)) == 0xFFFFFF ?
                (byte) 1 : (byte) -1;
    }

    public Image image() {
        BufferedImage resultBI = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (bytes[i][j] == 1)
                    resultBI.setRGB(i, j, 0xFFFFFFFF);
                else
                    resultBI.setRGB(i, j, 0xFF000000);
            }
        }
        return new Image(resultBI);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinaryImage image = (BinaryImage) o;
        return width == image.width
                && height == image.height
                && Arrays.deepEquals(bytes, image.bytes);
    }

    public BinaryImage applyNoise(double percent) {
        return ImageProcessor.applyNoise(this, percent);
    }
}
