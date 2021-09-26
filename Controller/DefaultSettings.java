package Controller;

import Model.MainModel;
import View.MainFrame;
import View.TextPaneLetterPainter;

import javax.swing.*;
import java.awt.*;

public class DefaultSettings {
    private static MainModel mainModel;
    private static JTextPane textPane;
    private static JTextField textField;

    public DefaultSettings(MainModel mainModel, MainFrame mainFrame) {
        DefaultSettings.mainModel = mainModel;
        DefaultSettings.textPane = mainFrame.getTextPane();
        DefaultSettings.textField = mainFrame.getTextField();
    }

    public static void setDefaultSettings() {
        TextPaneLetterPainter.paintLetter(textPane, 0, mainModel.getTextPaneText().length(), Color.white);
        textField.setText("Just start to type!");
        textField.setForeground(Color.green.darker());
        textField.requestFocus();
        textField.setBackground(Color.darkGray.darker());
        textField.setEnabled(true);
        mainModel.setCaretIndex(0);
    }
}
