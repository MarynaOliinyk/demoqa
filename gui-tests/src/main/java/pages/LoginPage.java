package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

@Getter
public class LoginPage extends BasePage {
    private SelenideElement usernameInput = $("#userName");
    private SelenideElement passwordInput = $("#password");
    private SelenideElement loginButton = $("#login");

    @Step("Login with a given username {userName} and password {password}")
    public void login(String userName, String password) {
        usernameInput.sendKeys(userName);
        passwordInput.sendKeys(password);
        waitCallbacksComplete(50);
        blurElement(passwordInput);
        loginButton.click();
    }
}
