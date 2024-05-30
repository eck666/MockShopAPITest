package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AllurePropertiesUtils {

    private static final String PROPERTIES_FILE_PATH = "C:\\Users\\emir\\IdeaProjects\\fakeapitest\\src\\test\\resources\\allure.properties";

    public static boolean isDeleteHistoryEnabled() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(PROPERTIES_FILE_PATH));
            String deleteHistory = properties.getProperty("deleteHistory");
            if (deleteHistory != null && deleteHistory.trim().equalsIgnoreCase("true")) {
                ServiceController.deleteAllureHistory();
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
