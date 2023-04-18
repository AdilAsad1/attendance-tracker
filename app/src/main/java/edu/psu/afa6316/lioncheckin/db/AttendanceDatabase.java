package edu.psu.afa6316.lioncheckin.db;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {Class.class, Student.class}, version = 1, exportSchema = false)
public abstract class AttendanceDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "attendance_database";
    public abstract ClassDao classDao();
    public abstract StudentDao studentDao();

    public interface StudentListener {
        void onStudentReturned(Student student);
    }
    public interface ClassListener {
        void onClassReturned(Class class_);
    }

    private static AttendanceDatabase INSTANCE;

    public static AttendanceDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AttendanceDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AttendanceDatabase.class, "attendance_database")
                            .addCallback(createAttendanceDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    // Note this call back will be run
    private static RoomDatabase.Callback createAttendanceDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };


/*
    public static void getClass(int id, ClassListner listener) {
        Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                listener.onClassReturned((Class) msg.obj);
            }
        };

        (new Thread(() -> {
            Message msg = handler.obtainMessage();
            msg.obj = INSTANCE.classDao().getClassById(id);
            handler.sendMessage(msg);
        })).start();
    }
*/

}



