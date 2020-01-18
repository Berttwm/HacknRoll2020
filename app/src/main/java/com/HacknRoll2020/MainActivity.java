package com.HacknRoll2020;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button addAlarm;
    ImageView alarm1_switch;
    ImageView alarm2_switch;
    ImageView alarm3_switch;

    boolean alarm1_status = true;
    boolean alarm2_status = false;
    boolean alarm3_status = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initValues();
        //initListeners();

        alarm1_switch.setOnClickListener(this);
        alarm2_switch.setOnClickListener(this);
        alarm3_switch.setOnClickListener(this);



        addAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSecondActivity();
            }
        });

    }

    private void initValues(){
        addAlarm = (Button) findViewById(R.id.addAlarm);
        alarm1_switch = (ImageView) findViewById(R.id.alarm1_switch);
        alarm2_switch = (ImageView) findViewById(R.id.alarm2_switch);
        alarm3_switch = (ImageView) findViewById(R.id.alarm3_switch);
    }

/*    private void initListeners() {
        alarm1_switch.setOnClickListener(this);
        alarm2_switch.setOnClickListener(this);
        alarm3_switch.setOnClickListener(this);



        addAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSecondActivity();
            }
        });
    }*/

    private void startSecondActivity() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.alarm1_switch:
                if(alarm1_status == false) {
                    alarm1_switch.setImageResource(R.drawable.switch_off);
                    alarm1_status = true;
                } else {
                    alarm1_switch.setImageResource(R.drawable.switch_on);
                    alarm1_status = false;
                }
                break;

            case R.id.alarm2_switch:
                if(alarm2_status == false) {
                    alarm2_switch.setImageResource(R.drawable.switch_off);
                    alarm2_status = true;
                } else {
                    alarm2_switch.setImageResource(R.drawable.switch_on);
                    alarm2_status = false;
                }
                break;

            case R.id.alarm3_switch:
                if(alarm3_status == false) {
                    alarm3_switch.setImageResource(R.drawable.switch_off);
                    alarm3_status = true;
                } else {
                    alarm3_switch.setImageResource(R.drawable.switch_on);
                    alarm3_status = false;
                }
                break;
        }
    }
}
//
//public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,
//        TimePickerDialog.OnTimeSetListener {
//
//    Button btn_pick;
//    TextView Result_tv;
//    int day,month,year,hour,minute;
//    int day_x, month_x, year_x, hour_x, minute_x;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        btn_pick = findViewById(R.id.btn_click);
//        Result_tv = findViewById(R.id.TV_result);
//
//        btn_pick.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Calendar c = Calendar.getInstance();
//                year = c.get(Calendar.YEAR);
//                month = c.get(Calendar.MONTH);
//                day = c.get(Calendar.DAY_OF_MONTH);
//                DatePickerDialog datePickerDialog = new DatePickerDialog( MainActivity.this,
//                        MainActivity.this, year, month, day);
//                datePickerDialog.show();
//            }
//        });
//    }
//
//    @Override
//    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
//        year_x = i;
//        month_x = i1+1;
//        day_x = i2;
//        Calendar c = Calendar.getInstance();
//        hour = c.get(Calendar.HOUR);
//        minute = c.get(Calendar.MINUTE);
//        TimePickerDialog timePickerDialog = new TimePickerDialog( MainActivity.this, MainActivity.this,
//                hour, minute, true);
//        timePickerDialog.show();
//
//    }
//
//    @Override
//    public void onTimeSet(TimePicker timePicker, int i, int i1) {
//        hour_x = i;
//        minute_x = i1;
//        Result_tv.setText("year::"+year_x+"\n"+
//                    "month::"+month_x+"\n"+
//                    "year::"+day_x+"\n"+
//                    "year::"+hour_x+"\n"+
//                    "year::"+minute_x
//                );
//    }
//}
