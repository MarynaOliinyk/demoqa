package models;

import lombok.Getter;

@Getter
public class BooksResponse {
    public static class GetBooksResponse {
        public BookResponse[] books;
    }

    @Getter
    public static class BookResponse {
        private String isbn;
        private String title;
        private String author;
        private String publisher;
        private String pages;
    }

    @Getter
    public static class AddBookResponse {
        public String isbn;
    }
}
