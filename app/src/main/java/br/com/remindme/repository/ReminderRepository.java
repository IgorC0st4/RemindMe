package br.com.remindme.repository;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import br.com.remindme.database.AppDatabase;
import br.com.remindme.model.Reminder;

public class ReminderRepository {
    private final Context context;

    public ReminderRepository(Context context) {
        this.context = context;
    }

    public boolean insert(Reminder reminder) {
        try {
            return new Insert().execute(reminder).get();
        } catch (Exception e) {
            Log.e("ERRO REPO REMINDER", e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Reminder reminder) {
        try {
            return new Delete().execute(reminder).get();
        } catch (Exception e) {
            Log.e("ERRO REPO REMINDER", e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Reminder reminder) {
        try {
            return new Update().execute(reminder).get();
        } catch (Exception e) {
            Log.e("ERRO REPO REMINDER", e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<Reminder> getAll() {
        try {
            return new GetAll().execute().get();
        } catch (Exception e) {
            Log.e("ERRO REPO REMINDER", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public int count() {
        try {
            return new Count().execute().get();
        } catch (Exception e) {
            Log.e("ERRO REPO REMINDER", e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }

    private class Insert extends AsyncTask<Reminder, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Reminder... reminders) {
            try {
                AppDatabase.getAppDatabase(context).reminderDao().insert(reminders[0]);
                return true;
            } catch (Exception e) {
                Log.e("ERRO ASYNC REMINDER", e.getMessage());
                e.printStackTrace();
                return false;
            }
        }
    }

    private class Delete extends AsyncTask<Reminder, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Reminder... reminders) {
            try {
                AppDatabase.getAppDatabase(context).reminderDao().delete(reminders[0]);
                return true;
            } catch (Exception e) {
                Log.e("ERRO ASYNC REMINDER", e.getMessage());
                e.printStackTrace();
                return false;
            }
        }
    }

    private class Update extends AsyncTask<Reminder, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Reminder... reminders) {
            try {
                AppDatabase.getAppDatabase(context).reminderDao().update(reminders[0]);
                return true;
            } catch (Exception e) {
                Log.e("ERRO ASYNC REMINDER", e.getMessage());
                e.printStackTrace();
                return false;
            }
        }
    }

    private class GetAll extends AsyncTask<Void, Void, List<Reminder>> {

        @Override
        protected List<Reminder> doInBackground(Void... voids) {
            try {
                return AppDatabase.getAppDatabase(context).reminderDao().getAll();
            } catch (Exception e) {
                Log.e("ERRO ASYNC REMINDER", e.getMessage());
                e.printStackTrace();
                return null;
            }
        }
    }

    private class Count extends AsyncTask<Void, Void, Integer> {
        @Override
        protected Integer doInBackground(Void... voids) {
            try {
                return AppDatabase.getAppDatabase(context).reminderDao().count();
            } catch (Exception e) {
                Log.e("ERRO ASYNC REMINDER", e.getMessage());
                e.printStackTrace();
                return -1;
            }
        }
    }
}
