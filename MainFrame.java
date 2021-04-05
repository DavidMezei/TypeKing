import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainFrame extends JFrame implements KeyListener, ActionListener {
    JTextPane textPane = new JTextPane();
    JTextField textField = new JTextField();
    Color labelColor = Color.CYAN;
    Color frameBackgroundColor = Color.black;
    Color textBackgroundColor = Color.darkGray.darker();
    Color textColor = Color.white;
    String defaultFont = "TimesRoman";
    JButton buttonTryAgain = new JButton("Try again");
    JButton buttonNewText = new JButton("New text");
    JPanel panelTextPane = new JPanel();
    JPanel panelTextField = new JPanel();
    JPanel panelLabels = new JPanel();
    JPanel panelButtons = new JPanel();
    JLabel labelWPM = new JLabel("0 WPM");
    JLabel labelElapsedTime = new JLabel("Time: 0:00");
    KeyProcessor keyProcessor;
    ButtonActionProcessor buttonActionProcessor;
    SetUpCollection SetUpCollection;

    MainFrame() {
        SetUpCollection = new SetUpCollection(this);
        SetUpCollection.setUpFrame(this);
        SetUpCollection.setUpTextPane(textPane);
        SetUpCollection.setUpTextField(textField);
        SetUpCollection.setUpPanel(panelTextPane);
        SetUpCollection.addComponentToPanel(panelTextPane, textPane);
        SetUpCollection.setUpPanel(panelTextField);
        SetUpCollection.addComponentToPanel(panelTextField, textField);
        SetUpCollection.setUpPanel(panelLabels);
        SetUpCollection.addComponentToPanel(panelLabels, labelWPM);
        SetUpCollection.addComponentToPanel(panelLabels, labelElapsedTime);
        SetUpCollection.setUpLabel(labelWPM);
        SetUpCollection.setUpLabel(labelElapsedTime);
        SetUpCollection.setUpPanel(panelButtons);
        SetUpCollection.addComponentToPanel(panelButtons,buttonNewText);
        SetUpCollection.addComponentToPanel(panelButtons,buttonTryAgain);
        SetUpCollection.setUpButton(buttonTryAgain);
        SetUpCollection.setUpButton(buttonNewText);

        addPanelsToContentPane();
        addListeners();
        revalidate();
        textField.requestFocusInWindow();
        keyProcessor = new KeyProcessor(this);
        buttonActionProcessor=new ButtonActionProcessor(this);
    }

    void addPanelsToContentPane() {
        getContentPane().add(Box.createRigidArea(new Dimension(0, 50)));
        getContentPane().add(panelLabels);
        getContentPane().add(Box.createRigidArea(new Dimension(0, 20)));
        getContentPane().add(panelTextPane);
        getContentPane().add(Box.createRigidArea(new Dimension(0, 25)));
        getContentPane().add(panelTextField);
        getContentPane().add(Box.createRigidArea(new Dimension(0, 50)));
        getContentPane().add(panelButtons);
        getContentPane().add(Box.createRigidArea(new Dimension(0,50)));
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
}
