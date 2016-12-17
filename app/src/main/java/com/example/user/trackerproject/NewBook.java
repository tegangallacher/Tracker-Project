package com.example.user.trackerproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by user on 16/12/2016.
 */
public class NewBook extends AppCompatActivity {
    EditText titleEditText;
    EditText authorEditText;
    Button saveButton;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_book);

        titleEditText = (EditText)findViewById(R.id.book_title);
        authorEditText = (EditText)findViewById(R.id.book_author);
        saveButton = (Button)findViewById(R.id.button_add);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseHandler db = ((MainApplication) getApplication()).db;
                String title = titleEditText.getText().toString();
                String author = authorEditText.getText().toString();
                Book newBook = new Book(title, author);
                db.addBook(newBook);
                backToMainView();
            }
        });
    }
    private void backToMainView() {
        Intent intent = new Intent(NewBook.this, MainActivity.class);
        startActivity(intent);
    }
}
