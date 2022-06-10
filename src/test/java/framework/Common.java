package test.java.framework;

import lombok.Getter;
import lombok.Setter;
import main.java.config.environment.Environment;
import main.java.config.environment.EnvironmentType;
import main.java.config.framework.TestSettings;
import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.xml.XmlSuite;

import java.lang.reflect.Method;

@Setter
@Getter
public class Common implements ITest {

    private ThreadLocal<String> testName = new ThreadLocal<>();

    @BeforeSuite(alwaysRun = true)
    public void initialize() throws Exception {

//        Reading the environment name from command line and setting it
        String environment = System.getProperty("env");

        if (environment == null)
            environment = "DEV";

        EnvironmentType environmentType = EnvironmentType.valueOf(environment.toUpperCase());
        Environment.prepareEnvironment(environmentType);
    }

    /* Setting the parallel thread count for both method and dataproviders
    Any testNG.xml file property can be changed from this method
     */

    @BeforeSuite(alwaysRun = true)
    public void performTestSettings(ITestContext context) throws Exception {
        context.getCurrentXmlTest().getSuite().setParallel(XmlSuite.ParallelMode.METHODS);
        context.getCurrentXmlTest().getSuite().setThreadCount(TestSettings.getTestSettings().getParallelMethodThreadCount());
        context.getCurrentXmlTest().getSuite().setDataProviderThreadCount(TestSettings.getTestSettings().getParallelDataProviderThreads());
    }

    @BeforeMethod
    public void BeforeMethod(Method method, Object[] testData, ITestContext context) {
        if (testData.length > 0) {
            testName.set(testData[0].toString());
            context.setAttribute("testName", testName.get());
        } else
            context.setAttribute("testName", method.getName());
    }

    @Override
    public String getTestName() {
        return testName.get();
    }
}

