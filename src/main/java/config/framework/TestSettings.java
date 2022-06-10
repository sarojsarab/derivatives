package main.java.config.framework;

import lombok.Getter;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

@Getter
public class TestSettings {

    private static TestSettings testSettings = null;
    private int parallelMethodThreadCount;
    private int parallelDataProviderThreads;
    private int failedTestRetryCount;
    private TestSettings() throws Exception {

        String path = TestSettings.class.getClassLoader().getResource("configurations").getPath();
        Properties parallelTestProp = new Properties();
        InputStream input = new FileInputStream(path + File.separator + "TestSettings.properties");
        parallelTestProp.load(input);

        try {
//            looking for external input for failed test retry from command line
            failedTestRetryCount = Integer.parseInt(System.getProperty("retryTestCount"));
        } catch (NumberFormatException nfe) {
//            no external input, reading it from TestSetting property file
            failedTestRetryCount = Integer.parseInt(parallelTestProp.getProperty("failedTestRetryCount"));
        }

        try {
//            looking for external input for parallel methods run count from command line
            parallelMethodThreadCount = Integer.parseInt(System.getProperty("parallelMethodThreadCount"));
        } catch (NumberFormatException nfe) {
//            no external input, reading it from TestSetting property file
            parallelMethodThreadCount = Integer.parseInt(parallelTestProp.getProperty("parallelMethodThreadCount"));
        }

        try {
//            looking for external input for parallel data provider tests run count from command line
            parallelDataProviderThreads = Integer.parseInt(System.getProperty("parallelDataProviderThreads"));
        } catch (NumberFormatException nfe) {
//            no external input, reading it from TestSetting property file
            parallelDataProviderThreads = Integer.parseInt(parallelTestProp.getProperty("dataProviderParallelThreads"));
        }
        input.close();
    }

    //    This method will provide an instance of GlobalSettings class
    public static synchronized TestSettings getTestSettings() throws Exception {
        if (testSettings == null) {
            testSettings = new TestSettings();
        }
        return testSettings;
    }
}
