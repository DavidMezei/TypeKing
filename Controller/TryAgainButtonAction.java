package Controller;

import Model.MainModel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class TryAgainButtonAction extends AbstractAction {
    private MainModel mainModel;

    public TryAgainButtonAction(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainModel.setTypingInProgress(false);
        mainModel.setMistakeCount(0);
        mainModel.setCaretIndex(0);
        mainModel.setNewRound(true);
        DefaultSettings.setDefaultSettings();
    }
}
