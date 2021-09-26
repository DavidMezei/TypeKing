package Model;

public class MyText {
    private String text;
    private int textID;

    MyText(String text, int textID) {
        this.text = text;
        this.textID = textID;
    }

    public int getTextID() {
        return textID;
    }

    public String getText() {
        return text;
    }
}
