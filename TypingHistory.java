import javax.swing.*;
import java.awt.*;

public class TypingHistory {
    private String date;
    private String time;
    private Float accuracy;
    private String elapsedTime;
    private int wpm;
    private int rigidAreaSize = 100;

    public JPanel getAllAttributesInOnePanel() {
        JPanel panel = new JPanel();
        //panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.setLayout(new GridBagLayout());
        panel.add(getDateLabel());
        panel.add(Box.createRigidArea(new Dimension(rigidAreaSize - 60, 0)));
        panel.add(getTimeLabel());
        panel.add(Box.createRigidArea(new Dimension(rigidAreaSize, 0)));
        panel.add(getAccuracyLabel());
        int rigidAreaXSizeAfterAccuracy = getRigidAreaXSizeAfterAccuracyLabel(getAccuracyLabel());
        panel.add(Box.createRigidArea(new Dimension(rigidAreaXSizeAfterAccuracy, 0)));
        panel.add(getElapsedTimeLabel());
        panel.add(Box.createRigidArea(new Dimension(rigidAreaSize, 0)));
        panel.add(getWpmLabel());
        panel.setMaximumSize(new Dimension(1000,50));
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
        label.setBackground(MainFrame.getFrameBackgroundColor());
    }

    public JLabel getDateLabel() {
        JLabel label = new JLabel(getDate());
        setUpLabel(label);
        return label;
    }

    public JLabel getTimeLabel() {
        JLabel label = new JLabel(getTime());
        setUpLabel(label);
        return label;
    }

    public JLabel getAccuracyLabel() {
        String accuracyString;
        if (accuracy - accuracy.intValue() > 0) accuracyString = accuracy.toString();
        else accuracyString = "" + accuracy.intValue();
        JLabel label = new JLabel(accuracyString);
        setUpLabel(label);
        label.setForeground(LabelAccuracyProcessor.getColorOfPercentage(getAccuracy()));
        return label;
    }

    public JLabel getWpmLabel() {
        JLabel label = new JLabel("" + getWpm());
        setUpLabel(label);
        return label;
    }

    public JLabel getElapsedTimeLabel() {
        JLabel label = new JLabel(getElapsedTime());
        setUpLabel(label);
        return label;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Float accuracy) {
        this.accuracy = accuracy;
    }

    public String getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(String elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public int getWpm() {
        return wpm;
    }

    public void setWpm(int wpm) {
        this.wpm = wpm;
    }

}
