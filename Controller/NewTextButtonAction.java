package Controller;

import Model.MainModel;
import Model.ReadText;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class NewTextButtonAction extends AbstractAction {
    private MainModel mainModel;
    private ReadText readText;

    public NewTextButtonAction(MainModel mainModel, ReadText readText) {
        this.mainModel = mainModel;
        this.readText = readText;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainModel.setTypingInProgress(false);
        mainModel.setTextPaneMyText(readText.getRandomText());
        mainModel.setMistakeCount(0);
        mainModel.setCaretIndex(0);
        mainModel.setNewRound(true);
        DefaultSettings.setDefaultSettings();
    }
}
