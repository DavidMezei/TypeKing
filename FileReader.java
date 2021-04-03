import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class FileReader {
    private static Vector<String> textList = new Vector<String>();
    private final String fileName = "C:\\Users\\mezei\\Desktop\\Java Programs\\TypeKing\\src\\texts.txt";

    private FileReader() {
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
        FileReader reader=new FileReader();
        int randomIndex = random.nextInt(textList.size());
        return textList.get(randomIndex);
    }
}
