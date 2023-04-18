package edu.psu.afa6316.lioncheckin.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ClassDao {

    @Query("SELECT * FROM classes WHERE id = :classId")
    Class getClassById(int classId);

    @Query("SELECT * FROM classes")
    List<Class> getAllClasses();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertClass(Class... class_);

    @Query("DELETE FROM classes WHERE id = :classId")
    void deleteClass(int classId);

}