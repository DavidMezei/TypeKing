package Model;

import java.awt.*;
import java.util.Vector;

public class PercentageColors {
    private static Vector<Color> percentageColors;
    public static PercentageColors instance = new PercentageColors();

    private PercentageColors() {
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

    public Color getColorOfPercentage(float percentage) {
        if (percentage == 100) return percentageColors.get(9);
        if (percentage >= 90) return percentageColors.get((int) (percentage % 10));
        else return Color.red;
    }

    public static PercentageColors getInstance() {
        return instance;
    }
}
