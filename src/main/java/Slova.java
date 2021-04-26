import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Slova {
    private static final String path = "src/main/resources/words.txt";

    public static void main(String[] args)
    {
        File file = new File(path);
        checkFile(file);
        readFile(file);
    }

    private static void readFile(File file) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String slowa = bufferedReader.readLine();
            while (slowa != null) {
                stringBuilder.append(slowa.trim());
                stringBuilder.append(" ");
                slowa = bufferedReader.readLine();
            }
            String slowaBillder = stringBuilder.toString();
            sortirovka(createArrayFromFile(slowaBillder));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        private static void sortirovka (String[] slowaArray)
        {
            List<String> listSlow = Arrays.asList(slowaArray);
            Map<String, Integer> count = new HashMap<String, Integer>();
            for (String repeats : listSlow)
            {
                count.put(repeats, Collections.frequency(listSlow, repeats));
            }
            count.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .forEach(System.out::println);
        }
        private static String[] createArrayFromFile (String slowaBillder)
        {
            String[] slowaArray = slowaBillder.split(" ");
            return slowaArray;
        }

    private static void checkFile(File file)
    {
        if (!file.exists())
        {
            file.getParentFile().mkdirs();
            try
            {
                file.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
