package edu.psu.afa6316.lioncheckin.db;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;


public class StudentListConverter {
    @TypeConverter
    public static List<Student> fromString(String value) {
        Type listType = new TypeToken<List<Student>>(){}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromList(List<Student> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
