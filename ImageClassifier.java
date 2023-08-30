import java.awt.Color;

public class ImageClassifier {
    // Creates a feature vector (1D array) from the given picture.
    // See below for more details.
    public static double[] extractFeatures(Picture picture) {
        Color[] pixels = new Color[picture.height() * picture.width()];
        int pixel_id_x = 0;

        for (int i = 0; i < picture.height(); i++) {
            for (int j = 0; j < picture.width(); j++) {
                pixels[pixel_id_x] = picture.get(j, i);
                pixel_id_x++;
            }
        }
        double[] color_ints = new double[pixels.length];
        for (int i = 0; i < color_ints.length; i++) {
            color_ints[i] = pixels[i].getRed();
        }
        return color_ints;
    }

    private static void train(In training, MultiPerceptron mp) {
        while (!training.isEmpty()) {
            String file_path = training.readString();
            int label = training.readInt();
            Picture train_pic = new Picture(file_path);
            mp.trainMulti(extractFeatures(train_pic), label);
        }

    }

    private static void test(In testing, MultiPerceptron mp, int m, int width, int height) {
        int m_classes = testing.readInt();
        int w = testing.readInt();
        int h = testing.readInt();
        if (m != m_classes) {
            System.out.println("classes dont match");
            return;
        }
        if (w != width || h != height) {
            System.out.println("dimensions dont match");
            return;
        }
        int test_num = 0;
        int errors = 0;
        while (!testing.isEmpty()) {
            test_num++;
            String file_path = testing.readString();
            int label = testing.readInt();
            Picture test_pic = new Picture(file_path);
            int predict = mp.predictMulti(extractFeatures(test_pic));
            if (predict != label) {
                errors++;
                System.out.println(file_path);
                System.out.println("label: " + label + "predicted: " + predict);
            }
        }
        double percent = (errors * 1.00) / test_num;
        System.out.println("test error rate = " + percent);
    }

    public static void main(String[] args) {
        In training = new In(args[0]);
        int m_classes = training.readInt();
        int width = training.readInt();
        int length = training.readInt();
        int n = width * length;
        MultiPerceptron mp = new MultiPerceptron(m_classes, n);
        train(training, mp);
        In testing = new In(args[1]);
        test(testing, mp, m_classes, width, length);


    }
}
