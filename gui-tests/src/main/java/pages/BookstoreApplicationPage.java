package pages;

import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

@Getter
public class BookstoreApplicationPage extends BasePage {
    @Step("Click 'Login' button")
    public void clickLoginButton() {
        $("#login").click();
    }
}
