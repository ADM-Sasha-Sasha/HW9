import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class User
{
    private static final String path = "src/main/resources/gsonFile.txt";
    private static final String jsonPath = "src/main/resources/jsonFile.json";

    public static void main(String[] args)
    {
        File gsonFile = new File(path);
        File jsonFile = new File(jsonPath);
        checkFile(gsonFile);
        checkFile(jsonFile);
        List<Piply> users = new LinkedList<>();
        readFile(gsonFile, users);
        writeToJson(jsonFile, users);
    }

    private static void writeToJson(File jsonFile, List<Piply> users)
    {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(jsonFile)))
        {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(users);
            bufferedWriter.write(json);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void readFile(File gsonFile, List<Piply> users)
    {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(gsonFile)))
        {
            String datafile = bufferedReader.readLine();
            while (datafile != null)
            {
                String[] columns = datafile.split(" ");
                if (!columns[0].equals("name") && !columns[1].equals("age"))
                {
                    users.add(new Piply(columns[0], Integer.parseInt(columns[1])));
                }
                datafile = bufferedReader.readLine();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
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
