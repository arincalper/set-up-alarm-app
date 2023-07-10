package com.example.eee335_lab2_task2_alper_arinc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button btnselectTime;
    EditText etTime;
    Context context = this;
    Button btnsetAlarm;
    int tempminute = 0;
    int temphour = 0;
    String am_pm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnselectTime = (Button) findViewById(R.id.btn_select_time);
        etTime = (EditText) findViewById(R.id.tv_selected_time);

        btnselectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // We get the current the information. Current hour, current minute.
                final Calendar takvim = Calendar.getInstance();
                int hour = takvim.get(Calendar.HOUR_OF_DAY);
                int minutes = takvim.get(Calendar.MINUTE);

                TimePickerDialog tpd = new TimePickerDialog(context,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                // hourOfDay and minute values are the chosen time values for our alarm.
                                // We send these chosen time values inside the edit text.


                                if (hourOfDay > 12)
                                {
                                    hourOfDay = hourOfDay - 12;
                                    am_pm = "PM";
                                } else {
                                    am_pm = "AM";
                                }

                                temphour = hourOfDay;
                                tempminute = minute;
                                etTime.setText(hourOfDay + ":" + minute + "" + am_pm);





                            }
                        }, hour, minutes, false);
                // Here we write the values to be set when the timepicker is executed.
                // we use the variables we defined above to show the current time.
                // true value is for 24-hour format.

                // We set the button information of the dialog window and show it on the screen.
                tpd.setButton(TimePickerDialog.BUTTON_POSITIVE, "Select", tpd);
                tpd.setButton(TimePickerDialog.BUTTON_NEGATIVE, "Cancel", tpd);
                tpd.show();



            }
        });

        btnsetAlarm = (Button) findViewById(R.id.btn_set_alarm);

        btnsetAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("çalışıyo"); // I used it for debugging during coding. My app was crashing and I was curious to see whether I was printing the value or not.
                int alarm_hour = Integer.parseInt(String.valueOf(temphour));
                int alarm_minute =  Integer.parseInt(String.valueOf(tempminute));

                System.out.println(am_pm); // used for debugging
                if(am_pm == "PM") {  // My alarms were always setting as AM. Therefore I had to write this piece of code.
                    alarm_hour = alarm_hour + 12;
                }

                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                intent.putExtra(AlarmClock.EXTRA_HOUR,alarm_hour);
                intent.putExtra(AlarmClock.EXTRA_MINUTES,alarm_minute);

                startActivity(intent);




            }


        });
    }


}