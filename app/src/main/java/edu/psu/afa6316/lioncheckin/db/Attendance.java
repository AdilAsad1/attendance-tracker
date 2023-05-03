package edu.psu.afa6316.lioncheckin.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "attendance_table")
public class Attendance {
    public Attendance(int id, String classId, boolean attendance) {
        this.id = id;
        this.classId = classId;
        this.attendance = attendance;
    }

    @PrimaryKey()
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "class_id")
    public String classId;

    @ColumnInfo(name = "attendance")
    public boolean attendance;

}
