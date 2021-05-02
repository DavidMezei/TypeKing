import javax.swing.*;
import java.awt.*;

public class SetUpCollection {
    private MainFrame mainFrame;

    SetUpCollection(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void setUpPanel(JPanel panel) {
        panel.setBackground(MainFrame.getFrameBackgroundColor());
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.add(Box.createRigidArea(new Dimension(50, 0)));
    }

    public void setUpHistoryPanel(JPanel panel) {
        setUpPanel(panel);
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.PAGE_AXIS));
        innerPanel.setBackground(panel.getBackground());
        JScrollPane scrollPane = new JScrollPane(innerPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane);
        panel.add(Box.createRigidArea(new Dimension(50, 0)));
        new HistoryFileReaderWriter();
        HistoryPanelUploader.uploadLoadedHistoriesToPanel();
    }

    public void setUpHistoryLabel(JLabel label) {
        label.setFont(new Font(mainFrame.getDefaultFont(), Font.BOLD, 35));
        label.setText("        Date and time          Accuracy       Time       WPM");
        label.setForeground(Color.yellow);
        label.setOpaque(true);
        label.setBackground(Color.blue.darker());
    }

    public void addComponentToPanelWithRigidArea(JPanel panel, Component component) {
        panel.add(component);
        panel.add(Box.createRigidArea(new Dimension(50, 0)));
    }

    public void setUpTextPane(JTextPane textPane) {
        textPane.setText(TextFileReader.getRandomText());
        textPane.setMaximumSize(new Dimension(1200, 200));
        textPane.setFont(new Font(mainFrame.getDefaultFont(), Font.PLAIN, 40));
        textPane.setEditable(false);
        textPane.setBorder(BorderFactory.createLineBorder(Color.cyan.darker(), 5));
        textPane.setBackground(mainFrame.getTextBackgroundColor());
        textPane.setForeground(mainFrame.getTextColor());
        textPane.setMargin(new Insets(10, 10, 10, 10));
    }

    public void setUpTextField(JTextField textField) {
        textField.setText("Just start to type!");
        textField.setMaximumSize(new Dimension(1200, 60));
        textField.setFont(new Font(mainFrame.getDefaultFont(), Font.PLAIN, 40));
        textField.setBackground(mainFrame.getTextBackgroundColor());
        textField.setForeground(Color.green.darker());
        textField.requestFocusInWindow();
    }

    public void setUpFrame(JFrame frame) {
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
        frame.setTitle("TypeKing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setBounds(600, 200, 1000, 1000);
        frame.getContentPane().setBackground(MainFrame.getFrameBackgroundColor());
        frame.setLocationRelativeTo(null);
    }

    public void setUpLabel(JLabel label) {
        label.setMaximumSize(new Dimension(400, 70));
        label.setFont(new Font(mainFrame.getDefaultFont(), Font.PLAIN, 50));
        label.setForeground(mainFrame.getLabelColor());
    }

    public void setUpButton(JButton button) {
        button.setMaximumSize(new Dimension(300, 100));
        button.setFont(new Font(mainFrame.getDefaultFont(), Font.PLAIN, 45));
        button.setForeground(Color.orange);
        button.setBackground(Color.blue.darker());
    }
}
