import api.BookstoreApiClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Story;
import models.TokenResponse;
import models.UserResponse;
import models.BooksResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.TestContext;
import utils.TestUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import static utils.RandStings.appendRandom;

@Epic(value = "Book Store Application")
@Feature(value = "Profile Page")
@Story(value = "Authorization and verify Bookstore")
public class BookstoreApiServiceTests {
    private BookstoreApiClient apiClient;
    private TestContext testContext;
    private String nameOfUser = appendRandom("UserName_", 20);
    private String password = "SecurePassword123!";

    @BeforeClass
    public void setup() {
        apiClient = new BookstoreApiClient();
        testContext = new TestContext();
    }

    @Test(priority = -2)
    public void createUserTest() {
        UserResponse user = apiClient.createUser(TestUtils.createUserRequestBody(nameOfUser, password));
        assertNotNull(user.getUserID());
        assertEquals(nameOfUser, user.getUsername());
        testContext.setUser(new UserResponse(user.getUserID(), user.getUsername(), user.getBooks()));
    }

    @Test(priority = -1, dependsOnMethods = "createUserTest")
    public void generateTokenTest() {
        TokenResponse tokenResponse = apiClient.generateToken(nameOfUser, password);
        assertEquals("Success", tokenResponse.getStatus());
        assertNotNull(tokenResponse.getToken(), "Token should not be null");
    }

    @Test
    public void getBooksList() {
        BooksResponse.GetBooksResponse getBooksResponse = apiClient.getBooks();
        List<BooksResponse.BookResponse> booksList = Arrays.stream(getBooksResponse.books).toList();

        assertNotNull(getBooksResponse.books, "Books list should not be null");
        Assert.assertTrue(booksList.size() > 2);
    }

    @Test(dataProvider = "bookFilters")
    public void filterBooksTest(String filterValue, String filterType) {
        BooksResponse.GetBooksResponse getBooksResponse = apiClient.getBooks();
        List<BooksResponse.BookResponse> filteredBooks = applyFilter(getBooksResponse, filterValue, filterType);
        assertTrue("No books found for filter: " + filterType + " = " + filterValue, filteredBooks.size() > 0);
    }

    @Issue("401 status code - \"User not authorized!\"")
    @Test(dependsOnMethods = "createUserTest", priority = 3)
    public void addBookToUser() {
        String isbn = "9781593275846"; // example ISBN

        // Create request body
        JsonObject requestBody = new JsonObject();
        JsonArray collectionOfIsbns = new JsonArray();
        collectionOfIsbns.add(isbn);
        requestBody.addProperty("userId", nameOfUser);
        requestBody.add("collectionOfIsbns", collectionOfIsbns);

        // Add book to user
        BooksResponse.AddBookResponse addBookResponse = apiClient.addBook(requestBody);
        Assert.assertNotNull(addBookResponse, "Response should not be null");
        Assert.assertEquals(addBookResponse.isbn, isbn, "ISBN should match the added book's ISBN");

        BooksResponse.GetBooksResponse getBooksResponse = apiClient.getBooks();
        Assert.assertTrue(getBooksResponse.books.length > 0, "User should have books in their collection");
        List<BooksResponse.BookResponse> booksAddedToUser = Arrays.stream(getBooksResponse.books)
                .filter(book -> Arrays.stream(testContext.getUser().getBooks())
                        .anyMatch(userBook -> userBook.getIsbn().equals(book.getIsbn())))
                .toList();
        assertEquals(testContext.getBooksAddedToUser().size(), booksAddedToUser.size());

        // save book in the context
        testContext.getBooksAddedToUser();
    }

    @DataProvider(name = "bookFilters")
    public Object[][] bookFilters() {
        return new Object[][]{
                {"O'Reilly Media", "publisher"},
                {"Axel Rauschmayer", "author"}
        };
    }

    private List<BooksResponse.BookResponse> applyFilter(BooksResponse.GetBooksResponse booksResponse, String filterValue, String filterType) {
        switch (filterType) {
            case "publisher":
                return Arrays.stream(booksResponse.books)
                        .filter(book -> book.getPublisher().equals(filterValue))
                        .collect(Collectors.toList());
            case "author":
                return Arrays.stream(booksResponse.books)
                        .filter(book -> book.getAuthor().equals(filterValue))
                        .collect(Collectors.toList());
            default:
                throw new IllegalArgumentException("Unknown filter type: " + filterType);
        }
    }
}