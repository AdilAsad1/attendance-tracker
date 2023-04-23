package edu.psu.afa6316.lioncheckin.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import edu.psu.afa6316.lioncheckin.db.Student;

@Dao
public interface StudentDao {
    @Query("SELECT * FROM students WHERE class_id = :classId")
    LiveData<List<Student>> getStudentsByClassId(int classId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStudent(Student student);

    @Query("DELETE FROM students WHERE id = :studentId")
    void deleteStudent(int studentId);
}