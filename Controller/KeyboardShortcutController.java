package Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class KeyboardShortcutController {
    private HashMap<KeyStroke, Action> actionMap;

    public KeyboardShortcutController(HashMap<KeyStroke, Action> actionMap) {
        this.actionMap = actionMap;
        KeyboardFocusManager keyManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        keyManager.addKeyEventDispatcher(new MyKeyEventDispatcher());

    }

    private class MyKeyEventDispatcher implements KeyEventDispatcher {
        @Override
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            KeyStroke keyStroke = KeyStroke.getKeyStrokeForEvent(keyEvent);
            if (actionMap.containsKey(keyStroke)) {
                ActionEvent actionEvent = new ActionEvent(keyEvent.getSource(), keyEvent.getID(), null);
                actionMap.get(keyStroke).actionPerformed(actionEvent);
                return true;
            }
            return false;
        }
    }
}
