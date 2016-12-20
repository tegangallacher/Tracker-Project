package com.example.user.trackerproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by user on 18/12/2016.
 */
public class EditBook extends AppCompatActivity {
    EditText titleEditText;
    EditText authorEditText;
    Button saveButton;
    Button cancelButton;
    RatingBar ratingBar;
    TextView ratingDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final DatabaseHandler db = ((MainApplication)getApplication()).db;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_book);

        titleEditText = (EditText)findViewById(R.id.book_title);
        authorEditText = (EditText)findViewById(R.id.book_author);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        saveButton = (Button)findViewById(R.id.button_save);
        cancelButton = (Button)findViewById(R.id.button_cancel);
        ratingDisplay = (TextView)findViewById(R.id.rating_text);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final int id = extras.getInt("id");
        final String title = extras.getString("title");
        final String author = extras.getString("author");
        final String rating = extras.getString("rating");

        titleEditText.setText(title);
        authorEditText.setText(author);
        ratingDisplay.setVisibility(View.VISIBLE);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString();
                String author = authorEditText.getText().toString();
                String rating = String.valueOf(ratingBar.getRating());
                ratingDisplay.setText("You rated the book: " + rating);
//                ratingDisplay.setVisibility(View.VISIBLE);
                Book bookToUpdate = new Book(id, title, author, rating);
                db.updateBook(bookToUpdate);
                backToBookView(id, title, author, rating);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToBookView(id, title, author, rating);
            }
        });
    }

    private void backToBookView(int id, String title, String author, String rating) {
        Intent intent = new Intent(EditBook.this, ViewBook.class);
        intent.putExtra("id", id);
        intent.putExtra("title", title);
        intent.putExtra("author", author);
        intent.putExtra("rating", rating);

        startActivity(intent);
    }
}
