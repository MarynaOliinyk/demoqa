package utils;

import models.BooksResponse;
import models.UserResponse;

import java.util.ArrayList;
import java.util.List;

public class TestContext {
    private UserResponse user;
    private  List<BooksResponse.BookResponse> booksAddedToUser = new ArrayList<>();

    public void setUser(UserResponse user) {
        this.user = user;
    }

    public UserResponse getUser() {
        return user;
    }


    public List<BooksResponse.BookResponse> getBooksAddedToUser() {
        return booksAddedToUser;
    }
    public void setBooksAddedToUser(List<BooksResponse.BookResponse> booksAddedToUser) {
        this.booksAddedToUser = booksAddedToUser;
    }
}
