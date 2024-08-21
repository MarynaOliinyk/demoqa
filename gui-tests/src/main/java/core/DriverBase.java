package core;

import api.BookstoreApiClient;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import utils.TestListener;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Slf4j
@Listeners({TestListener.class, ScreenShooter.class})
public class DriverBase extends BookstoreApiClient implements PagesForTests {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeforeClass(description = "Drivers initialization", alwaysRun = true)
    @Parameters({"browserName"})
    public void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

    }

    @AfterClass(description = "Close browser", alwaysRun = true)
    public void logoutAndClearCookies() {
        getWebDriver().manage().deleteAllCookies();
        // Selenide will close the driver automatically after tests
        logger.info("Closing driver");
    }
}
