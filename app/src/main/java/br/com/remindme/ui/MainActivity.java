package br.com.remindme.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import br.com.remindme.R;
import br.com.remindme.adapter.ReminderAdapter;
import br.com.remindme.model.Reminder;
import br.com.remindme.repository.ReminderRepository;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    final static int RQS_1 = 1;

    private RecyclerView recyclerView;
    private TimePickerDialog timePickerDialog;
    private ReminderRepository reminderRepository;
    private ReminderAdapter reminderAdapter;
    private ArrayList<Reminder> reminders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: started");

        initRepository();
    }

    private void initRepository() {
        reminderRepository = new ReminderRepository(MainActivity.this);
        reminders = (ArrayList<Reminder>) reminderRepository.getAll();
        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview");
        recyclerView = findViewById(R.id.recycler_view);
        reminderAdapter = new ReminderAdapter(reminders, MainActivity.this);
        recyclerView.setAdapter(reminderAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    public void openTimePickerDialog(View view) {
        Calendar calendar = Calendar.getInstance();
        timePickerDialog = new TimePickerDialog(
                MainActivity.this,
                onTimeSetListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true);
        timePickerDialog.setTitle("Set Alarm Time");
        timePickerDialog.show();
    }

    TimePickerDialog.OnTimeSetListener onTimeSetListener
            = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Calendar calNow = Calendar.getInstance();
            Calendar calSet = (Calendar) calNow.clone();
            calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calSet.set(Calendar.MINUTE, minute);
            calSet.set(Calendar.SECOND, 0);
            calSet.set(Calendar.MILLISECOND, 0);
            if (calSet.compareTo(calNow) <= 0) {
                calSet.add(Calendar.DATE, 1);
            }
            Reminder reminder = new Reminder();

            String title = "Reminder " + (reminderRepository.count()+1);

            reminder.setHour(hourOfDay);
            reminder.setMinute(minute);
            reminder.setTitle(title);

            if(reminderRepository.insert(reminder)){
                Toast.makeText(MainActivity.this, "Reminder saved", Toast.LENGTH_SHORT).show();
            }

            initRepository();
            setAlarm(calSet);
        }
    };


    private void setAlarm(Calendar targetCal){
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_1, intent, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
    }

}