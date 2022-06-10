package main.java.core;

import io.qameta.allure.Attachment;
import org.testng.ITestListener;

public class Logger implements ITestListener {

    @Attachment(value = "{0}", type = "text/plain")
    public static void log(String message) {
        System.out.println(message);
    }
}
