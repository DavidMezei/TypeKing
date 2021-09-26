package Controller;

import Model.MainModel;
import Model.Observer;
import Model.PercentageColors;

import javax.swing.*;
import java.awt.*;

public class LabelAccuracyController implements Observer {
    private MainModel mainModel;
    private int overallMistakeCount;
    private int recentMistakeCount;
    private int textLength;
    private JLabel labelAccuracyPercentage;
    private PercentageColors percentColors;

    public LabelAccuracyController(MainModel mainModel, JLabel labelAccuracyPercentage) {
        this.mainModel = mainModel;
        textLength = mainModel.getTextPaneText().length();
        overallMistakeCount = 0;
        recentMistakeCount = 0;
        percentColors = PercentageColors.getInstance();
        this.labelAccuracyPercentage = labelAccuracyPercentage;
    }

    public void updateLabelAccuracy() {
        int remainedKeys = textLength - overallMistakeCount;
        float percentage = remainedKeys / (float) textLength;
        percentage *= 100;
        if (percentage < 0) percentage = 0;
        setAccuracyPercentageWithColor(percentage);
    }

    public void setAccuracyPercentageWithColor(float percentage) {
        labelAccuracyPercentage.setForeground(percentColors.getColorOfPercentage(percentage));
        labelAccuracyPercentage.setText(String.format("%.1f", percentage) + "%");
    }

    public void setDefaultLabelAccuracy() {
        overallMistakeCount = 0;
        labelAccuracyPercentage.setText("100%");
        labelAccuracyPercentage.setForeground(Color.cyan);
    }

    @Override
    public void mistakeUpdate() {
        if (recentMistakeCount < mainModel.getMistakeCount()) {
            overallMistakeCount++;
            updateLabelAccuracy();
        }
        recentMistakeCount = mainModel.getMistakeCount();
    }

    @Override
    public void newRoundUpdate() {
        textLength = mainModel.getTextPaneText().length();
        setDefaultLabelAccuracy();
    }

    @Override
    public void startRoundUpdate() {

    }

    @Override
    public void completionRoundUpdate() {

    }

    @Override
    public void interruptedRoundUpdate() {

    }

    @Override
    public void caretIndexUpdate() {

    }
}
