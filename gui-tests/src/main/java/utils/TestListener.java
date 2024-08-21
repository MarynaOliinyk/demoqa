package utils;

import core.DriverBase;
import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.logging.Logger;

public class TestListener extends DriverBase implements ITestListener, IAnnotationTransformer {
    private final static Logger logger = Logger.getLogger("TestListener.class");

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod()
                .getConstructorOrMethod()
                .getName();
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        logger.info("Test Method FAILED: " + iTestResult.getName() + " on testsuite " + (iTestResult.getTestContext()
                .getName()).toUpperCase() + " " + iTestResult.getThrowable()
                .getCause());
        saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");
    }


    private static String saveTextLog(String msg) {
        return msg;
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        logger.info("Test Method SKIPPED: " + iTestResult.getName() + " on testsuite " + (iTestResult.getTestContext()
                .getName()).toUpperCase() + " " + iTestResult.getThrowable().getMessage());
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        logger.info("TestSuite started: " + iTestContext.getName());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        logger.info("TestSuite finished: " + iTestContext.getName());
    }
}
