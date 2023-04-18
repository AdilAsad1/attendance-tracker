package edu.psu.afa6316.lioncheckin.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

@Entity(tableName = "classes")
public class Class {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @TypeConverters({StudentListConverter.class})
    public List<Student> students;


    @ColumnInfo(name = "name")
    public String className;

    @ColumnInfo(name = "code")
    public String classCode;



}