package edu.psu.afa6316.lioncheckin.db;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {
    private LiveData<List<Student>> students;

    public StudentViewModel (Application application) {
        super(application);
        students = AttendanceDatabase.getDatabase(getApplication()).studentDao().getStudentsByClassId();
    }



    public LiveData<List<Student>> getAllStudents() {
        return students;
    }
}
