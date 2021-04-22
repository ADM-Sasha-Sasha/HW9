import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Phone {
    private static final String path = "src/main/resources/file.txt";

    public static void main(String[] args) {
        File file = new File(path);
        checkFile(file);
        readFile(file);
    }

    private static void readFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String phones = reader.readLine();
            while (phones != null) {
                if (checkPhone(phones)) {
                    System.out.println(phones);
                }
                phones = reader.readLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean checkPhone(String phones) {
        String correctType = "\\([0-9]{3}\\) [0-9]{3} - [0-9]{4}";
        String correctTypes = "[0-9]{3} [0-9]{3} - [0-9]{4}";
        return phones.matches(correctType) || phones.matches(correctTypes);
    }

    private static void checkFile(File file) {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
