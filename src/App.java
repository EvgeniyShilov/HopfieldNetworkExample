import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        HopfieldNetwork network = new HopfieldNetwork();
        BinaryImage l = new Image("l.png").binaryImage();
        BinaryImage v = new Image("v.png").binaryImage();
        BinaryImage t = new Image("t.png").binaryImage();
        network.addImage(l);
        network.addImage(v);
        network.addImage(t);
        network.calculateWeightValues();
        network.recognize(l.applyNoise(0.1)).image().save("l1.png");
        network.recognize(v.applyNoise(0.2)).image().save("v1.png");
        network.recognize(t.applyNoise(0.3)).image().save("t1.png");
        network.recognize(l.applyNoise(0.35)).image().save("l2.png");
        network.recognize(v.applyNoise(0.4)).image().save("v2.png");
        network.recognize(t.applyNoise(0.45)).image().save("t2.png");
        network.recognize(l.applyNoise(0.5)).image().save("l3.png");
        network.recognize(v.applyNoise(0.6)).image().save("v3.png");
        network.recognize(t.applyNoise(0.7)).image().save("t3.png");
        network.recognize(l.applyNoise(0.8)).image().save("l4.png");
        network.recognize(v.applyNoise(0.9)).image().save("v4.png");
        network.recognize(t.applyNoise(1)).image().save("t4.png");
    }
}
