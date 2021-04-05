import java.awt.event.ActionEvent;

public class ButtonActionProcessor {
    private MainFrame mainFrame;
    private KeyProcessor keyProcessor;

    ButtonActionProcessor(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        keyProcessor = mainFrame.keyProcessor;
    }

    public void buttonActionProcessing(ActionEvent actionEvent) {
        if (actionEvent.getSource() == mainFrame.buttonTryAgain) {
            processButtonTryAgain();
        } else if (actionEvent.getSource() == mainFrame.buttonNewText) {
            processButtonNewText();
        }
    }

    private void processButtonTryAgain() {
        keyProcessor.labelsThread.stopLabelsThread();
        keyProcessor.textPaneHighlighter.removeHighlight(0, keyProcessor.getCaretIndex());
        keyProcessor.textPaneCaretHighlighter.removeHighlight(0, keyProcessor.getCaretIndex());
        TextPaneLetterPainter.paintLetter(mainFrame.textPane, 0, keyProcessor.getCaretIndex(), mainFrame.textColor);
        keyProcessor.setCaretIndexToZero();
        keyProcessor.firstKeyProcessingCall = true;
    }

    private void processButtonNewText() {
        keyProcessor.labelsThread.stopLabelsThread();
        keyProcessor.textPaneHighlighter.removeHighlight(0, keyProcessor.getCaretIndex());
        keyProcessor.textPaneCaretHighlighter.removeHighlight(0, keyProcessor.getCaretIndex());
        TextPaneLetterPainter.paintLetter(mainFrame.textPane, 0, keyProcessor.getCaretIndex(), mainFrame.textColor);
        keyProcessor.setCaretIndexToZero();
        keyProcessor.textPaneText = FileReader.getRandomText();
        mainFrame.textPane.setText(keyProcessor.textPaneText);
        keyProcessor.firstKeyProcessingCall = true;
    }
}
