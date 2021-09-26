package Model;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyMistakeObservers() {
        for (var observer : observers) {
            observer.mistakeUpdate();
        }
    }

    public void notifyCaretIndexObservers() {
        for (var observer : observers) {
            observer.caretIndexUpdate();
        }
    }

    public void notifyNewRoundObservers() {
        for (var observer : observers) {
            observer.newRoundUpdate();
        }
    }

    public void notifyCompletionRoundObservers() {
        for (var observer : observers) {
            observer.completionRoundUpdate();
        }
    }

    public void notifyStartRoundObservers() {
        for (var observer : observers) {
            observer.startRoundUpdate();
        }
    }

    public void notifyInterruptedRoundObservers() {
        for (var observer : observers) {
            observer.interruptedRoundUpdate();
        }
    }
}
