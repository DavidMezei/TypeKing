package Model;

import java.util.Vector;

public interface HistoryReaderWriter {
    Vector<TypingHistory> getTextHistory(int textID);

    void addRoundToHistory(int textID, TypingHistory typingHistory);
}
