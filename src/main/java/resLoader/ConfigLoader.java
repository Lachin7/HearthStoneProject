package resLoader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class ConfigLoader {

    private  String defaultAddress = "./src/main/resources/config/config.properties";
    private  static final ConfigLoader loader = new ConfigLoader();
    public static ConfigLoader getInstance(){ return loader; }

    private FileInputStream fileInputStream;
    private final Properties properties;

    private ConfigLoader(){
        properties = new Properties();
        load();
    }

    public ConfigLoader(String name){
        properties = new Properties();
        defaultAddress = "./src/main/resources/config/"+name+".properties";
        load();
    }

    private void load(){
        try {
            fileInputStream = new FileInputStream(defaultAddress);
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readString(String name){
        return properties.getProperty(name);
    }

    public int readInteger(String name){
        return Integer.parseInt(properties.getProperty(name));
    }

    public List<String> readStringList(String name){
        String listString = properties.getProperty(name);
        String[] str = listString.split(",");
        return Arrays.asList(str);
    }

}
