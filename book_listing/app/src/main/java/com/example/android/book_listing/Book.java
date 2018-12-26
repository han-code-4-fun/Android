package com.example.android.book_listing;

public class Book {
    private String bookName;


    private String author;

    private String description;

    private String url;

    public Book(String bookName, String author, String description,String url)
    {
        this.bookName = bookName;
        this.author =author;
        this.description = description;
        this.url = url;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }
}
