package edu.psu.afa6316.lioncheckin.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import edu.psu.afa6316.lioncheckin.db.Class;

@Dao
public interface ClassDao {
    @Query("SELECT * FROM class_list ORDER BY name COLLATE NOCASE, id")
    LiveData<List<Class>> getAllClasses();

    @Insert
    void insertClass(Class... classes);

    @Delete
    void deleteClass(Class... classes);

    @Query("DELETE FROM class_list WHERE id = :classId")
    void deleteClass(int classId);

}