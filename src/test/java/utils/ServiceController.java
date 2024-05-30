package utils;

import org.testng.annotations.Listeners;

@Listeners({TestAllureListener.class})
public class ServiceController {

    public <T> T startTest(T page) {
        ThreadLocal<T> tl = new ThreadLocal<>();
        tl.set(page);
        return tl.get();
    }

    public static void openAllureReport() {
        System.out.println("commanda geldi");
        Terminal.runCommand("cmd /c start allure serve -h localhost");
    }

    public static void deleteAllureHistory() {
        System.out.println("history geldi");
        Terminal.runCommand("cmd /c start allure generate --clean --output allure-results");

    }
}
