package edu.psu.afa6316.lioncheckin.db;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ClassViewModel extends AndroidViewModel {

    private LiveData<List<Class>> classes;
    private ClassDao classDao;

    public ClassViewModel(Application application) {
        super(application);
        AttendanceDatabase database = AttendanceDatabase.getInstance(application);
        classDao = database.classDao();
        classes = classDao.getAllClasses();
    }

    public LiveData<List<Class>> getAllClasses() {
        return classes;
    }
}
