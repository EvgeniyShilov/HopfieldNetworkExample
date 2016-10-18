import java.util.ArrayList;
import java.util.List;

public class HopfieldNetwork {

    private List<BinaryImage> images = new ArrayList<>();
    private byte[][] weights = new byte[100][100];

    public void addImage(BinaryImage image) {
        images.add(image);
    }

    public void calculateWeightValues() {
        if (images.isEmpty()) return;
        for (BinaryImage image : images) {
            for (int i = 0; i < 100; i++) {
                for (int j = i + 1; j < 100; j++) {
                    weights[i][j] += image.get(i) * image.get(j);
                    weights[j][i] = weights[i][j];
                }
            }
        }
    }

    public BinaryImage getNewNetworkState(BinaryImage image) {
        BinaryImage result = new BinaryImage(10, 10);
        for (int i = 0; i < 100; i++) {
            int sum = 0;
            for (int j = 0; j < 100; j++) {
                sum += weights[i][j] * image.get(j);
            }
            if (sum > 0) {
                result.set(i, (byte)1);
            } else {
                result.set(i, (byte)-1);
            }
        }
        return result;
    }

    public BinaryImage recognize(BinaryImage image) {
        BinaryImage oldState = image;
        while (true) {
            BinaryImage newState = getNewNetworkState(oldState);
            if(newState.equals(oldState))
                return newState;
            oldState = newState;
        }
    }
}
