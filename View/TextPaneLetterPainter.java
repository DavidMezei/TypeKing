package View;

import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class TextPaneLetterPainter {
    public static void paintLetter(JTextPane textPane, int startIndex, Color letterColor) {
        StyledDocument styledDocument = textPane.getStyledDocument();
        Style style = textPane.addStyle("", null);
        StyleConstants.setForeground(style, letterColor);
        styledDocument.setCharacterAttributes(startIndex, 1, style, true);
    }

    public static void paintLetter(JTextPane textPane, int startIndex, int endIndex, Color letterColor) {
        StyledDocument styledDocument = textPane.getStyledDocument();
        Style style = textPane.addStyle("", null);
        StyleConstants.setForeground(style, letterColor);
        styledDocument.setCharacterAttributes(startIndex, endIndex - startIndex, style, true);
    }
}
