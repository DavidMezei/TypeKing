import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class HistoryPanelUploader {
    private static int addedPanelsToHistoryPanel = 0;
    private static JPanel historyPanelInnerPanel;
    private static Color lineColor1;
    private static Color lineColor2;

    public static void uploadLoadedHistoriesToPanel() {
        Vector<TypingHistory> typingHistories = HistoryFileReaderWriter.getLoadedTextHistory();
        if (addedPanelsToHistoryPanel == 0) {
            initializeComponents();
        }
        historyPanelInnerPanel.removeAll();
        historyPanelInnerPanel.revalidate();
        for (TypingHistory typingHistory : typingHistories) {
            addTypingHistoryToHistoryPanel(typingHistory);
        }
    }

    public static void addTypingHistoryToHistoryPanel(TypingHistory typingHistory) {
        if (addedPanelsToHistoryPanel == 0) {
            initializeComponents();
        }
        JSeparator separator = new JSeparator();
        separator.setMaximumSize(new Dimension(1000, 3));
        JPanel allInOneHistoryPanel = typingHistory.getAllAttributesInOnePanel();
        if (addedPanelsToHistoryPanel % 2 == 0) allInOneHistoryPanel.setBackground(lineColor1);
        else allInOneHistoryPanel.setBackground(lineColor2);
        historyPanelInnerPanel.add(allInOneHistoryPanel, 0);
        addedPanelsToHistoryPanel++;
        historyPanelInnerPanel.add(separator, 0);
    }

    private static void initializeComponents() {
        JScrollPane scrollPane = (JScrollPane) MainFrame.getHistoryPanel().getComponent(1);
        historyPanelInnerPanel = (JPanel) scrollPane.getViewport().getComponent(0);
        lineColor1 = new Color(15, 15, 15);
        lineColor2 = new Color(30, 45, 40);
    }
}
