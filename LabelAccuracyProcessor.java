import javax.swing.*;
import java.awt.*;

public class LabelAccuracyProcessor {
    private int mistakeCounter = 0;
    private int textLength;
    private JLabel labelAccuracyPercentage;
    private double lastPercentage = 100;
    private int red = 0;
    private int green = 255;

    LabelAccuracyProcessor(JLabel labelAccuracyPercentage) {
        this.labelAccuracyPercentage = labelAccuracyPercentage;
    }

    public void setTextLength(int textLength) {
        this.textLength = textLength;
    }

    public void updateLabelAccuracy() {
        mistakeCounter++;
        int remainedKeys = textLength - mistakeCounter;
        double percentage = remainedKeys / (double) textLength;
        percentage *= 100;
        updateLabelAccuracyColorRedGreen(percentage);
        labelAccuracyPercentage.setForeground(new Color(red, green, 0));
        labelAccuracyPercentage.setText(String.format("%.1f", percentage) + "%");
    }

    private void updateLabelAccuracyColorRedGreen(double percentage) {
        double multiplier = lastPercentage - percentage;
        int pace = (int) (45 * multiplier);
        if (red < 255) {
            red += pace;
            if (red > 255) red = 255;
        }
        if (red == 255) {
            green -= pace;
            if (green < 0) green = 0;
        }
        lastPercentage = percentage;
    }

    public void setDefaultLabelAccuracy() {
        red = 0;
        green = 255;
        mistakeCounter = 0;
        labelAccuracyPercentage.setText("100%");
        labelAccuracyPercentage.setForeground(Color.cyan);
        lastPercentage = 100;
    }
}
