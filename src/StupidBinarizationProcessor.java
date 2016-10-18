public class StupidBinarizationProcessor implements PixelProcessor {

    private static StupidBinarizationProcessor instance;

    private StupidBinarizationProcessor() {}

    public synchronized static StupidBinarizationProcessor getInstance() {
        if (instance == null) {
            instance = new StupidBinarizationProcessor();
        }
        return instance;
    }

    @Override
    public int process(int value) {
        int R = (value & 0x00FF0000) >> 16;
        int G = (value & 0x0000FF00) >> 8;
        int B = (value & 0x000000FF);
        R = R >= 128 ? 255 : 0;
        G = G >= 128 ? 255 : 0;
        B = B >= 128 ? 255 : 0;
        return R * 256 * 256 + G * 256 + B;
    }
}

