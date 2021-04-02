import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collection;

public class MainFrame extends JFrame implements KeyListener {
    private JTextPane textPane;
    private JTextField textField;

    private Color frameBackgroundColor=Color.black;
    private Color textBackgroundColor = Color.darkGray.darker();
    private Color textColor = Color.white;

    private JPanel panelTextPane;
    private JPanel panelTextField;
    private String defaultFont="TimesRoman";

    MainFrame() {
        setUpFrame();

        textPane = new JTextPane();
        textField = new JTextField();
        setUpTextField();
        setUpTextPane();
        panelTextPane = new JPanel();
        setUpPanel(panelTextPane,textPane);
        panelTextField = new JPanel();
        setUpPanel(panelTextField,textField);

        addPanelsToContentPane();
        revalidate();
       // pack();
    }

    void addPanelsToContentPane(){
        getContentPane().add(Box.createRigidArea(new Dimension(0,50)));
        getContentPane().add(panelTextPane);
        getContentPane().add(Box.createRigidArea(new Dimension(0,25)));
        getContentPane().add(panelTextField);
        getContentPane().add(Box.createRigidArea(new Dimension(0,50)));
    }

    void setUpPanel(JPanel panel, Component component){
        panel.setBackground(getContentPane().getBackground());
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.add(Box.createRigidArea(new Dimension(50,0)));
        panel.add(component);
        panel.add(Box.createRigidArea(new Dimension(50,0)));
    }

    void setUpTextPane(){
        textPane.setText(fileReader.getRandomText());
        textPane.setMaximumSize(new Dimension(600,300));
        textPane.setFont(new Font(defaultFont,Font.PLAIN,40));
        textPane.setBackground(Color.yellow);
        textPane.setEditable(false);
        textPane.setBorder(BorderFactory.createLineBorder(Color.cyan.darker(), 5));
        textPane.setBackground(textBackgroundColor);
        textPane.setForeground(textColor);
    }

    void setUpTextField(){
        textField.setText("Just start to type!");
        textField.setMaximumSize(new Dimension(600,60));
        textField.setFont(new Font(defaultFont,Font.PLAIN,40));
        textField.setBackground(Color.green);
        textField.setFocusable(true);
        textField.requestFocus();
        textField.requestFocusInWindow();
        textField.setBackground(textBackgroundColor);
        textField.setForeground(Color.green.darker());
    }

    void setUpFrame(){
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        setTitle("TypeKing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setBounds(600,200,700,700);
        getContentPane().setBackground(frameBackgroundColor);
        setLocationRelativeTo(null);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}
