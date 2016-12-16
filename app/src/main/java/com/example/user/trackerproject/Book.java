package com.example.user.trackerproject;

/**
 * Created by user on 16/12/2016.
 */
public class Book {
    int id;
    String title;
    String author;
    RatingType rating;

//Empty Constructor
    public Book() {
    }

//Constructor
    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

//Constructor (with rating - once read)
    public Book(int id, String title, String author, RatingType rating) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.rating = rating;
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public RatingType getRating() {
        return rating;
    }

    public void setRating(RatingType rating) {
        this.rating = rating;
    }

    public String getBookInfo() {
        return String.format(title + " by " + author);
    }
}
