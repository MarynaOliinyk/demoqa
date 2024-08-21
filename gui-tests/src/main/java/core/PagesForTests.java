package core;

import pages.BookDetailsPage;
import pages.BookstoreApplicationPage;
import pages.HomePage;
import pages.LoginPage;
import pages.UserAccountPage;

public interface PagesForTests {
    HomePage homePage = new HomePage();
    BookstoreApplicationPage bookstoreApplicationPage = new BookstoreApplicationPage();
    LoginPage loginPage = new LoginPage();
    BookDetailsPage bookDetailsPage = new BookDetailsPage();
    UserAccountPage userAccountPage = new UserAccountPage();
}
