import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.text.NumberFormat;

public class TextPaneLetterPainter {
    static void paintLetter(JTextPane textPane, int index, Color letterColor){
        StyledDocument styledDocument=textPane.getStyledDocument();
        Style style=textPane.addStyle("",null);
        StyleConstants.setForeground(style,letterColor);
        styledDocument.setCharacterAttributes(index,1,style,true);
    }
}
