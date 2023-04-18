package edu.psu.afa6316.lioncheckin.db;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;


public class ClassViewModel extends AndroidViewModel {

    public ClassViewModel (Application application) {
        super(application);
        classes = AttendanceDatabase.getDatabase(getApplication()).classDao().getClassById();
    }


}