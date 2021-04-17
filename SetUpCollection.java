import javax.swing.*;
import java.awt.*;

public class SetUpCollection {
    private MainFrame mainFrame;

    SetUpCollection(MainFrame mainFrame){
        this.mainFrame = mainFrame;
    }

    public void setUpPanel(JPanel panel){
        panel.setBackground(mainFrame.getFrameBackgroundColor());
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.add(Box.createRigidArea(new Dimension(50,0)));
    }

    public void addComponentToPanel(JPanel panel, Component component){
        panel.add(component);
        panel.add(Box.createRigidArea(new Dimension(50,0)));
    }

    public void addComponentToPanelWithoutRigidArea(JPanel panel, Component component){
        panel.add(component);
    }

    public void setUpTextPane(JTextPane textPane){
        textPane.setText(FileReader.getRandomText());
        textPane.setMaximumSize(new Dimension(1200,200));
        textPane.setFont(new Font(mainFrame.getDefaultFont(),Font.PLAIN,40));
        textPane.setEditable(false);
        textPane.setBorder(BorderFactory.createLineBorder(Color.cyan.darker(), 5));
        textPane.setBackground(mainFrame.getTextBackgroundColor());
        textPane.setForeground(mainFrame.getTextColor());
        textPane.setMargin(new Insets(10,10,10,10));
    }

    public void setUpTextField(JTextField textField){
        textField.setText("Just start to type!");
        textField.setMaximumSize(new Dimension(1200,60));
        textField.setFont(new Font(mainFrame.getDefaultFont(),Font.PLAIN,40));
        textField.setBackground(mainFrame.getTextBackgroundColor());
        textField.setForeground(Color.green.darker());
        textField.requestFocusInWindow();
   }

    public void setUpFrame(JFrame frame){
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
        frame.setTitle("TypeKing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setBounds(600,200,1000,700);
        frame.getContentPane().setBackground(mainFrame.getFrameBackgroundColor());
        frame.setLocationRelativeTo(null);
    }

    public void setUpLabel(JLabel label){
        label.setMaximumSize(new Dimension(400,70));
        label.setFont(new Font(mainFrame.getDefaultFont(),Font.PLAIN,50));
        label.setForeground(mainFrame.getLabelColor());
    }

    public void setUpButton(JButton button){
        button.setMaximumSize(new Dimension(300,100));
        button.setFont(new Font(mainFrame.getDefaultFont(),Font.PLAIN,45));
        button.setForeground(Color.orange);
        button.setBackground(Color.blue.darker());
    }
}
