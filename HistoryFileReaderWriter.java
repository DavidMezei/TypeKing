import java.io.*;
import java.util.Scanner;
import java.util.Vector;

public class HistoryFileReaderWriter {
    private static Vector<Vector<TypingHistory>> typingHistories;
    private static final String fileName = "C:\\Users\\mezei\\Desktop\\Java Programs\\TypeKing\\src\\history.txt";
    private int historyLineIndex;

    public HistoryFileReaderWriter() {
        String historyLine;
        TypingHistory typingHistory;
        typingHistories = new Vector<Vector<TypingHistory>>();
        File file = new File(fileName);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNextLine()) {
            historyLine = scanner.nextLine();
            Vector<TypingHistory> historyLineTypingHistories = new Vector<>();
            historyLineIndex = 0;
            while (historyLineIndex < historyLine.length()) {
                typingHistory = convertAPartOfHistoryLineInTypingHistory(historyLine);
                historyLineTypingHistories.add(typingHistory);
            }
            typingHistories.add(historyLineTypingHistories);
        }
    }

    //TODO put try catch if index=-1
    public static Vector<TypingHistory> getLoadedTextHistory() {
        Vector<TypingHistory> vector = typingHistories.get(TextFileReader.getCurrentTextIndex());

        return vector;
    }

    public static void addCurrentRoundToHistory(TypingHistory typingHistory) {
        typingHistories.get(TextFileReader.getCurrentTextIndex()).add(typingHistory);
    }

    public static void writeUpdatesInHistoryFile() {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            String historyLine = convertTypingHistoriesToHistoryLine(typingHistories.get(0));
            fileWriter.write(historyLine);
            int i = 1;
            while (i < typingHistories.size()) {
                historyLine = convertTypingHistoriesToHistoryLine(typingHistories.get(i));
                fileWriter.append(historyLine);
                i++;
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private TypingHistory convertAPartOfHistoryLineInTypingHistory(String historyLine) {
        TypingHistory typingHistory = new TypingHistory();
        int spaceIndex = getNextSpaceIndexFrom(historyLineIndex, historyLine);
        typingHistory.setDate(historyLine.substring(historyLineIndex, spaceIndex));

        historyLineIndex = spaceIndex + 1;
        spaceIndex = getNextSpaceIndexFrom(historyLineIndex, historyLine);
        typingHistory.setTime(historyLine.substring(historyLineIndex, spaceIndex));

        historyLineIndex = spaceIndex + 1;
        spaceIndex = getNextSpaceIndexFrom(historyLineIndex, historyLine);
        typingHistory.setAccuracy(Float.parseFloat(historyLine.substring(historyLineIndex, spaceIndex)));

        historyLineIndex = spaceIndex + 1;
        spaceIndex = getNextSpaceIndexFrom(historyLineIndex, historyLine);
        typingHistory.setElapsedTime(historyLine.substring(historyLineIndex, spaceIndex));

        historyLineIndex = spaceIndex + 1;
        spaceIndex = getNextSpaceIndexFrom(historyLineIndex, historyLine);
        typingHistory.setWpm(Integer.parseInt(historyLine.substring(historyLineIndex, spaceIndex)));

        historyLineIndex = spaceIndex + 1;

        return typingHistory;
    }

    private int getNextSpaceIndexFrom(int beginIndex, String historyLine) {
        int i = beginIndex;
        while (i < historyLine.length() && historyLine.charAt(i) != ' ') {
            i++;
        }
        return i;
    }

    private static String convertTypingHistoriesToHistoryLine(Vector<TypingHistory> typingHistories) {
        int i = 0;
        String historyLine = "";
        while (i < typingHistories.size()) {
            TypingHistory typingHistory = typingHistories.get(i);
            historyLine += (typingHistory.getDate() + " ");
            historyLine += (typingHistory.getTime() + " ");
            historyLine += (typingHistory.getAccuracy() + " ");
            historyLine += (typingHistory.getElapsedTime() + " ");
            historyLine += (typingHistory.getWpm() + " ");
            i++;
        }
        historyLine += "\n";
        return historyLine;
    }
}
