package Data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LoadProperties {
    public static Properties testData =
            loadproperties(System.getProperty("user.dir")+"\\src\\test\\java\\Data\\testdata.properties");

    private static Properties loadproperties(String path ) {
        Properties pro= new Properties();
// stream for loading file
        try {
            FileInputStream stream = new FileInputStream(path);
            pro.load(stream);
        } catch (FileNotFoundException e) {
            System.out.print("error occurred : " + e.getMessage());
        } catch (IOException e) {
            System.out.print("error occurred : " + e.getMessage());
        }
return  pro;
    }
}
