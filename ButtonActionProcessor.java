import java.awt.*;
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
        processButton();
    }

    private void processButtonNewText() {
        processButton();
        keyProcessor.textPaneText = FileReader.getRandomText();
    }

    private void processButton(){
        keyProcessor.labelsThread.stopLabelsThread();
        keyProcessor.textPaneHighlighter.removeHighlight(0, keyProcessor.getCaretIndex());
        keyProcessor.textPaneCaretHighlighter.removeHighlight(0, keyProcessor.getCaretIndex());
        TextPaneLetterPainter.paintLetter(mainFrame.textPane, 0, keyProcessor.getCaretIndex(), mainFrame.textColor);
        keyProcessor.setCaretIndexToZero();
        keyProcessor.firstKeyProcessingCall = true;
        mainFrame.labelWPM.setText("0 WPM");
        mainFrame.labelElapsedTime.setText("Time: 0:00");
        mainFrame.textField.setText("Just start to type!");
        mainFrame.textField.setEditable(true);
        mainFrame.textField.setForeground(Color.green.darker());
        mainFrame.textField.setBackground(mainFrame.textBackgroundColor);
        mainFrame.textField.requestFocusInWindow();
    }
}
