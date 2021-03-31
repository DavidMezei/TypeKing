import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JTextPane textPane;
    private JTextField textField;
    private Color frameBackgroundColor=Color.black;

    MainFrame() {
        setUpFrame();

        textPane = new JTextPane();
        setUpTextPane();
        textField = new JTextField();
        setUpTextField();

        JPanel panelTextPane = new JPanel();
        setUpPanel(panelTextPane,textPane);
        JPanel panelTextField = new JPanel();
        setUpPanel(panelTextField,textField);

        addComponentsToContentPane(panelTextPane,panelTextField);

        revalidate();
        pack();
    }

    void addComponentsToContentPane(JPanel panelTextPane, JPanel panelTextField){
        getContentPane().add(Box.createRigidArea(new Dimension(0,50)));
        getContentPane().add(panelTextPane);
        getContentPane().add(Box.createRigidArea(new Dimension(0,25)));
        getContentPane().add(panelTextField);
        getContentPane().add(Box.createRigidArea(new Dimension(0,50)));
    }

    void setUpPanel(JPanel panel, Component comp){
        panel.setBackground(getContentPane().getBackground());
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.add(Box.createRigidArea(new Dimension(50,0)));
        panel.add(comp);
        panel.add(Box.createRigidArea(new Dimension(50,0)));

    }

    void setUpTextPane(){
        textPane.setText("This is the text that I want to write here!");
        textPane.setMaximumSize(new Dimension(1000,200));
        textPane.setFont(new Font("TimeRoman",Font.PLAIN,40));
        textPane.setBackground(Color.yellow);
    }

    void setUpTextField(){
        textField.setText("Start to type as fast as you can!");
        textField.setMaximumSize(new Dimension(2000,60));
        textField.setFont(new Font("TimesRoma",Font.PLAIN,40));
        textField.setBackground(Color.green);
    }

    void setUpFrame(){
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        setTitle("TypeKing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setBounds(600,200,700,700);
        getContentPane().setBackground(frameBackgroundColor);
    }

}
