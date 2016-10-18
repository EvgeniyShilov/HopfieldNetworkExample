public class HalftoneProcessor implements PixelProcessor {

    private static HalftoneProcessor instance;

    private HalftoneProcessor() {}

    public synchronized static HalftoneProcessor getInstance() {
        if (instance == null) {
            instance = new HalftoneProcessor();
        }
        return instance;
    }

    @Override
    public int process(int value) {
        int Y = getBrightness(value);
        return Y * 256 * 256 + Y * 256 + Y;
    }

    private static int getBrightness(int value) {
        int R = (value & 0x00FF0000) >> 16;
        int G = (value & 0x0000FF00) >> 8;
        int B = (value & 0x000000FF);
        return (int)(0.3 * R + 0.59 * G + 0.11 * B);
    }
}