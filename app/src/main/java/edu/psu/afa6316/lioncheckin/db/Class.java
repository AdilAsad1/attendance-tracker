package edu.psu.afa6316.lioncheckin.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "class_list")
public class Class {
    public Class(int id, String classSubject, String className) {
        this.id = id;
        this.classSubject = classSubject;
        this.className = className;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "subject")
    public String classSubject;

    @ColumnInfo(name = "name")
    public String className;

}