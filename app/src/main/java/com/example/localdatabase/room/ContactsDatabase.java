package com.example.localdatabase.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.localdatabase.MainActivity;

@TypeConverters(Converters.class)
@Database(entities = Contact.class,version = 1,exportSchema = false)
public abstract class ContactsDatabase extends RoomDatabase {

    private static ContactsDatabase instance;

    public static ContactsDatabase getInstance(Context context){
        if (instance==null){

            instance = Room.databaseBuilder(
                    context,
                    ContactsDatabase.class,
                    "contactsDatabase")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public abstract ContactDao contactDao();

}
