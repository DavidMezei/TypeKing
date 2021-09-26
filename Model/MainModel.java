package Model;

public class MainModel extends Observable {
    private int caretIndex;
    private int mistakeCount;
    private MyText textPaneMyText;
    private boolean typingInProgress;
    private boolean newRound;

    public MainModel() {
        caretIndex = 0;
        mistakeCount = 0;
        typingInProgress = false;
        newRound = true;
    }

    private boolean isRoundCompleted() {
        return (caretIndex + 1 == textPaneMyText.getText().length() && mistakeCount == 0);
    }

    public void setNewRound(boolean newRound) {
        this.newRound = newRound;
        if (newRound) {
            notifyNewRoundObservers();
        }
    }

    public MyText getTextPaneMyText() {
        return textPaneMyText;
    }

    public boolean isNewRound() {
        return newRound;
    }

    public boolean isTypingInProgress() {
        return typingInProgress;
    }

    public void setTypingInProgress(boolean typingInProgress) {
        this.typingInProgress = typingInProgress;
        if (typingInProgress) {
            notifyStartRoundObservers();
        } else {
            if (isRoundCompleted())
                notifyCompletionRoundObservers();
            else
                notifyInterruptedRoundObservers();
        }
    }

    public String getTextPaneText() {
        return textPaneMyText.getText();
    }

    public void setTextPaneMyText(MyText myText) {
        this.textPaneMyText = myText;
    }

    public int getMistakeCount() {
        return mistakeCount;
    }

    public void incrementMistakeCount() {
        mistakeCount++;
        notifyMistakeObservers();
    }

    public void decrementMistakeCount() {
        if (mistakeCount > 0) {
            mistakeCount--;
            notifyMistakeObservers();
        }
    }

    public void setMistakeCount(int mistakeCount) {
        this.mistakeCount = mistakeCount;
    }

    public void caretStepBack() {
        if (caretIndex > 0) {
            caretIndex--;
            notifyCaretIndexObservers();
        }
    }

    public void caretMoveForward() {
        caretIndex++;
        notifyCaretIndexObservers();
    }

    public int getCaretIndex() {
        return caretIndex;
    }

    public void setCaretIndex(int caretIndex) {
        this.caretIndex = caretIndex;
    }
}
