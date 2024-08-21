package pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class BookDetailsPage extends BasePage {
    @Step("Get title")
    public String getTitle() {
        return $("#title").getText();
    }

    @Step("Get author")
    public String getAuthor() {
        return $("#author)").getText();
    }

    @Step("Get publisher")
    public String getPublisher() {
        return $("#publisher)").getText();
    }

    @Step("Get number of pages")
    public String getNumberOfPages() {
        return $("#numberOfPages").getText();
    }
}
