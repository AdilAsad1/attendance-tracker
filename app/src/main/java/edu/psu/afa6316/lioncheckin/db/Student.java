package edu.psu.afa6316.lioncheckin.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "students")
public class Student {
    public Student(String name, int studentId, int classId) {
        this.name = name;
        this.studentId = studentId;
        this.classId = classId;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int studentId;

    @ColumnInfo(name = "student_name")
    public String name;

    @ColumnInfo(name = "class_id")
    public int classId;

    @ColumnInfo(name = "attendance")
    public boolean attendance;

}
