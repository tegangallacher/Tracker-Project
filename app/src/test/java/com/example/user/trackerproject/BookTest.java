package com.example.user.trackerproject;

import org.junit.Before;
import org.junit.Test;
import static junit.framework.Assert.*;

/**
 * Created by user on 20/12/2016.
 */
public class BookTest {
    Book book;

    @Before
    public void before() {
        book = new Book("The Girls", "Emma Cline", "4.0");
    }

    @Test
    public void testBookHasTitle() {
        assertEquals("The Girls", book.getTitle());
    }

    @Test
    public void testBookHasAuthor() {
        assertEquals("Emma Cline", book.getAuthor());
    }

    @Test
    public void testBookHasRating() {
        assertEquals("4.0", book.getRating());
    }

    @Test
    public void testBookCanGetInfo() {
        assertEquals("The Girls by Emma Cline", book.getBookInfo());
    }


}
