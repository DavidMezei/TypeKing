package Controller;

import Model.HistoryFileReaderWriter;
import View.MainFrame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AppClosingController extends WindowAdapter {
    private MainFrame mainFrame;
    private HistoryFileReaderWriter historyFileReaderWriter;

    public AppClosingController(HistoryFileReaderWriter historyFileReaderWriter, MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        mainFrame.addWindowListener(this);
        this.historyFileReaderWriter = historyFileReaderWriter;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        super.windowClosing(e);
        historyFileReaderWriter.writeUpdatesInHistoryFile();
    }
}
