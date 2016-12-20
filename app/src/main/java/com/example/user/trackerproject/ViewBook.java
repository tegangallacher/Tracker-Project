package com.example.user.trackerproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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
        editButton = (Button) findViewById(R.id.button_edit);
        deleteButton = (Button) findViewById(R.id.button_delete);
        backButton = (Button) findViewById(R.id.button_back);
        submit = (Button)findViewById(R.id.subbttn);
        ratingDisplay = (TextView)findViewById(R.id.rating_text);


        ratingBar = (RatingBar)findViewById(R.id.ratingBar);

        String savedTextFromPreferences = SavedTextPreferences.getStoredText(this);
        if (savedTextFromPreferences != null && !savedTextFromPreferences.isEmpty()) {
            ratingDisplay.setVisibility(View.VISIBLE);
            ratingDisplay.setText("You rated the book: " + savedTextFromPreferences);
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSave = String.valueOf(ratingBar.getRating());
                ratingDisplay.setVisibility(View.VISIBLE);
                ratingDisplay.setText("You rated the book: " + toSave);
                Context context = v.getContext();
                SavedTextPreferences.setStoredText(context, toSave);
            }
        });


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final int id = extras.getInt("id");
        final String title = extras.getString("title");
        final String author = extras.getString("author");


        titleEditText.setText(title);
        authorEditText.setText(author);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewBook.this, EditBook.class);
                intent.putExtra("id", id);
                intent.putExtra("title", title);
                intent.putExtra("author", author);
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
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMainActivity();
            }
        });
    }

    private void backToMainActivity() {
        Intent intent = new Intent(ViewBook.this, MainActivity.class);
        startActivity(intent);
    }

}