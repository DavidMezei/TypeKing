import java.awt.*;
import java.awt.event.KeyEvent;

public class KeyProcessor {
    private int caretIndex = 0;
    private MainFrame mainFrame;
    private boolean firstKeyProcessingCall = true;
    private TextPaneHighlighter textPaneRedHighlighter;
    private TextPaneHighlighter textPaneCaretHighlighter;
    private String textPaneText;
    private LabelsThread labelsThread;
    private LabelAccuracyProcessor labelAccuracyProcessor;

    KeyProcessor(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        textPaneCaretHighlighter = new TextPaneHighlighter(mainFrame.getTextPane(), new Color(70, 70, 0));
        textPaneRedHighlighter = new TextPaneHighlighter(mainFrame.getTextPane(), Color.red);
        labelAccuracyProcessor = new LabelAccuracyProcessor(mainFrame.getLabelAccuracyPercentage());
        labelsThread = new LabelsThread(this, mainFrame.getLabelWPM(), mainFrame.getLabelElapsedTime());
    }

    public void keyProcessing(KeyEvent keyEvent) {
        char pressedKeyChar = keyEvent.getKeyChar();
        ifButtonShortcutThenExecuteIt(keyEvent);
        if (isAppropriateKey(pressedKeyChar)) {
            if (firstKeyProcessingCall)
                firstKeyProcessingCallSetUp();
            processPressedKey(pressedKeyChar);
        } else if ((int) pressedKeyChar == KeyEvent.VK_BACK_SPACE) {
            if (keyEvent.isControlDown())
                processCtrlBackspace();
            else
                processBackspace();
        }
    }

    private boolean isAppropriateKey(char pressedKeyChar) {
        int keyCode = (int) pressedKeyChar;
        if (keyCode == KeyEvent.VK_SPACE || keyCode == 34) return true;
        if (keyCode > 0 && keyCode < 37) return false;
        return keyCode < 123;
    }

    private void firstKeyProcessingCallSetUp() {
        textPaneText = mainFrame.getTextPane().getText();
        firstKeyProcessingCall = false;
        mainFrame.getTextField().setEditable(true);
        setTextFieldForFirstCall();
        labelsThread = new LabelsThread(this, mainFrame.getLabelWPM(), mainFrame.getLabelElapsedTime());
        labelsThread.start();
        labelAccuracyProcessor.setTextLength(textPaneText.length());
        mainFrame.getLabelAccuracyPercentage().setForeground(Color.green);
    }

    private void ifButtonShortcutThenExecuteIt(KeyEvent keyEvent) {
        int pressedKeyCode = keyEvent.getKeyCode();
        if (keyEvent.isControlDown()) {
            if (pressedKeyCode == KeyEvent.VK_T)
                mainFrame.getButtonTryAgain().doClick();
            else if (pressedKeyCode == KeyEvent.VK_N)
                mainFrame.getButtonNewText().doClick();
        }
    }

    private void processPressedKey(char pressedKeyChar) {
        if (caretIndex < textPaneText.length()) {
            if (pressedKeyChar == textPaneText.charAt(caretIndex)) {
                processRightKey(pressedKeyChar);
            } else {
                processWrongKey();
            }
            textPaneCaretHighlighter.removeHighlight(caretIndex);
            textPaneCaretHighlighter.addHighlight(caretIndex + 1);
            if (caretIsAtTextEndAndThereIsNoWrongKeyTyped()) {
                endThisRound();
           }
        }
        caretIndex++;
    }

    private void endThisRound(){
        mainFrame.getTextField().setText("");
        mainFrame.getTextField().setEditable(false);
        labelsThread.stopLabelsThread();
    }

    private boolean caretIsAtTextEndAndThereIsNoWrongKeyTyped(){
        return caretIndex == textPaneText.length() - 1 && textPaneRedHighlighter.getHighlightsMap().isEmpty();
    }

    private void processWrongKey() {
        textPaneRedHighlighter.addHighlight(caretIndex);
        mainFrame.getTextField().setBackground(Color.red);
        labelAccuracyProcessor.updateLabelAccuracy();
    }

    private void processRightKey(char pressedKeyChar) {
        TextPaneLetterPainter.paintLetter(mainFrame.getTextPane(), caretIndex, Color.GREEN.darker());
        if (textPaneRedHighlighter.getHighlightsMap().isEmpty() && (int) pressedKeyChar == KeyEvent.VK_SPACE) {
            mainFrame.getTextField().setText("");
        }
    }

    private void processBackspace() {
        if (caretIndex > 0) {
            if (caretIndex <= textPaneText.length()) {
                textPaneCaretHighlighter.removeHighlight(caretIndex);
                caretIndex--;
                textPaneRedHighlighter.removeHighlight(caretIndex);
                textPaneCaretHighlighter.addHighlight(caretIndex);
                TextPaneLetterPainter.paintLetter(mainFrame.getTextPane(), caretIndex, mainFrame.getTextColor());
                if (textPaneRedHighlighter.getHighlightsMap().isEmpty()) {
                    mainFrame.getTextField().setBackground(mainFrame.getTextBackgroundColor());
                }
            } else {
                caretIndex--;
            }
        }
    }

    private void processCtrlBackspace() {
        int firstSpaceBackwardsIndex = findFirstSpaceBackwardsInTextPane(caretIndex - 1);
        textPaneCaretHighlighter.removeHighlight(caretIndex);
        textPaneRedHighlighter.removeHighlight(firstSpaceBackwardsIndex, caretIndex);
        TextPaneLetterPainter.paintLetter(mainFrame.getTextPane(), firstSpaceBackwardsIndex, caretIndex, mainFrame.getTextColor());
        if (firstSpaceBackwardsIndex != 0) firstSpaceBackwardsIndex++;
        caretIndex = firstSpaceBackwardsIndex;
        textPaneCaretHighlighter.addHighlight(caretIndex);
        if (textPaneRedHighlighter.getHighlightsMap().isEmpty()) {
            mainFrame.getTextField().setBackground(mainFrame.getTextBackgroundColor());
        }
    }

    private int findFirstSpaceBackwardsInTextPane(int index) {
        if (index == -1) return 0;
        if (index >= textPaneText.length()) index = textPaneText.length() - 1;
        if ((int) textPaneText.charAt(index) == KeyEvent.VK_SPACE) {
            index--;
        }
        while ((int) textPaneText.charAt(index) != KeyEvent.VK_SPACE && index > 0) {
            index--;
        }
        return index;
    }

    private void setTextFieldForFirstCall() {
        mainFrame.getTextField().setText("");
        mainFrame.getTextField().setForeground(mainFrame.getTextColor());
    }

    public int getCaretIndex() {
        return caretIndex;
    }

    public void setCaretIndexToZero() {
        caretIndex = 0;
    }

    public void setFirstKeyProcessingCallToTrue() {
        firstKeyProcessingCall = true;
    }

    public TextPaneHighlighter getTextPaneRedHighlighter() {
        return textPaneRedHighlighter;
    }

    public TextPaneHighlighter getTextPaneCaretHighlighter() {
        return textPaneCaretHighlighter;
    }

    public String getTextPaneText() {
        return textPaneText;
    }

    public LabelsThread getLabelsThread() {
        return labelsThread;
    }

    public void setTextPaneText(String textPaneText) {
        this.textPaneText = textPaneText;
    }

    public LabelAccuracyProcessor getLabelAccuracyProcessor() {
        return labelAccuracyProcessor;
    }
}