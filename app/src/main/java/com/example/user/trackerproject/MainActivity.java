package com.example.user.trackerproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 16/12/2016.
 */
public class MainActivity extends AppCompatActivity {
    ListView listView;
    Button newBookButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.book_list);
        newBookButton = (Button)findViewById(R.id.button_new);

        final DatabaseHandler db = ((MainApplication)getApplication()).db;

        //db.deleteAllBooks();
//        db.addBook(new Book("A Little Life", "Hanya Yanagihara"));
//        db.addBook(new Book("The Girls", "Emma Cline"));
//        db.addBook(new Book("Norwegian Wood", "Haruki Murakami"));


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getAllBookTitles(db));
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedBook = (String)listView.getItemAtPosition(position);
                Log.d("ListView:", selectedBook + " selected, position " + position + ", id: " + id);
                Book book = db.getBook(selectedBook);
                Intent intent = new Intent(MainActivity.this, ViewBook.class);
                intent.putExtra("id", book.getId());
                intent.putExtra("title", book.getTitle());
                intent.putExtra("author", book.getAuthor());
                startActivity(intent);
            }
        });

        newBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewBook.class);
                startActivity(intent);
            }
        });
    }


    public ArrayList<String> getAllBookTitles(DatabaseHandler db) {
        ArrayList<String> bookTitles = new ArrayList<String>();

        ArrayList<Book> titles = db.getAllBooks();
        for (Book book : titles) {
            bookTitles.add(book.getTitle());
        }
        return bookTitles;
    }
}
