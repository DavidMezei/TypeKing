import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JTextPane textPane;
    private JTextField textField;

    MainFrame() {
        setLayout(new BorderLayout());
        setTitle("TypeKing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setBounds(1700,700,700,700);

        textPane = new JTextPane();
        setTextPane();
        textField = new JTextField();
        setTextField();

        getContentPane().add(textPane,BorderLayout.CENTER);
        getContentPane().add(textField,BorderLayout.NORTH);
        getContentPane().setBackground(Color.black);
        revalidate();

    }

    void setTextPane(){
        textPane.setText("This is the text that I want to write here!");
        textPane.setSize(new Dimension(100,100));
        textPane.setBounds(200,200,200,200);
        textPane.setBackground(Color.blue);
        textPane.revalidate();
        textPane.repaint();
    }

    void setTextField(){
        textField.setText("Start to type as fast you can!");
        textField.setBounds(100,100,100,100);
        textField.setBackground(Color.green);
    }

}
