package com.example.user.trackerproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by user on 16/12/2016.
 */
public class MainActivity extends AppCompatActivity {
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView)findViewById(R.id.book_list);

        final DatabaseHandler db = ((MainApplication)getApplication()).db;

        Log.d("Insert: ", "Inserting..");
        db.addBook(new Book("A Little Life", "Hanya Yanagihara"));

    }
}
