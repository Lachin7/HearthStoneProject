package resLoader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class ConfigLoader {

    private  static String defaultAddress = "./src/main/resources/config/config.properties";
    private  static ConfigLoader loader = new ConfigLoader();
    public static ConfigLoader getInstance(){ return loader; }

    private FileInputStream fileInputStream;
    private Properties properties;

    private ConfigLoader(){
        properties = new Properties();
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

    public boolean readBoolean (String name){
        return Boolean.parseBoolean(properties.getProperty(name));
    }

    public List<Integer> readIntegerList(String name){
        List<Integer> list = new ArrayList<>();
        for(String string: readStringList(name)){
            list.add(Integer.parseInt(string));
        }
        return list;
    }

    public List<String> readStringList(String name){
        String listString = properties.getProperty(name);
        String[] str = listString.split(",");
        return Arrays.asList(str);
    }

}
