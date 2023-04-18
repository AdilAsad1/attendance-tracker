package edu.psu.afa6316.lioncheckin.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "students")
public class Student {



    public Student(String name, int id, String email) {
        this.name = name;
        this.id = id;
        this.email = email;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "student_name")
    public String name;

    @ColumnInfo(name = "student_email")
    public String email;


}
