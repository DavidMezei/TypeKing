import Controller.*;
import Model.*;
import View.MainFrame;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class AppStarter {

    public void start(){
        MainFrame mainFrame=new MainFrame();
        MainModel mainModel=new MainModel();
        HistoryFileReaderWriter historyReaderWriter=new HistoryFileReaderWriter();
        ReadText readText=new ReadTextFromFile();
        new DefaultSettings(mainModel,mainFrame);
        new KeyboardShortcutController(getActionMap(mainModel,readText));
        mainFrame.getButtonNewText().addActionListener(new NewTextButtonAction(mainModel,readText));
        mainFrame.getButtonTryAgain().addActionListener(new TryAgainButtonAction(mainModel));
        mainModel.setTextPaneMyText(readText.getRandomText());
        mainFrame.getTextPane().setText(mainModel.getTextPaneText());
        new MainController(mainModel,mainFrame);
        new AppClosingController(historyReaderWriter,mainFrame);
        HistoryPanelController historyPanelController=new HistoryPanelController(mainModel,historyReaderWriter,mainFrame);
        LabelAccuracyController labelAccuracyController=new LabelAccuracyController(mainModel,mainFrame.getLabelAccuracyPercentage());
        LabelElapsedTimeWPMController labelElapsedTimeWPMController=new LabelElapsedTimeWPMController(mainModel,mainFrame.getLabelWPM(),mainFrame.getLabelElapsedTime());
        TextPaneController textPaneController =new TextPaneController(mainModel, mainFrame.getTextPane());
        historyPanelController.uploadLoadedHistoriesToPanel();
        mainModel.addObserver(historyPanelController);
        mainModel.addObserver(labelAccuracyController);
        mainModel.addObserver(labelElapsedTimeWPMController);
        mainModel.addObserver(textPaneController);
    }

    private HashMap<KeyStroke,Action> getActionMap(MainModel mainModel, ReadText readText){
       HashMap<KeyStroke, Action> actionMap = new HashMap<KeyStroke, Action>();
       actionMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK),new NewTextButtonAction(mainModel,readText));
       actionMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_T,InputEvent.CTRL_DOWN_MASK), new TryAgainButtonAction(mainModel));

       return actionMap;
    }

}
