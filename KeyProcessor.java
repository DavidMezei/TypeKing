import java.awt.*;
import java.awt.event.KeyEvent;

import static java.lang.Thread.sleep;

public class KeyProcessor {
    private int caretIndex = 0;
    private MainFrame mainFrame;
    private boolean firstKeyProcessingCall = true;
    private TextPaneHighlighter textPaneHighlighter;
    private TextPaneHighlighter textPaneCursorHighlighter;

    KeyProcessor(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        textPaneCursorHighlighter = new TextPaneHighlighter(mainFrame.textPane, new Color(70, 70, 0));
        textPaneHighlighter = new TextPaneHighlighter(mainFrame.textPane, Color.red);
    }

    public void keyProcessing(KeyEvent keyEvent) {
        char keyChar = keyEvent.getKeyChar();
        if (isAppropriateKey(keyChar)) {
            processKey(keyChar);
            if (firstKeyProcessingCall) {
                firstKeyProcessingCall = false;
                setTextFieldForFirstCall();
            }
        } else if ((int) keyChar == KeyEvent.VK_BACK_SPACE) {
            if (keyEvent.isControlDown()) {
                processCtrlBackspace();
            } else {
                processBackspace();
            }
        }
    }

    private void processKey(char keyChar) {
        String textPaneText = mainFrame.textPane.getText();
        if (!caretIsAtTheEndOrForth()) {
            if (keyChar == textPaneText.charAt(caretIndex)) {
                if (textPaneHighlighter.highlightsMap.isEmpty() && (int) keyChar == KeyEvent.VK_SPACE) {
                    mainFrame.textField.setText("");
                }
                TextPaneLetterPainter.paintLetter(mainFrame.textPane, caretIndex, Color.GREEN.darker());
            } else {
                textPaneHighlighter.addHighlight(caretIndex);
                mainFrame.textField.setBackground(Color.red);
            }
            textPaneCursorHighlighter.removeHighlight(caretIndex);
            caretIndex++;
            textPaneCursorHighlighter.addHighlight(caretIndex);
            if (caretIndex == textPaneText.length() && textPaneHighlighter.highlightsMap.isEmpty()) {
                mainFrame.textField.setText("");
                mainFrame.textField.setEditable(false);
            }
        } else {
            caretIndex++;
        }
    }

    private boolean caretIsAtTheEndOrForth() {
        if (caretIndex >= mainFrame.textPane.getText().length()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean caretIsOutOfText() {
        return caretIndex > mainFrame.textPane.getText().length();
    }

    private boolean isAppropriateKey(char keyChar) {
        int keyCode = (int) keyChar;
        if (keyCode == KeyEvent.VK_SPACE || keyCode == 34) return true;
        if (keyCode > 0 && keyCode < 37) return false;
        return keyCode < 123;
    }

    private void processBackspace() {
        if (caretIndex > 0) {
            if (!caretIsOutOfText()) {
                textPaneCursorHighlighter.removeHighlight(caretIndex);
                caretIndex--;
                textPaneHighlighter.removeHighlight(caretIndex);
                textPaneCursorHighlighter.addHighlight(caretIndex);
                TextPaneLetterPainter.paintLetter(mainFrame.textPane, caretIndex, mainFrame.textColor);
                if (textPaneHighlighter.highlightsMap.isEmpty()) {
                    mainFrame.textField.setBackground(mainFrame.textBackgroundColor);
                }
            } else {
                caretIndex--;
            }
        }
    }

    //TODO make it full real with textField
    private void processCtrlBackspace() {

    }

    private void setTextFieldForFirstCall() {
        mainFrame.textField.setText("");
        mainFrame.textField.setForeground(mainFrame.textColor);
    }
}