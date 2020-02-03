import java.io.InputStream;
import java.io.FileInputStream;
import java.util.Properties;
import java.io.FileNotFoundException;

public class PropertiesFile {
    static Properties prop = new Properties();

    public static String getNodeProperty(String propertyName){
        try {
            InputStream input = new FileInputStream("C:\\Users\\GunavaranBrihadiswar\\IdeaProjects\\DistributedFileSharing\\resources\\nodeConfig");
            prop.load(input);
            return prop.getProperty(propertyName);
        } catch (Exception e){
            e.printStackTrace();
        }

        return "Error";
    }


}
