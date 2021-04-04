import java.awt.*;
import java.awt.event.KeyEvent;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class KeyProcessor {
    private int caretIndex = 0;
    private MainFrame mainFrame;
    private boolean firstKeyProcessingCall = true;
    private TextPaneHighlighter textPaneHighlighter;
    private TextPaneHighlighter textPaneCaretHighlighter;
    private String textPaneText;
    private LabelsThread labelsThread;

    KeyProcessor(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        textPaneCaretHighlighter = new TextPaneHighlighter(mainFrame.textPane, new Color(70, 70, 0));
        textPaneHighlighter = new TextPaneHighlighter(mainFrame.textPane, Color.red);
        textPaneText=mainFrame.textPane.getText();
        labelsThread=new LabelsThread(this,mainFrame.labelWPM,mainFrame.labelElapsedTime);
    }

    public void keyProcessing(KeyEvent keyEvent) {
        char keyChar = keyEvent.getKeyChar();
        if (isAppropriateKey(keyChar)) {
            processKey(keyChar);
            if (firstKeyProcessingCall) {
                firstKeyProcessingCall = false;
                setTextFieldForFirstCall();
                labelsThread.start();
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
            textPaneCaretHighlighter.removeHighlight(caretIndex);
            caretIndex++;
            textPaneCaretHighlighter.addHighlight(caretIndex);
            if (caretIndex == textPaneText.length() && textPaneHighlighter.highlightsMap.isEmpty()) {
                mainFrame.textField.setText("");
                mainFrame.textField.setEditable(false);
                labelsThread.stopLabelsThread();
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
                textPaneCaretHighlighter.removeHighlight(caretIndex);
                caretIndex--;
                textPaneHighlighter.removeHighlight(caretIndex);
                textPaneCaretHighlighter.addHighlight(caretIndex);
                TextPaneLetterPainter.paintLetter(mainFrame.textPane, caretIndex, mainFrame.textColor);
                if (textPaneHighlighter.highlightsMap.isEmpty()) {
                    mainFrame.textField.setBackground(mainFrame.textBackgroundColor);
                }
            } else {
                caretIndex--;
            }
        }
    }

    private void processCtrlBackspace() {
        int deleteStartIndex=findFirstSpaceBackwardsInTextPane(caretIndex-1);
        textPaneCaretHighlighter.removeHighlight(caretIndex);
        textPaneHighlighter.removeHighlight(deleteStartIndex,caretIndex);
        TextPaneLetterPainter.paintLetter(mainFrame.textPane,deleteStartIndex,caretIndex,mainFrame.textColor);
        if (deleteStartIndex!=0) deleteStartIndex++;
        caretIndex=deleteStartIndex;
        textPaneCaretHighlighter.addHighlight(caretIndex);
        if (textPaneHighlighter.highlightsMap.isEmpty()){
            mainFrame.textField.setBackground(mainFrame.textBackgroundColor);
        }
    }

    private int findFirstSpaceBackwardsInTextPane(int index) {
        if (index==-1) return 0;
        if ((int)textPaneText.charAt(index)==KeyEvent.VK_SPACE){
            index--;
        }
        while ((int)textPaneText.charAt(index)!=KeyEvent.VK_SPACE && index>0){
            index--;
        }
        return index;
    }

    private void setTextFieldForFirstCall() {
        mainFrame.textField.setText("");
        mainFrame.textField.setForeground(mainFrame.textColor);
    }

    public int getCaretIndex(){
        return caretIndex;
    }
}