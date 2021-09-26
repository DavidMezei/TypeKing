package View;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JTextPane textPane;
    private JTextField textField;
    private Color labelColor;
    private Color frameBackgroundColor;
    private Color textBackgroundColor;
    private Color textColor;
    private String defaultFont;
    private JButton buttonTryAgain;
    private JButton buttonNewText;
    private JPanel panelTextPane;
    private JPanel panelTextField;
    private JPanel panelLabels;
    private JPanel panelButtons;
    private JLabel labelWPM;
    private JLabel labelElapsedTime;
    private JLabel labelAccuracy;
    private JLabel labelAccuracyPercentage;
    private SetUpCollection SetUpCollection;
    private JPanel historyPanel;
    private JLabel historyLabel;
    private JPanel historyLabelPanel;

    public MainFrame() {
        createAndInitializeObjects();
        addPanelsToContentPane();
        callSetUps();
        revalidate();
    }

    private void createAndInitializeObjects() {
        SetUpCollection = new SetUpCollection(this);
        textPane = new JTextPane();
        textField = new JTextField();
        buttonTryAgain = new JButton();
        buttonNewText = new JButton();
        panelTextPane = new JPanel();
        panelTextField = new JPanel();
        panelLabels = new JPanel();
        panelButtons = new JPanel();
        labelWPM = new JLabel();
        labelElapsedTime = new JLabel();
        labelAccuracy = new JLabel();
        labelAccuracyPercentage = new JLabel();
        historyPanel = new JPanel();
        historyLabel = new JLabel();
        historyLabelPanel = new JPanel();
        SetUpCollection.setDefaultSettings();
    }

    private void callSetUps() {
        SetUpCollection.setUpFrame(this);
        SetUpCollection.setUpPanel(panelTextField);
        SetUpCollection.addComponentToPanelWithRigidArea(panelTextField, textField);
        SetUpCollection.setUpTextField(textField);
        SetUpCollection.setUpTextPane(textPane);
        SetUpCollection.setUpPanel(panelTextPane);
        SetUpCollection.addComponentToPanelWithRigidArea(panelTextPane, textPane);
        SetUpCollection.setUpPanel(panelLabels);
        panelLabels.add(labelAccuracy);
        SetUpCollection.addComponentToPanelWithRigidArea(panelLabels, labelAccuracyPercentage);
        SetUpCollection.addComponentToPanelWithRigidArea(panelLabels, labelElapsedTime);
        SetUpCollection.addComponentToPanelWithRigidArea(panelLabels, labelWPM);
        SetUpCollection.setUpLabel(labelWPM);
        SetUpCollection.setUpLabel(labelElapsedTime);
        SetUpCollection.setUpLabel(labelAccuracy);
        SetUpCollection.setUpPanel(panelButtons);
        SetUpCollection.setUpLabel(labelAccuracyPercentage);
        SetUpCollection.addComponentToPanelWithRigidArea(panelButtons, buttonNewText);
        SetUpCollection.addComponentToPanelWithRigidArea(panelButtons, buttonTryAgain);
        SetUpCollection.setUpButton(buttonTryAgain);
        SetUpCollection.setUpButton(buttonNewText);
        SetUpCollection.setUpHistoryPanel(historyPanel);
        SetUpCollection.setUpHistoryLabel(historyLabel);
        SetUpCollection.setUpPanel(historyLabelPanel);
        SetUpCollection.addComponentToPanelWithRigidArea(historyLabelPanel, historyLabel);
    }

    void addPanelsToContentPane() {
        getContentPane().add(Box.createRigidArea(new Dimension(0, 20)));
        getContentPane().add(panelLabels);
        getContentPane().add(Box.createRigidArea(new Dimension(0, 25)));
        getContentPane().add(panelTextPane);
        getContentPane().add(Box.createRigidArea(new Dimension(0, 25)));
        getContentPane().add(panelTextField);
        getContentPane().add(Box.createRigidArea(new Dimension(0, 50)));
        getContentPane().add(panelButtons);
        getContentPane().add(Box.createRigidArea(new Dimension(0, 50)));
        getContentPane().add(historyLabelPanel);
        getContentPane().add(historyPanel);
        getContentPane().add(Box.createRigidArea(new Dimension(0, 50)));
    }

    public void setTextPane(JTextPane textPane) {
        this.textPane = textPane;
    }

    public void setTextField(JTextField textField) {
        this.textField = textField;
    }

    public void setLabelColor(Color labelColor) {
        this.labelColor = labelColor;
    }

    public void setFrameBackgroundColor(Color frameBackgroundColor) {
        this.frameBackgroundColor = frameBackgroundColor;
    }

    public void setTextBackgroundColor(Color textBackgroundColor) {
        this.textBackgroundColor = textBackgroundColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public void setDefaultFont(String defaultFont) {
        this.defaultFont = defaultFont;
    }

    public JPanel getHistoryPanel() {
        return historyPanel;
    }

    public JTextPane getTextPane() {
        return textPane;
    }

    public JTextField getTextField() {
        return textField;
    }

    public Color getLabelColor() {
        return labelColor;
    }

    public Color getFrameBackgroundColor() {
        return frameBackgroundColor;
    }

    public Color getTextBackgroundColor() {
        return textBackgroundColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public String getDefaultFont() {
        return defaultFont;
    }

    public JButton getButtonTryAgain() {
        return buttonTryAgain;
    }

    public JButton getButtonNewText() {
        return buttonNewText;
    }

    public JPanel getPanelTextPane() {
        return panelTextPane;
    }

    public JPanel getPanelTextField() {
        return panelTextField;
    }

    public JPanel getPanelLabels() {
        return panelLabels;
    }

    public JPanel getPanelButtons() {
        return panelButtons;
    }

    public JLabel getLabelWPM() {
        return labelWPM;
    }

    public JLabel getLabelElapsedTime() {
        return labelElapsedTime;
    }

    public JLabel getLabelAccuracy() {
        return labelAccuracy;
    }

    public JLabel getLabelAccuracyPercentage() {
        return labelAccuracyPercentage;
    }

}
