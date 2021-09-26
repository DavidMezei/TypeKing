package Controller;

import Model.MainModel;
import Model.Observer;
import View.TextPaneHighlighter;
import View.TextPaneLetterPainter;

import javax.swing.*;
import java.awt.*;

public class TextPaneController implements Observer {
    private TextPaneHighlighter textPaneCaretHighlighter;
    private TextPaneHighlighter textPaneMistakeHighlighter;
    private MainModel mainModel;
    private JTextPane textPane;
    private int previousCaretIndex;
    private int recentMistakeCount;
    private int caretRecentMistakeCount;

    public TextPaneController(MainModel mainModel, JTextPane textPane) {
        this.mainModel = mainModel;
        this.textPane = textPane;
        textPaneCaretHighlighter = new TextPaneHighlighter(textPane, new Color(70, 70, 0));
        textPaneMistakeHighlighter = new TextPaneHighlighter(textPane, Color.red);
        textPaneCaretHighlighter.addHighlight(0);
        previousCaretIndex = 0;
        recentMistakeCount = 0;
    }

    @Override
    public void mistakeUpdate() {
        if (recentMistakeCount > mainModel.getMistakeCount()) {
            if (textPaneMistakeHighlighter.isHighlighted(mainModel.getCaretIndex() - 1)) {
                textPaneMistakeHighlighter.removeHighlight(mainModel.getCaretIndex() - 1);
            } else {
                textPaneMistakeHighlighter.addHighlight(mainModel.getCaretIndex() - 1);
            }
        } else {
            if (textPaneMistakeHighlighter.isHighlighted(mainModel.getCaretIndex())) {
                textPaneMistakeHighlighter.removeHighlight(mainModel.getCaretIndex());
            } else {
                textPaneMistakeHighlighter.addHighlight(mainModel.getCaretIndex());
            }
        }
        recentMistakeCount = mainModel.getMistakeCount();
    }

    @Override
    public void caretIndexUpdate() {
        if (caretRecentMistakeCount == mainModel.getMistakeCount() && previousCaretIndex < mainModel.getCaretIndex()) {
            TextPaneLetterPainter.paintLetter(textPane, mainModel.getCaretIndex() - 1, Color.green.darker());
        } else caretRecentMistakeCount = mainModel.getMistakeCount();
        if (previousCaretIndex > mainModel.getCaretIndex())
            TextPaneLetterPainter.paintLetter(textPane, mainModel.getCaretIndex(), Color.white);
        textPaneCaretHighlighter.removeHighlight(previousCaretIndex);
        textPaneCaretHighlighter.addHighlight(mainModel.getCaretIndex());
        previousCaretIndex = mainModel.getCaretIndex();
    }

    @Override
    public void newRoundUpdate() {
        //TODO check if caret is removed properly
        textPaneCaretHighlighter.removeHighlight(previousCaretIndex);
        textPaneMistakeHighlighter.removeAllHighlights();
        textPane.setText(mainModel.getTextPaneText());
        textPaneCaretHighlighter.addHighlight(0);
        caretRecentMistakeCount = 0;
        recentMistakeCount = 0;
        previousCaretIndex = 0;
    }

    @Override
    public void startRoundUpdate() {

    }

    @Override
    public void completionRoundUpdate() {

    }

    @Override
    public void interruptedRoundUpdate() {
        textPaneMistakeHighlighter.removeHighlight(0, mainModel.getCaretIndex());
        textPaneCaretHighlighter.removeHighlight(mainModel.getCaretIndex());
    }
}
