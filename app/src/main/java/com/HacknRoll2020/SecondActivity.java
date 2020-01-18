package com.HacknRoll2020;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final EditText toiletEditText = (EditText) findViewById(R.id.toiletEditText);
        Button saveButton = (Button) findViewById(R.id.saveButton);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int steps = Integer.parseInt(toiletEditText.getText().toString());
//            }
//        });
    }
}
