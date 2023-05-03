package edu.psu.afa6316.lioncheckin.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AttendanceDao {
    @Query("SELECT * FROM attendance_table")
    LiveData<List<Attendance>> getAllAttendance();

    @Insert
    void insertAttendance(Attendance... attendances);

    @Delete
    void deleteClass(Attendance... attendances);



}
