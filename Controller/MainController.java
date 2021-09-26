package Controller;

import Model.MainModel;
import View.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class MainController {
    private MainModel mainModel;
    private JTextField textField;
    private MainFrame mainFrame;
    private HashMap<Integer, Boolean> mistakeMap;

    public MainController(MainModel mainModel, MainFrame mainFrame) {
        this.textField = mainFrame.getTextField();
        mistakeMap = new HashMap<>();
        this.mainFrame = mainFrame;
        this.mainModel = mainModel;
        textField.addKeyListener(new MyKeyListener());
    }


    public class MyKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent keyEvent) {

        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {
            if (isAppropriateKey(keyEvent)) {
                if (!mainModel.isTypingInProgress())
                    firstKeyProcessingCallSetUp();
                processPressedKey(keyEvent.getKeyChar());
            } else if (keyEvent.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                if (keyEvent.isControlDown())
                    processCtrlBackspace();
                else
                    processBackspace();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    private boolean isAppropriateKey(KeyEvent keyEvent) {
        if (keyEvent.isControlDown() || keyEvent.isAltDown()) return false;
        int pressedKeyCode = keyEvent.getKeyCode();
        if (pressedKeyCode == KeyEvent.VK_SPACE || pressedKeyCode == 222) return true;
        if (pressedKeyCode > 0 && pressedKeyCode < 33) return false;
        return pressedKeyCode < 127;
    }

    private void firstKeyProcessingCallSetUp() {
        setTextFieldForFirstCall();
        mainModel.setTypingInProgress(true);
        mainModel.setNewRound(false);
    }

    private void setTextFieldForFirstCall() {
        textField.setEnabled(true);
        textField.setText("");
        textField.setCaretColor(Color.white);
        textField.setForeground(mainFrame.getTextColor());
    }

    private void processPressedKey(char pressedKeyChar) {
        if (mainModel.getCaretIndex() < mainModel.getTextPaneText().length()) {
            if (pressedKeyChar == mainModel.getTextPaneText().charAt(mainModel.getCaretIndex())) {
                processRightKey();
            } else {
                processWrongKey();
            }
            if (caretIsAtTextEndAndThereIsNoWrongKeyTyped()) {
                endThisRound();
            }
        }
        mainModel.caretMoveForward();
    }

    private void endThisRound() {
        textField.setText("");
        textField.setEnabled(false);
        mainModel.setTypingInProgress(false);
        mistakeMap.clear();
    }

    private void processWrongKey() {
        mainModel.incrementMistakeCount();
        mistakeMap.put(mainModel.getCaretIndex(), Boolean.TRUE);
        textField.setBackground(Color.red);
    }

    private void processRightKey() {
        int caretIndex = mainModel.getCaretIndex() - 1;
        if (caretIndex < 0) caretIndex = 0;
        if (mainModel.getMistakeCount() == 0 && (int) mainModel.getTextPaneText().charAt(caretIndex) == KeyEvent.VK_SPACE) {
            textField.setText(" ");
        }
    }

    private void processBackspace() {
        if (mainModel.getCaretIndex() > 0) {
            if (mainModel.getCaretIndex() <= mainModel.getTextPaneText().length()) {
                if (itWasMistake(mainModel.getCaretIndex() - 1)) {
                    mistakeMap.remove(mainModel.getCaretIndex() - 1);
                    mainModel.decrementMistakeCount();
                    if (mainModel.getMistakeCount() == 0) {
                        textField.setBackground(mainFrame.getTextBackgroundColor());
                    }
                }
                mainModel.caretStepBack();
                if (mainModel.getCaretIndex() != 0 && mainModel.getMistakeCount() == 0 && mainModel.getTextPaneText().charAt(mainModel.getCaretIndex() - 1) == ' ') {
                    int wordStartIndex = findFirstSpaceBackwardsInText(mainModel.getTextPaneText(), mainModel.getCaretIndex() - 1);
                    textField.setText(mainModel.getTextPaneText().substring(wordStartIndex, mainModel.getCaretIndex() + 1));
                }
            } else {
                mainModel.caretStepBack();
            }
        }
    }

    private boolean itWasMistake(int index) {
        return mistakeMap.containsKey(index);
    }


    private boolean caretIsAtTextEndAndThereIsNoWrongKeyTyped() {
        return mainModel.getCaretIndex() == mainModel.getTextPaneText().length() - 1 && mainModel.getMistakeCount() == 0;
    }

    private void processCtrlBackspace() {
        int firstSpaceBackwardsIndex = findFirstSpaceBackwardsInText(textField.getText(), textField.getText().length());
        int charactersToDeleteCount = textField.getText().length() - firstSpaceBackwardsIndex - 1;
        while (charactersToDeleteCount > 0) {
            processBackspace();
            charactersToDeleteCount--;
        }
        if (mainModel.getCaretIndex() == 1) processBackspace();
    }

    private int findFirstSpaceBackwardsInText(String text, int index) {
        if (index == -1 || index == 0) return 0;
        if (index >= text.length()) index = text.length() - 1;
        if ((int) text.charAt(index) == KeyEvent.VK_SPACE) {
            index--;
        }
        while ((int) text.charAt(index) != KeyEvent.VK_SPACE && index > 0) {
            index--;
        }
        return index;
    }
}