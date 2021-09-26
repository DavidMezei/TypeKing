package View;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.HashMap;
import java.util.Set;

public class TextPaneHighlighter {
    private DefaultHighlighter.DefaultHighlightPainter highlightPainter;
    private Highlighter paneHighlighter;
    private HashMap<Integer, Highlighter.Highlight> highlightsMap;


    public TextPaneHighlighter(JTextComponent textComponent, Color highlightColor) {
        paneHighlighter = textComponent.getHighlighter();
        highlightPainter = new DefaultHighlighter.DefaultHighlightPainter(highlightColor);
        highlightsMap = new HashMap<>();
    }

    public void addHighlight(int startIndex) {
        try {
            paneHighlighter.addHighlight(startIndex, startIndex + 1, highlightPainter);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        Highlighter.Highlight[] highlights = paneHighlighter.getHighlights();
        highlightsMap.put(startIndex, highlights[highlights.length - 1]);
    }

    public void removeHighlight(int startIndex) {
        if (highlightsMap.get(startIndex) != null) {
            paneHighlighter.removeHighlight(highlightsMap.get(startIndex));
            highlightsMap.remove(startIndex);
        }
    }

    public void removeHighlight(int startIndex, int endIndex) {
        while (startIndex <= endIndex) {
            if (highlightsMap.get(startIndex) != null) {
                paneHighlighter.removeHighlight(highlightsMap.get(startIndex));
                highlightsMap.remove(startIndex);
            }
            startIndex++;
        }
    }

    public void removeAllHighlights() {
        Set<Integer> highlightsSet = highlightsMap.keySet();
        for (var highlight : highlightsMap.values()) {
            paneHighlighter.removeHighlight(highlight);
        }
        highlightsMap.clear();
    }

    public boolean isHighlighted(int index) {
        return highlightsMap.containsKey(index);
    }
}
