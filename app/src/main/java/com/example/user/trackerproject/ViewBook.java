package com.example.user.trackerproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.RatingBar;
import android.widget.Toast;

/**
 * Created by user on 18/12/2016.
 */
public class ViewBook extends AppCompatActivity {
    TextView titleEditText;
    TextView authorEditText;
    Button editButton;
    Button deleteButton;
    Button backButton;
    RatingBar ratingBar;
    Button submit;
    TextView ratingDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        final DatabaseHandler db = ((MainApplication) getApplication()).db;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_book);

        titleEditText = (TextView) findViewById(R.id.book_title);
        authorEditText = (TextView) findViewById(R.id.book_author);
        ratingDisplay = (TextView)findViewById(R.id.rating_text);
        editButton = (Button) findViewById(R.id.button_edit);
        deleteButton = (Button) findViewById(R.id.button_delete);
//        backButton = (Button) findViewById(R.id.button_back);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final int id = extras.getInt("id");
        final String title = extras.getString("title");
        final String author = extras.getString("author");
        final String rating = extras.getString("rating");


        titleEditText.setText(title);
        authorEditText.setText("by " + author);


        if (rating.equals("null")) {
//            ratingDisplay.setText(" ");
            ratingDisplay.setVisibility(View.INVISIBLE);
        } else {
            ratingDisplay.setVisibility(View.VISIBLE);
            ratingDisplay.setText("You rated this book: " + rating);
        }


        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewBook.this, EditBook.class);
                intent.putExtra("id", id);
                intent.putExtra("title", title);
                intent.putExtra("author", author);
                intent.putExtra("rating", rating);
                startActivity(intent);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteBook(id);
                backToMainActivity();
            }
        });
//
//        backButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                backToMainActivity();
//            }
//        });
    }

    private void backToMainActivity() {
        Intent intent = new Intent(ViewBook.this, MainActivity.class);
        startActivity(intent);
    }

}