package Controller;

import Model.HistoryReaderWriter;
import Model.MainModel;
import Model.Observer;
import Model.TypingHistory;
import View.MainFrame;
import View.TypingHistoryLabels;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

public class HistoryPanelController implements Observer {
    private int addedPanelsToHistoryPanel = 0;
    private JPanel historyPanelInnerPanel;
    private Color lineColor1;
    private Color lineColor2;
    private MainFrame mainFrame;
    private MainModel mainModel;
    private HistoryReaderWriter historyReaderWriter;

    public HistoryPanelController(MainModel mainModel, HistoryReaderWriter historyReaderWriter, MainFrame mainFrame) {
        this.mainModel = mainModel;
        this.mainFrame = mainFrame;
        this.historyReaderWriter = historyReaderWriter;
    }

    public void uploadLoadedHistoriesToPanel() {
        Vector<TypingHistory> typingHistories = historyReaderWriter.getTextHistory(mainModel.getTextPaneMyText().getTextID());
        if (addedPanelsToHistoryPanel == 0) {
            initializeComponents();
        }
        historyPanelInnerPanel.removeAll();
        historyPanelInnerPanel.revalidate();
        for (TypingHistory typingHistory : typingHistories) {
            addTypingHistoryToHistoryPanel(typingHistory);
        }
        historyPanelInnerPanel.revalidate();
    }

    public void addTypingHistoryToHistoryPanel(TypingHistory typingHistory) {
        if (addedPanelsToHistoryPanel == 0) {
            initializeComponents();
        }
        JSeparator separator = new JSeparator();
        separator.setMaximumSize(new Dimension(1000, 3));
        JPanel allInOneHistoryPanel = new TypingHistoryLabels(typingHistory).getAllAttributesInOnePanel();
        if (addedPanelsToHistoryPanel % 2 == 0) allInOneHistoryPanel.setBackground(lineColor1);
        else allInOneHistoryPanel.setBackground(lineColor2);
        historyPanelInnerPanel.add(allInOneHistoryPanel, 0);
        addedPanelsToHistoryPanel++;
        historyPanelInnerPanel.add(separator, 0);
    }

    private void initializeComponents() {
        JScrollPane scrollPane = (JScrollPane) mainFrame.getHistoryPanel().getComponent(1);
        historyPanelInnerPanel = (JPanel) scrollPane.getViewport().getComponent(0);
        lineColor1 = new Color(15, 15, 15);
        lineColor2 = new Color(30, 45, 40);
    }

    private TypingHistory createTypingHistoryFromCurrentRound() {
        TypingHistory typingHistory = new TypingHistory();
        String accuracyPercentage = mainFrame.getLabelAccuracyPercentage().getText();
        String elapsedTime = mainFrame.getLabelElapsedTime().getText();
        String wpm = mainFrame.getLabelWPM().getText();
        typingHistory.setDate(LocalDate.now().toString());
        typingHistory.setTime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        typingHistory.setAccuracy(Float.parseFloat(accuracyPercentage.substring(0, accuracyPercentage.length() - 1)));
        typingHistory.setElapsedTime(elapsedTime.substring(6));
        typingHistory.setWpm(Integer.parseInt(wpm.substring(0, wpm.length() - 4)));
        return typingHistory;
    }

    @Override
    public void mistakeUpdate() {

    }

    @Override
    public void caretIndexUpdate() {

    }

    @Override
    public void newRoundUpdate() {
        uploadLoadedHistoriesToPanel();
    }

    @Override
    public void startRoundUpdate() {

    }

    @Override
    public void completionRoundUpdate() {
        TypingHistory typingHistory = createTypingHistoryFromCurrentRound();
        historyReaderWriter.addRoundToHistory(mainModel.getTextPaneMyText().getTextID(), typingHistory);
        addTypingHistoryToHistoryPanel(typingHistory);
        historyPanelInnerPanel.revalidate();
    }

    @Override
    public void interruptedRoundUpdate() {

    }
}
