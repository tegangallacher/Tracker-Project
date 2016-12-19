package com.example.user.trackerproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by user on 18/12/2016.
 */
public class EditBook extends AppCompatActivity {
    EditText titleEditText;
    EditText authorEditText;
    Button saveButton;
    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final DatabaseHandler db = ((MainApplication)getApplication()).db;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_book);

        titleEditText = (EditText)findViewById(R.id.book_title);
        authorEditText = (EditText)findViewById(R.id.book_author);
        saveButton = (Button)findViewById(R.id.button_save);
        cancelButton = (Button)findViewById(R.id.button_cancel);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final int id = extras.getInt("id");
        final String title = extras.getString("title");
        final String author = extras.getString("author");

        titleEditText.setText(title);
        authorEditText.setText(author);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString();
                String author = authorEditText.getText().toString();
                Book bookToUpdate = new Book(id, title, author);
                db.updateBook(bookToUpdate);
                backToBookView(id, title, author);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToBookView(id, title, author);
            }
        });
    }

    private void backToBookView(int id, String title, String author) {
        Intent intent = new Intent(EditBook.this, ViewBook.class);
        intent.putExtra("id", id);
        intent.putExtra("title", title);
        intent.putExtra("author", author);

        startActivity(intent);
    }
}
