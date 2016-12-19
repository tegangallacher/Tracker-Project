package com.example.user.trackerproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 16/12/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "bookTracker";

    private static final String TABLE_BOOKS = "books";

    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_AUTHOR = "author";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BOOKS_TABLE = "CREATE TABLE " + TABLE_BOOKS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT,"
                + KEY_AUTHOR + " TEXT" + ")";
        db.execSQL(CREATE_BOOKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
        onCreate(db);
    }

    private void runSQL(String sql) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
    }

    public void addBook(Book book) {
        String title = book.getTitle();
        String author = book.getAuthor();

        String sql = "INSERT INTO " + TABLE_BOOKS + "(" + KEY_TITLE + "," + KEY_AUTHOR + ") " +
                "VALUES ('" + title + "','" + author + "')";
        runSQL(sql);
    }

    public void updateBook(Book book) {
        int id = book.getId();
        String title = book.getTitle();
        String author = book.getAuthor();

        String sql = "UPDATE " + TABLE_BOOKS + " SET "
                + KEY_TITLE + " = '" + title + "',"
                + KEY_AUTHOR + " = '" + author + "' WHERE "
                + KEY_ID + " = " + id;
        runSQL(sql);
    }

    public Book getBook(String title) {
        String sql = "SELECT * FROM " + TABLE_BOOKS +  " WHERE " + KEY_TITLE + " = '" + title + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();

            Book book = getBookFromDBCursor(cursor);
            return book;
        }
        return null;
    }


    public ArrayList<Book> getAllBooks() {
        ArrayList<Book> bookList = new ArrayList<Book>();
        String sql = "SELECT * FROM " + TABLE_BOOKS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                Book book = getBookFromDBCursor(cursor);
                bookList.add(book);
            } while (cursor.moveToNext());
        }
        return bookList;
    }

    public void deleteBook(Book book) {
        int id = book.getId();
        String sql = "DELETE FROM " + TABLE_BOOKS + " WHERE " + KEY_ID + " = " + id;
        runSQL(sql);
    }

    public void deleteBook(int id) {
        String sql = "DELETE FROM " + TABLE_BOOKS + " WHERE " + KEY_ID + " = " + id;
        runSQL(sql);
    }

    public void deleteAllBooks() {
        String sql = "DELETE FROM " + TABLE_BOOKS;
        runSQL(sql);
    }

    private  Book getBookFromDBCursor(Cursor cursor) {
        int idColumnNum = cursor.getColumnIndex(KEY_ID);
        int titleColumnNum = cursor.getColumnIndex(KEY_TITLE);
        int authorColumnNum = cursor.getColumnIndex(KEY_AUTHOR);

        int id = Integer.parseInt(cursor.getString(idColumnNum));
        String title = cursor.getString(titleColumnNum);
        String author = cursor.getString(authorColumnNum);

        Book book = new Book(id, title, author);
        return book;
    }

}

















