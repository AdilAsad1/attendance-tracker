package edu.psu.afa6316.lioncheckin.db;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {
    private LiveData<List<Student>> students;
    private int class_id;

    public void setClassid(int class_id){
        this.class_id = class_id;
        students = AttendanceDatabase.getDatabase(getApplication()).studentDao().getStudentsByClassId(class_id);
    }

    public StudentViewModel(Application application){
        super(application);
    }


    public LiveData<List<Student>> getAll(){
        return students;
    }
}
