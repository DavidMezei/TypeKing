import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainFrame extends JFrame implements KeyListener, ActionListener {
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
    private KeyProcessor keyProcessor;
    private ButtonActionProcessor buttonActionProcessor;
    private SetUpCollection SetUpCollection;

    MainFrame() {
        createAndInitializeObjects();
        addPanelsToContentPane();
        callSetUps();
        addListeners();
        revalidate();
    }

    private void createAndInitializeObjects() {
        labelColor = Color.cyan;
        frameBackgroundColor = Color.black;
        textBackgroundColor = Color.darkGray.darker();
        textColor = Color.white;
        defaultFont = "TimesRoman";
        SetUpCollection = new SetUpCollection(this);
        textPane = new JTextPane();
        textField = new JTextField();
        buttonTryAgain = new JButton("Try again");
        buttonNewText = new JButton("New Text");
        panelTextPane = new JPanel();
        panelTextField = new JPanel();
        panelLabels = new JPanel();
        panelButtons = new JPanel();
        labelWPM = new JLabel("0 WPM");
        labelElapsedTime = new JLabel("Time: 0:00");
        labelAccuracy = new JLabel("Accuracy: ");
        labelAccuracyPercentage = new JLabel("100%");
        keyProcessor = new KeyProcessor(this);
        buttonActionProcessor = new ButtonActionProcessor(this);
    }

    private void callSetUps() {
        SetUpCollection.setUpFrame(this);
        SetUpCollection.setUpPanel(panelTextField);
        SetUpCollection.addComponentToPanel(panelTextField, textField);
        SetUpCollection.setUpTextField(textField);
        SetUpCollection.setUpTextPane(textPane);
        SetUpCollection.setUpPanel(panelTextPane);
        SetUpCollection.addComponentToPanel(panelTextPane, textPane);
        SetUpCollection.setUpPanel(panelLabels);
        SetUpCollection.addComponentToPanelWithoutRigidArea(panelLabels,labelAccuracy);
        SetUpCollection.addComponentToPanel(panelLabels, labelAccuracyPercentage);
        SetUpCollection.addComponentToPanel(panelLabels, labelElapsedTime);
        SetUpCollection.addComponentToPanel(panelLabels, labelWPM);
        SetUpCollection.setUpLabel(labelWPM);
        SetUpCollection.setUpLabel(labelElapsedTime);
        SetUpCollection.setUpLabel(labelAccuracy);
        SetUpCollection.setUpPanel(panelButtons);
        SetUpCollection.setUpLabel(labelAccuracyPercentage);
        SetUpCollection.addComponentToPanel(panelButtons, buttonNewText);
        SetUpCollection.addComponentToPanel(panelButtons, buttonTryAgain);
        SetUpCollection.setUpButton(buttonTryAgain);
        SetUpCollection.setUpButton(buttonNewText);
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
    }

    void addListeners() {
        textField.addKeyListener(this);
        buttonNewText.addActionListener(this);
        buttonTryAgain.addActionListener(this);
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        keyProcessor.keyProcessing(keyEvent);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        buttonActionProcessor.buttonActionProcessing(actionEvent);
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

    public KeyProcessor getKeyProcessor() {
        return keyProcessor;
    }

    public ButtonActionProcessor getButtonActionProcessor() {
        return buttonActionProcessor;
    }

}
