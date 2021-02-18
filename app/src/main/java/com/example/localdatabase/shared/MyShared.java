package com.example.localdatabase.shared;

import android.content.Context;
import android.content.SharedPreferences;

public class MyShared {
    private static SharedPreferences instance;

    public static SharedPreferences getInstance(Context context) {
        if (instance == null) {
            instance = context.getSharedPreferences("userData", Context.MODE_PRIVATE);
        }
        return instance;
    }
}