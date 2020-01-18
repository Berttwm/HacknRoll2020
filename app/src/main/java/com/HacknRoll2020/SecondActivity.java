package com.HacknRoll2020;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity implements SensorEventListener{

    float currentWalkedSteps; //incrementing
    float startingSteps; //always stay same
    float totalFinalValue;
    boolean running = false;
    boolean initializedStarting = false;

    SensorManager sensorManager;
    Button saveButton, cancelButton;
    EditText toiletEditText;
    SeekBar seekBar;
    MediaPlayer player;
    TextView testView, testView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        testView = (TextView) findViewById(R.id.testView);
        testView2 = (TextView) findViewById(R.id.testView2);
        saveButton = (Button) findViewById(R.id.saveButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        toiletEditText = (EditText) findViewById(R.id.toiletEditText);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float stepsToToilet = Float.parseFloat(toiletEditText.getText().toString());
                int progressLevel = seekBar.getProgress();
                totalFinalValue = stepsToToilet + startingSteps;
                testView2.setText(totalFinalValue + "");
                if(player == null) {
                    if (progressLevel == 0) {
                        player = (MediaPlayer) MediaPlayer.create(getApplicationContext(), R.raw.light_sleeper);
                        player.start();
                    } else if (progressLevel == 1) {
                        player = (MediaPlayer) MediaPlayer.create(getApplicationContext(), R.raw.medium_sleeper);
                        player.start();
                    } else if (progressLevel == 2) {
                        player = (MediaPlayer) MediaPlayer.create(getApplicationContext(), R.raw.heavy_sleeper);
                        player.start();
                    }
                }

//                backToMain();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopMusic();
            }
        });

    }

    private void stopMusic() {
        if (player != null)
            player.release();
        player = null;
        Toast.makeText(getApplicationContext(), "CONGRATS U WOKE UP FAGGOT", Toast.LENGTH_SHORT).show();
    }

    private void backToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "Sensor not found!", Toast.LENGTH_SHORT).show();
        }
    }

    /*@Override
    protected void onPause() {
        super.onPause();
        running = false;
        //if you unregister, hardware will stop detecting steps
        //sensorManager.unregisterListener(this);
    }*/

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(running) {
            testView.setText(String.valueOf(event.values[0]));
            currentWalkedSteps = event.values[0];

            if(initializedStarting == false) {
                startingSteps = currentWalkedSteps;
                initializedStarting = true;
            }

            if(currentWalkedSteps > totalFinalValue) {
                stopMusic();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
