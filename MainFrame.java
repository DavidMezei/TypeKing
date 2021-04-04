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
    JButton buttonTryAgain = new JButton();
    JButton buttonNewText = new JButton();
    JPanel panelTextPane = new JPanel();
    JPanel panelTextField = new JPanel();
    JPanel panelLabels = new JPanel();
    JLabel labelWPM = new JLabel("0 WPM");
    JLabel labelElapsedTime = new JLabel("Time: 0:00");
    KeyProcessor keyProcessor;
    SetUpCollection SetUpCollection;

    MainFrame() {
        SetUpCollection = new SetUpCollection(this);
        SetUpCollection.setUpFrame(this);
        SetUpCollection.setUpTextPane(textPane);
        SetUpCollection.setUpTextField(textField);
        SetUpCollection.setUpPanel(panelTextPane, textPane);
        SetUpCollection.setUpPanel(panelTextField, textField);
        SetUpCollection.setUpPanel(panelLabels, labelWPM);
        SetUpCollection.setUpPanel(panelLabels, labelElapsedTime);
        SetUpCollection.setUpLabel(labelWPM);
        SetUpCollection.setUpLabel(labelElapsedTime);

        addPanelsToContentPane();
        addListeners();
        revalidate();
        textField.requestFocusInWindow();
        keyProcessor = new KeyProcessor(this);
    }

    void addPanelsToContentPane() {
        getContentPane().add(Box.createRigidArea(new Dimension(0, 50)));
        getContentPane().add(panelLabels);
        getContentPane().add(Box.createRigidArea(new Dimension(0, 20)));
        getContentPane().add(panelTextPane);
        getContentPane().add(Box.createRigidArea(new Dimension(0, 25)));
        getContentPane().add(panelTextField);
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
    public void actionPerformed(ActionEvent e) {

    }
}
