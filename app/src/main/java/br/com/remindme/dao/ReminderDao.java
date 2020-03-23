package br.com.remindme.dao;

import androidx.room.*;

import java.util.List;

import br.com.remindme.model.Reminder;

@Dao
public interface ReminderDao {
    @Insert
    void insert (Reminder reminder);

    @Update
    void update (Reminder reminder);

    @Delete
    void delete (Reminder reminder);

    @Query("SELECT * FROM Reminder ORDER BY id ASC")
    List<Reminder> getAll();

    @Query("SELECT COUNT(id) FROM Reminder")
    int count();
}
