import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class TextPaneHighlighter {
    private DefaultHighlighter.DefaultHighlightPainter highlightPainter;
    private Highlighter paneHighlighter;
    private HashMap<Integer,Highlighter.Highlight> highlightsMap;


    TextPaneHighlighter(JTextPane textPane, Color highlightColor){
        paneHighlighter=textPane.getHighlighter();
        highlightPainter=new DefaultHighlighter.DefaultHighlightPainter(highlightColor);
        highlightsMap = new HashMap<>();
    }

    void addHighlight(int startIndex){
        try {
            paneHighlighter.addHighlight(startIndex,startIndex+1,highlightPainter);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        Highlighter.Highlight[] highlights=paneHighlighter.getHighlights();
        highlightsMap.put(startIndex,highlights[highlights.length-1]);
    }

    void removeHighlight(int startIndex){
       if (highlightsMap.get(startIndex) != null) {
           paneHighlighter.removeHighlight(highlightsMap.get(startIndex));
           highlightsMap.remove(startIndex);
       }
    }

    void removeHighlight(int startIndex, int endIndex){
       while (startIndex<=endIndex){
           if (highlightsMap.get(startIndex) != null) {
               paneHighlighter.removeHighlight(highlightsMap.get(startIndex));
               highlightsMap.remove(startIndex);
           }
           startIndex++;
       }
    }

    public HashMap<Integer, Highlighter.Highlight> getHighlightsMap() {
        return highlightsMap;
    }
}
