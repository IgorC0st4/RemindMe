package br.com.remindme.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.com.remindme.dao.ReminderDao;
import br.com.remindme.model.Reminder;

@Database(entities = Reminder.class, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ReminderDao reminderDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "RemindMe")
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}