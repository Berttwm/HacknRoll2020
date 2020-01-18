package com.HacknRoll2020;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    Button saveButton, cancelButton;
    EditText toiletEditText;
    SeekBar seekBar;
    MediaPlayer player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        saveButton = (Button) findViewById(R.id.saveButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        toiletEditText = (EditText) findViewById(R.id.toiletEditText);
        seekBar = (SeekBar) findViewById(R.id.seekBar);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int stepsToToilet = Integer.parseInt(toiletEditText.getText().toString());
                int progressLevel = seekBar.getProgress();

                if(player == null) {
                    if (progressLevel == 0) {
                        player = (MediaPlayer) MediaPlayer.create(getApplicationContext(), R.raw.light_sleeper);
                    } else if (progressLevel == 1) {
                        player = (MediaPlayer) MediaPlayer.create(getApplicationContext(), R.raw.medium_sleeper);
                    } else if (progressLevel == 2) {
                        player = (MediaPlayer) MediaPlayer.create(getApplicationContext(), R.raw.heavy_sleeper);
                    }

                    player.start();
                }

//                backToMain();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player != null)
                player.release();
                player = null;
                Toast.makeText(getApplicationContext(), "CONGRATS U WOKE UP FAGGOT", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void backToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
