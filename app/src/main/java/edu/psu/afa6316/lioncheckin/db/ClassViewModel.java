package edu.psu.afa6316.lioncheckin.db;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ClassViewModel extends AndroidViewModel {

    private LiveData<List<Class>> classes;

    public ClassViewModel(Application application) {
        super(application);
        classes = AttendanceDatabase.getDatabase(getApplication()).classDao().getAllClasses();
    }



    public LiveData<List<Class>> getAll() {
        return classes;
    }
}
