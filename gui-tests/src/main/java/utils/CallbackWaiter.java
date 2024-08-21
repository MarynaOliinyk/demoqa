package utils;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.StaleElementReferenceException;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.sleep;

@Slf4j
public class CallbackWaiter {
    public static boolean waitCallbacksComplete(long ms) {
        Selenide.sleep(ms);
        return waitCallbacksComplete();
    }

    @Step("Wait till all callbacks are complete")
    public static boolean waitCallbacksComplete() {
        String js = "return bps.isReqInProgress";
        String isBpsExist = "return typeof bps !== \"undefined\"";
        Boolean isInProgress = null;
        try {
            if (!(Boolean) executeJavaScript(isBpsExist))
                sleep(2000);
            else
                isInProgress = executeJavaScript(js);
        } catch (JavascriptException | NullPointerException exh) {
            log.info("No callbacks found. The page is broken");
        }
        if (isInProgress == null) return true;
        log.info("Callbacks initial state: {}", isInProgress);
        int i = 20;
        while (null != isInProgress && isInProgress) {
            isInProgress = executeJavaScript(js);
            log.info("Is callbacks still present: {}", isInProgress);
            sleep(500);
            if (i-- < 0) break;
        }
        return true;
    }


    @Step("Blur (Unfocus) given element - {element}")
    public void blurElement(SelenideElement element) {
        element.shouldBe(visible);
        waitCallbacksComplete();
        try {
            executeJavaScript("arguments[0].blur()", element);
        } catch (StaleElementReferenceException ex) {
            ex.printStackTrace();
            blurElement(element);
        }
        waitCallbacksComplete();
    }
}
