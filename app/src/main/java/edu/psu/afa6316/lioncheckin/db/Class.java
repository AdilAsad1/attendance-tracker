package edu.psu.afa6316.lioncheckin.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "class_list")
public class Class {
    public Class(int id, String className, String classCode) {
        this.id = id;
        this.className = className;
        this.classCode = classCode;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "name")
    public String className;

    @ColumnInfo(name = "code")
    public String classCode;


    public int getId() {
        return id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }
}