package edu.psu.afa6316.lioncheckin.db;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {
    private LiveData<List<Student>> students;
    private int classId;

//    public void setClassId(int class_id){
//        classId = class_id;
//        Log.d("Here2", "setClassId: " + classId);
//    }

    public StudentViewModel(Application application){
        super(application);
        students = AttendanceDatabase.getDatabase(getApplication()).studentDao().getStudentsByClassId();
    }


    public LiveData<List<Student>> getAll(){
        return students;
    }
}
