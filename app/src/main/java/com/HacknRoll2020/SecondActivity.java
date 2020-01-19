package com.HacknRoll2020;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

    //boolean alarm_status;

    SensorManager sensorManager;
    Button saveButton, cancelButton;
    EditText toiletEditText;
    SeekBar seekBar;
    MediaPlayer player;
    MediaPlayer congratsPlayer;

    //Dialog box after alarm ends
    Dialog alarmOffDialog;
    Button closeButton;
    TextView headerMsg, bodyMsg;

    //Dialog box before alarm ends
    Dialog alarmOnDialog;
    TextView run_headerMsg, run_bodyMsg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        alarmOffDialog = new Dialog(this);

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
                if(player == null) {
                    if (progressLevel == 0) {
                        player = (MediaPlayer) MediaPlayer.create(getApplicationContext(), R.raw.light_sleeper);
                        player.start();
                        Toast.makeText(getApplicationContext(), "LET'S GET WALKING!", Toast.LENGTH_SHORT).show();
//                        alarmRingMessage();
                    } else if (progressLevel == 1) {
                        player = (MediaPlayer) MediaPlayer.create(getApplicationContext(), R.raw.medium_sleeper);
                        player.start();
                        Toast.makeText(getApplicationContext(), "LET'S GET WALKING!", Toast.LENGTH_SHORT).show();
                        //alarmRingMessage();
                    } else if (progressLevel == 2) {
                        player = (MediaPlayer) MediaPlayer.create(getApplicationContext(), R.raw.heavy_sleeper);
                        player.start();
                        Toast.makeText(getApplicationContext(), "LET'S GET WALKING!", Toast.LENGTH_SHORT).show();
                        //alarmRingMessage();
                    }
                }

                //backToMain();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopMusic();
                showAlarmEndMsg();
            }
        });

    }

    private void alarmRingMessage() {
        alarmOnDialog.setContentView(R.layout.running_alarm_popup);
        run_headerMsg = (TextView) alarmOnDialog.findViewById(R.id.runningAlarm_headerMsg);
        run_bodyMsg = (TextView) alarmOnDialog.findViewById(R.id.runningAlarm_bodyMsg);

        alarmOnDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alarmOnDialog.show();
    }


    private void showAlarmEndMsg() {

        if(congratsPlayer == null) {
            congratsPlayer = (MediaPlayer) MediaPlayer.create(getApplicationContext(), R.raw.congrats_sound);
            congratsPlayer.start();
        }

        alarmOffDialog.setContentView(R.layout.end_alarm_popup);
        closeButton = (Button) alarmOffDialog.findViewById(R.id.button_close);
        headerMsg = (TextView) alarmOffDialog.findViewById(R.id.endAlarm_headerMsg);
        bodyMsg = (TextView) alarmOffDialog.findViewById(R.id.endAlarm_bodyMsg);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alarmOffDialog.dismiss();
            }
        });

        alarmOffDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alarmOffDialog.show();
    }

    private void stopMusic() {
        if (player != null)
            player.release();
        player = null;
        Toast.makeText(getApplicationContext(), "Alarm is Off", Toast.LENGTH_SHORT).show();
    }

    private void backToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        //intent.putExtra("ALARM_STATUS", "on");
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
            currentWalkedSteps = event.values[0];

            if(initializedStarting == false) {
                startingSteps = currentWalkedSteps;
                initializedStarting = true;
            }


            if((currentWalkedSteps > totalFinalValue) && (player != null)) {
                stopMusic();
//                alarmOnDialog.dismiss();
                showAlarmEndMsg();
            }


        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
