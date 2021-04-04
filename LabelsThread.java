import javax.swing.*;
import java.time.LocalTime;

public class LabelsThread extends Thread {
    static boolean stopThread = false;
    private JLabel labelWPM;
    private JLabel labelElapsedTime;
    private KeyProcessor keyProcessor;

    LabelsThread(KeyProcessor keyProcessor, JLabel labelWPM, JLabel labelElapsedTime) {
        this.keyProcessor = keyProcessor;
        this.labelWPM = labelWPM;
        this.labelElapsedTime = labelElapsedTime;
    }

    public void stopLabelsThread() {
        stopThread = true;
    }

    @Override
    public void run() {
        LocalTime startThreadTime = LocalTime.now();
        ThreadSleep.sleep(1000);
        labelElapsedTime.setText("Time: 0:01");
        ThreadSleep.sleep(1000);

        int startThreadTimeInSeconds = startThreadTime.getMinute() * 60 + startThreadTime.getSecond();
        while (!stopThread) {
            LocalTime currentTime = LocalTime.now();
            int currentTimeInSeconds = currentTime.getMinute() * 60 + currentTime.getSecond();
            if (currentTimeInSeconds < startThreadTimeInSeconds)
                startThreadTimeInSeconds = -(60 - startThreadTimeInSeconds);
            int elapsedTimeInSeconds = currentTimeInSeconds - startThreadTimeInSeconds;
            double elapsedTimeInMinutes = elapsedTimeInSeconds / 60.0;
            double typedWords = keyProcessor.getCaretIndex() / 5.0;
            int WPM = (int) (typedWords / elapsedTimeInMinutes);
            updateElapsedTimeLabel(elapsedTimeInSeconds);
            ThreadSleep.sleep(500);
            updateWPMLabel(WPM);
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
}
