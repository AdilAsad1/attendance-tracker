package edu.psu.afa6316.lioncheckin.db;

import android.content.Context;
import android.os.Looper;
import android.os.Message;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {Class.class, Student.class}, version = 1, exportSchema = false)
public abstract class AttendanceDatabase extends RoomDatabase {
    public interface classListener{
        void onClassReturned(Class class_);
    }

    public abstract ClassDao classDao();
    public abstract StudentDao studentDao();

    private static AttendanceDatabase INSTANCE;

    public static AttendanceDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AttendanceDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AttendanceDatabase.class, "AttendanceDatabase").addCallback(createAttendanceDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback createAttendanceDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };

    public static void insertClass(Class class_){
        new Thread(() -> INSTANCE.classDao().insertClass(class_)).start();
    }

    public static void deleteClass(int classId){
        new Thread(()-> INSTANCE.classDao().deleteClass(classId)).start();
    }

    public static void insertStudent(Student student){
        new Thread(() -> INSTANCE.studentDao().insertStudent(student)).start();
    }

    public static void deleteStudent(int studentId){
        new Thread(()-> INSTANCE.studentDao().deleteStudent(studentId)).start();
    }

    public static void getClass(final classListener listener){
        Handler handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg){
                super.handleMessage(msg);
                listener.onClassReturned((Class) msg.obj);
            }
        };
        new Thread(() -> {
            Message msg = handler.obtainMessage();
            msg.obj = INSTANCE.classDao().getAllClasses();
            handler.sendMessage(msg);
        }).start();
    }

}
