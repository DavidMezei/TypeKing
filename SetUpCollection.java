import javax.swing.*;
import java.awt.*;

public class SetUpCollection {
    private MainFrame mainFrame;

    SetUpCollection(MainFrame mainFrame){
        this.mainFrame = mainFrame;
    }

    public void setUpPanel(JPanel panel, Component component){
        panel.setBackground(mainFrame.frameBackgroundColor);
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.add(Box.createRigidArea(new Dimension(50,0)));
        panel.add(component);
        panel.add(Box.createRigidArea(new Dimension(50,0)));
    }

    public void setUpTextPane(JTextPane textPane){
        textPane.setText(FileReader.getRandomText());
        textPane.setMaximumSize(new Dimension(600,300));
        textPane.setFont(new Font(mainFrame.defaultFont,Font.PLAIN,40));
        textPane.setEditable(false);
        textPane.setBorder(BorderFactory.createLineBorder(Color.cyan.darker(), 5));
        textPane.setBackground(mainFrame.textBackgroundColor);
        textPane.setForeground(mainFrame.textColor);
    }

    public void setUpTextField(JTextField textField){
        textField.setText("Just start to type!");
        textField.setMaximumSize(new Dimension(600,60));
        textField.setFont(new Font(mainFrame.defaultFont,Font.PLAIN,40));
        textField.setBackground(mainFrame.textBackgroundColor);
        textField.setForeground(Color.green.darker());
   }

    public void setUpFrame(JFrame frame){
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
        frame.setTitle("TypeKing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setBounds(600,200,700,700);
        frame.getContentPane().setBackground(mainFrame.frameBackgroundColor);
        frame.setLocationRelativeTo(null);
    }

    public void setUpLabel(JLabel label){
        label.setMaximumSize(new Dimension(200,70));
        label.setFont(new Font(mainFrame.defaultFont,Font.PLAIN,50));
        label.setForeground(mainFrame.labelColor);
    }
}
