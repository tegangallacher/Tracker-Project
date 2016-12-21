package com.example.user.trackerproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by user on 21/12/2016.
 */
public class HomePage extends AppCompatActivity {

    TextView welcome;
    Button enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_main);

        welcome = (TextView)findViewById(R.id.home_page_text);
        enter = (Button)findViewById(R.id.enter_button);


        enter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }
}