import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainFrame extends JFrame implements KeyListener {
    JTextPane textPane;
    JTextField textField;

    Color frameBackgroundColor = Color.black;
    Color textBackgroundColor = Color.darkGray.darker();
    Color textColor = Color.white;

    JPanel panelTextPane;
    JPanel panelTextField;
    String defaultFont = "TimesRoman";

    KeyProcessor keyProcessor;
    SetUpCollection SetUpCollection;
    MainFrame() {
        SetUpCollection=new SetUpCollection(this);
        SetUpCollection.setUpFrame(this);

        textPane = new JTextPane();
        SetUpCollection.setUpTextPane(textPane);
        textField = new JTextField();
        SetUpCollection.setUpTextField(textField);
        textField.addKeyListener(this);
        panelTextPane = new JPanel();
        SetUpCollection.setUpPanel(panelTextPane, textPane);
        panelTextField = new JPanel();
        SetUpCollection.setUpPanel(panelTextField, textField);

        addPanelsToContentPane();
        revalidate();
        textField.requestFocusInWindow();
        keyProcessor = new KeyProcessor(this);
    }

    void addPanelsToContentPane() {
        getContentPane().add(Box.createRigidArea(new Dimension(0, 50)));
        getContentPane().add(panelTextPane);
        getContentPane().add(Box.createRigidArea(new Dimension(0, 25)));
        getContentPane().add(panelTextField);
        getContentPane().add(Box.createRigidArea(new Dimension(0, 50)));
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

}
