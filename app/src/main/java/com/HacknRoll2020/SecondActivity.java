package com.HacknRoll2020;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    Button saveButton;
    EditText toiletEditText;
    SeekBar seekBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        saveButton = (Button) findViewById(R.id.saveButton);
        toiletEditText = (EditText) findViewById(R.id.toiletEditText);
        seekBar = (SeekBar) findViewById(R.id.seekBar);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int progressLevel = seekBar.getProgress();
            }
        });
    }
}
