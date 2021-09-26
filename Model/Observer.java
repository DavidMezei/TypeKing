package Model;

public interface Observer {
    void mistakeUpdate();

    void caretIndexUpdate();

    void newRoundUpdate();

    void startRoundUpdate();

    void completionRoundUpdate();

    void interruptedRoundUpdate();
}
