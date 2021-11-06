package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class ReadTextFromFile implements ReadText {
    private boolean firstCall = true;
    private Vector<String> textList = new Vector<String>();
    private String fileName = "./texts.txt";

    public ReadTextFromFile(String fileNameFullPath) {
        this.fileName = fileNameFullPath;
        readTextsFromFile();
    }

    public ReadTextFromFile() {
        readTextsFromFile();
    }

    private void readTextsFromFile() {
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
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public MyText getRandomText() {
        Random random = new Random();
        if (firstCall) {
            firstCall = false;
            new ReadTextFromFile();
        }
        int randomIndex;
        randomIndex = random.nextInt(textList.size());

        return new MyText(textList.get(randomIndex), randomIndex);
    }

}
