package com.HacknRoll2020;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
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
