package Database;

import java.io.*;
import java.util.Properties;

public class BadCount {

    private static Properties properties;
    static File file = new File("bads.properties");

    public static void init() throws IOException {
        properties = new Properties();
        try {
            InputStream in = new FileInputStream(file);
            properties.load(in);
            //in.close();
            //load();
        } catch (IOException e) {
            System.out.println("Settings File '" + file.getName() + "' (" + file.getAbsolutePath() + ") not found\n" +
                    "Please restart!");
            fileNotFoundAction(file);
            System.exit(0);
        }
    }

    public static boolean propExist(String key) {
        return properties.getProperty(key) != null;
    }

    public static String load(String key) {
        return properties.getProperty(key);
    }

    public static void save(String hash, String name) {
        properties.put(hash, name);
        try {
            properties.store(new FileOutputStream(file), "Yamazaki Bad Word Counts");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void delete(String hash) {
        properties.remove(hash);
    }

    private static void fileNotFoundAction(File f){
        try {
            properties.store(new FileOutputStream(f), "Yamazaki Bad Word Counts");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getKeySize() {
        return properties.size();
    }

    public static String getKeyInt(int i) {
        return String.valueOf(properties.get(i));
    }

}
