import java.awt.*;
import java.awt.event.ActionEvent;

public class ButtonActionProcessor {
    private MainFrame mainFrame;
    private KeyProcessor keyProcessor;

    ButtonActionProcessor(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        keyProcessor = mainFrame.getKeyProcessor();
    }

    public void buttonActionProcessing(ActionEvent actionEvent) {
        if (actionEvent.getSource() == mainFrame.getButtonTryAgain()) {
            processButtonTryAgain();
        } else if (actionEvent.getSource() == mainFrame.getButtonNewText()) {
            processButtonNewText();
        }
    }

    private void processButtonTryAgain() {
        processButton();
    }

    private void processButtonNewText() {
        processButton();
        keyProcessor.setTextPaneText(TextFileReader.getRandomText());
        mainFrame.getTextPane().setText(keyProcessor.getTextPaneText());
        HistoryPanelUploader.uploadLoadedHistoriesToPanel();
    }

    private void processButton() {
        keyProcessor.getLabelsThread().stopLabelsThread();
        keyProcessor.getTextPaneRedHighlighter().removeHighlight(0, keyProcessor.getCaretIndex());
        keyProcessor.getTextPaneCaretHighlighter().removeHighlight(0, keyProcessor.getCaretIndex());
        TextPaneLetterPainter.paintLetter(mainFrame.getTextPane(), 0, keyProcessor.getCaretIndex(), mainFrame.getTextColor());
        keyProcessor.setCaretIndexToZero();
        keyProcessor.setFirstKeyProcessingCallToTrue();
        keyProcessor.getLabelAccuracyProcessor().setDefaultLabelAccuracy();
        mainFrame.getLabelElapsedTime().setText("Time 0:00");
        mainFrame.getLabelWPM().setText("0 WPM");
        mainFrame.getTextField().setText("Just start to type!");
        mainFrame.getTextField().setEditable(true);
        mainFrame.getTextField().setForeground(Color.green.darker());
        mainFrame.getTextField().setBackground(mainFrame.getTextBackgroundColor());
        mainFrame.getTextField().requestFocusInWindow();
    }
}
