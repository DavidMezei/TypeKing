package Controller;

import Model.MainModel;
import Model.Observer;

import javax.swing.*;
import java.time.LocalTime;

public class LabelElapsedTimeWPMController extends Thread implements Observer {
    private boolean stopThread = false;
    private JLabel labelWPM;
    private JLabel labelElapsedTime;
    private MainModel mainModel;
    private LabelElapsedTimeWPMController labelElapsedTimeWPMController;

    public LabelElapsedTimeWPMController(MainModel mainModel, JLabel labelWPM, JLabel labelElapsedTime) {
        this.mainModel = mainModel;
        this.labelWPM = labelWPM;
        this.labelElapsedTime = labelElapsedTime;
    }

    public void stopLabelsThread() {
        stopThread = true;
    }

    @Override
    public void run() {
        LocalTime startThreadTime = LocalTime.now();
        int elapsedTimeInSeconds;
        int startThreadTimeInSeconds = startThreadTime.getMinute() * 60 + startThreadTime.getSecond();
        ThreadSleep.sleep(1000);
        labelElapsedTime.setText("Time: 0:01");
        ThreadSleep.sleep(1000);
        while (!stopThread) {
            LocalTime currentTime = LocalTime.now();
            int currentTimeInSeconds = currentTime.getMinute() * 60 + currentTime.getSecond();
            if (currentTimeInSeconds > startThreadTimeInSeconds)
                elapsedTimeInSeconds = currentTimeInSeconds - startThreadTimeInSeconds;
            else
                elapsedTimeInSeconds = 3600 - startThreadTimeInSeconds + currentTimeInSeconds; //if startTime=13:59 and currentTime=14:01
            double elapsedTimeInMinutes = elapsedTimeInSeconds / 60.0;
            double typedWords = mainModel.getCaretIndex() / 5.0;
            int WPM = (int) (typedWords / elapsedTimeInMinutes);
            updateElapsedTimeLabel(elapsedTimeInSeconds);
            updateWPMLabel(WPM);
            ThreadSleep.sleep(500);
            ThreadSleep.sleep(500);
        }
    }

    private void updateWPMLabel(int WPM) {
        labelWPM.setText(WPM + " WPM");
    }

    private void updateElapsedTimeLabel(int elapsedTimeInSeconds) {
        int elapsedTimeJustMinutes = elapsedTimeInSeconds / 60;
        int elapsedTimeJustSeconds = elapsedTimeInSeconds % 60;
        if (elapsedTimeJustSeconds > 9)
            labelElapsedTime.setText("Time: " + elapsedTimeJustMinutes + ":" + elapsedTimeJustSeconds);
        else
            labelElapsedTime.setText("Time: " + elapsedTimeJustMinutes + ":0" + elapsedTimeJustSeconds);
    }

    @Override
    public void mistakeUpdate() {

    }

    @Override
    public void caretIndexUpdate() {

    }

    @Override
    public void newRoundUpdate() {
        labelElapsedTime.setText("Time: 0:00");
        labelWPM.setText("0 WPM");
    }

    @Override
    public void startRoundUpdate() {
        labelElapsedTimeWPMController = new LabelElapsedTimeWPMController(mainModel, labelWPM, labelElapsedTime);
        labelElapsedTimeWPMController.start();
    }

    @Override
    public void completionRoundUpdate() {
        labelElapsedTimeWPMController.stopLabelsThread();
    }

    @Override
    public void interruptedRoundUpdate() {
        if (labelElapsedTimeWPMController != null) {
            labelElapsedTimeWPMController.stopLabelsThread();
        }
    }

    private static class ThreadSleep {
        private static void sleep(int millis) {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
            }
        }
    }
}
