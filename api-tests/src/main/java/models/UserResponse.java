package models;

import lombok.Getter;

@Getter
public class UserResponse {
    private String userID;
    private String username;
    private BooksResponse.BookResponse[] books;

    public UserResponse(String userID, String username, BooksResponse.BookResponse[] books) {
        this.userID = userID;
        this.username = username;
        this.books = books;
    }
}
