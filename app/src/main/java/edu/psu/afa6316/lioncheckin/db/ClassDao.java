package edu.psu.afa6316.lioncheckin.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import edu.psu.afa6316.lioncheckin.db.Class;

@Dao
public interface ClassDao {
    @Query("SELECT * FROM class_list")
    LiveData<List<Class>> getAllClasses();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertClass(Class... class_);

    @Query("DELETE FROM class_list WHERE id = :classId")
    void deleteClass(int classId);


}