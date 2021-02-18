package com.example.localdatabase.room;

import androidx.room.TypeConverter;

import com.example.localdatabase.shared.User;
import com.google.gson.Gson;

public class Converters {
    @TypeConverter
    public static String fromUserToJson(User user){
        return new Gson().toJson(user);
    }
    @TypeConverter
    public static User fromJsonTOUser(String json){
        return new Gson().fromJson(json,User.class);
    }
}
