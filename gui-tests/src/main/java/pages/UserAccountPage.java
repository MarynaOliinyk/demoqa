package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Getter
public class UserAccountPage extends BasePage {
    //Not possible to add any books in the app
    private SelenideElement booksTable = $("#booksTable");
    private SelenideElement logoutButton = $("#submit");

    //It's not possible to get number of book
    @Step("Get number of Books")
    public int getNumberOfBooks() {
        return $$("#books").size();
    }

    @Step("Open book by index")
    public BookDetailsPage openBookDetails(int index) {
        $$("#books").get(index).click();
        return new BookDetailsPage();
    }
}
