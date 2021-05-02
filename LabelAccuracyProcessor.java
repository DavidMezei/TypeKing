import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class LabelAccuracyProcessor {
    private int mistakeCounter = 0;
    private int textLength;
    private static JLabel labelAccuracyPercentage;
    private static Vector<Color> percentageColors;

    LabelAccuracyProcessor(JLabel labelAccuracyPercentage) {
        LabelAccuracyProcessor.labelAccuracyPercentage = labelAccuracyPercentage;
    }

    public void setTextLength(int textLength) {
        this.textLength = textLength;
    }

    public void updateLabelAccuracy() {
        mistakeCounter++;
        int remainedKeys = textLength - mistakeCounter;
        float percentage = remainedKeys / (float) textLength;
        percentage *= 100;
        setAccuracyPercentageWithColor(percentage);
    }

    public static Color getColorOfPercentage(float percentage){
        if (percentageColors==null) createPercentageColorsVector();
        if (percentage==100) return percentageColors.get(9);
        if (percentage>=90) return percentageColors.get((int) (percentage%10));
        else return Color.red;
    }

    public static void setAccuracyPercentageWithColor(float percentage){
        labelAccuracyPercentage.setForeground(getColorOfPercentage(percentage));
        labelAccuracyPercentage.setText(String.format("%.1f", percentage) + "%");
    }

    private static void createPercentageColorsVector() {
        int i = 10;
        int green = 0;
        int red = 255;
        percentageColors = new Vector<>();
        while (i > 0) {
            percentageColors.add(new Color(red, green, 0));
            if (green < 255) {
                green += 51;
            }
            if (green == 255) {
                red -= 51;
            }
            i--;
        }
    }

    public void setDefaultLabelAccuracy() {
        mistakeCounter = 0;
        labelAccuracyPercentage.setText("100%");
        labelAccuracyPercentage.setForeground(Color.cyan);
    }
}
