import api.BookstoreApiClient;
import core.DriverBase;
import io.qameta.allure.Issue;
import models.BooksResponse;
import models.UserResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.TestContext;
import utils.TestUtils;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static core.TestData.General.BASE_URL;
import static org.testng.Assert.assertEquals;


public class BookstoreTest extends DriverBase {
    private BookstoreApiClient apiClient;
    private TestContext testContext;
    private String nameOfUser = "UserName_" + System.currentTimeMillis();
    private String password = "SecurePassword123!";

    @BeforeClass
    public void preconditions() {
        apiClient = new BookstoreApiClient();
        testContext = new TestContext();
        UserResponse user = apiClient.createUser(TestUtils.createUserRequestBody(nameOfUser, password));
        testContext.setUser(user);
        apiClient.generateToken(nameOfUser, password);
        BooksResponse.GetBooksResponse getBooksResponse = apiClient.getBooks();
        List<BooksResponse.BookResponse> booksList = Arrays.stream(getBooksResponse.books).toList();
        testContext.setBooksAddedToUser(booksList);
    }

    @Issue("Not possible to add book to the user")
    @Test(description = "Login user, and verify books")
    public void loginUserAndVerifyBooks() {
        open(BASE_URL);
        homePage.getHomeBanner().shouldBe(visible);
        homePage.navigateToBookStoreApp();

        bookstoreApplicationPage.clickLoginButton();
        loginPage.login(nameOfUser, password);
        userAccountPage.getLogoutButton().shouldBe(appear);

       //this part is not checked because of application issue (Not possible to add book through backend or UI)
        int numberOfBooksOnWebsite = userAccountPage.getNumberOfBooks();
        int numberOfBooksInContext = testContext.getUser().getBooks().length;
        assertEquals(numberOfBooksOnWebsite, numberOfBooksInContext, "Number of books should match");

        //it doesn't work because not possible to add book to the user
        userAccountPage.openBookDetails(1);

        for (BooksResponse.BookResponse book : testContext.getBooksAddedToUser()) {
            assertEquals(bookDetailsPage.getTitle(), book.getTitle());
            assertEquals(bookDetailsPage.getAuthor(), book.getAuthor());
            assertEquals(bookDetailsPage.getPublisher(), book.getPublisher());
            assertEquals(bookDetailsPage.getNumberOfPages(), book.getPages());
        }
    }
}
