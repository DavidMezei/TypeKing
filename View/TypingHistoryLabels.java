package View;

import Model.PercentageColors;
import Model.TypingHistory;

import javax.swing.*;
import java.awt.*;

public class TypingHistoryLabels {
    TypingHistory typingHistory;

    public TypingHistoryLabels(TypingHistory typingHistory) {
        this.typingHistory = typingHistory;
    }

    public JPanel getAllAttributesInOnePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.add(getDateLabel());
        int rigidAreaSize = 100;
        panel.add(Box.createRigidArea(new Dimension(rigidAreaSize - 60, 0)));
        panel.add(getTimeLabel());
        panel.add(Box.createRigidArea(new Dimension(rigidAreaSize, 0)));
        panel.add(getAccuracyLabel());
        int rigidAreaXSizeAfterAccuracy = getRigidAreaXSizeAfterAccuracyLabel(getAccuracyLabel());
        panel.add(Box.createRigidArea(new Dimension(rigidAreaXSizeAfterAccuracy, 0)));
        panel.add(getElapsedTimeLabel());
        panel.add(Box.createRigidArea(new Dimension(rigidAreaSize, 0)));
        panel.add(getWpmLabel());
        panel.setMaximumSize(new Dimension(1000, 50));
        return panel;
    }

    private int getRigidAreaXSizeAfterAccuracyLabel(JLabel label) {
        if (label.getText().equals("100")) return 110;
        if (!label.getText().contains(".")) return 130;
        return 100;
    }

    private void setUpLabel(JLabel label) {
        label.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        label.setForeground(Color.cyan);
    }

    public JLabel getDateLabel() {
        JLabel label = new JLabel(typingHistory.getDate());
        setUpLabel(label);
        return label;
    }

    public JLabel getTimeLabel() {
        JLabel label = new JLabel(typingHistory.getTime());
        setUpLabel(label);
        return label;
    }

    public JLabel getAccuracyLabel() {
        String accuracyString;
        if (typingHistory.getAccuracy() - typingHistory.getAccuracy().intValue() > 0)
            accuracyString = typingHistory.getAccuracy().toString();
        else accuracyString = "" + typingHistory.getAccuracy().intValue();
        JLabel label = new JLabel(accuracyString);
        setUpLabel(label);
        PercentageColors percentageColors = PercentageColors.getInstance();
        label.setForeground(percentageColors.getColorOfPercentage(typingHistory.getAccuracy()));
        return label;
    }

    public JLabel getWpmLabel() {
        JLabel label = new JLabel("" + typingHistory.getWpm());
        setUpLabel(label);
        return label;
    }

    public JLabel getElapsedTimeLabel() {
        JLabel label = new JLabel(typingHistory.getElapsedTime());
        setUpLabel(label);
        return label;
    }

}
