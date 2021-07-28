import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class TextFileReader {
    private static int currentTextIndex = -1;
    private static boolean firstCall = true;
    private static Vector<String> textList = new Vector<String>();
    private final String fileName = ".\\src\\texts.txt";

    public TextFileReader() {
        String text = "";
        String nextLine;
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                nextLine = scanner.nextLine();
                if (nextLine.equals("")) {
                    textList.add(text);
                    text = "";
                }
                text += nextLine;
            }
            textList.add(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String getRandomText() {
        Random random = new Random();
        if (firstCall) {
            firstCall = false;
            new TextFileReader();
        }
        int randomIndex;
        do {
            randomIndex = random.nextInt(textList.size());
        } while (randomIndex == currentTextIndex);
        currentTextIndex =randomIndex;

        return textList.get(randomIndex);
    }

    public static int getCurrentTextIndex() {
        return currentTextIndex;
    }
}
