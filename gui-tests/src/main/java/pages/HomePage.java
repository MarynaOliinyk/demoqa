package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;


@Getter
public class HomePage extends BasePage {
    private SelenideElement homeBanner = $(".home-banner");

    @Step("Click on Bookstore Application")
    public void navigateToBookStoreApp() {
        $(".top-card:nth-child(6)").scrollTo().click();
    }
}
