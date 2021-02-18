package com.example.localdatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.localdatabase.room.Contact;
import com.example.localdatabase.room.ContactsDatabase;
import com.example.localdatabase.shared.User;
import com.example.localdatabase.ui.AddContact;
import com.example.localdatabase.ui.ContactsAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

// Local Database >> SharedPreferences , Room
//Room
//Entity , Dao , Database
public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ContactsAdapter contactsAdapter;
    List<Contact> contactList =new ArrayList<>();

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView= findViewById(R.id.recycler_view);
        getContacts();

    }
    private void sharedPreference(){
        SharedPreferences preferences = getSharedPreferences("userData", MODE_PRIVATE);
        String token = preferences.getString("token", "");
        Log.i(TAG, "onCreate: " + token);

        if (token.isEmpty()){
            Log.i(TAG, "onCreate: token is Empty");
        }
        preferences.edit().putString("token","marco nagy005200550").apply();
        String newToken = preferences.getString("token","");
        Log.i(TAG, "onCreate: "+newToken);

        //Gson
        User user = new User(1 ,"marco", "01220407005" );
        Log.i(TAG, "onCreate: "+user);
        Gson gson = new Gson();

        String userJson= gson.toJson(user);
        Log.i(TAG, "onCreate: "+userJson);
        preferences.edit().putString("user",userJson).apply();
        String userJsonFromShared = preferences.getString("user","");
        if (!userJsonFromShared.isEmpty()){
            User userFromJson = gson.fromJson(userJsonFromShared,User.class);
            Log.i(TAG, "onCreate: "+userFromJson);
        }
    }

    private void getContacts(){
        contactList= ContactsDatabase.getInstance(this).contactDao().getContact();
        contactsAdapter = new ContactsAdapter(contactList,this, contactI);
        recyclerView.setAdapter(contactsAdapter);
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        getContacts();
    }

    public void navigate(View view) {
        startActivity(new Intent(MainActivity.this, AddContact.class));
    }
  ContactsAdapter.ContactI contactI =new ContactsAdapter.ContactI() {
      @Override
      public void onDeleteClick(Contact contact) {
        ContactsDatabase.getInstance(MainActivity.this)
                .contactDao()
                .deleteContact(contact);
          getContacts();
      }
  };
}