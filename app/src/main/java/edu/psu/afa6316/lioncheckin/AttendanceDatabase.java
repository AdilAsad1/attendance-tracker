package edu.psu.afa6316.lioncheckin;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Class.class, Student.class}, version = 1)
public abstract class AttendanceDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "attendance_database";
    private static AttendanceDatabase instance;

    public static synchronized AttendanceDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AttendanceDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract ClassDao classDao();

    public abstract StudentDao studentDao();
}