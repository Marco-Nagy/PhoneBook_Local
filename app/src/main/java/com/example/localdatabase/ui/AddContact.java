package com.example.localdatabase.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.localdatabase.R;
import com.example.localdatabase.room.Contact;
import com.example.localdatabase.room.ContactsDatabase;
import com.google.android.material.textfield.TextInputEditText;

public class AddContact extends AppCompatActivity {
    TextInputEditText editTextName,editTextPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        editTextName=findViewById(R.id.edit_text_name);
        editTextPhone=findViewById(R.id.edit_text_phone);
    }


    public void addContact(View view) {

            String name = editTextName.getText().toString();
            String phone = editTextPhone.getText().toString();

            if (name.isEmpty()||phone.isEmpty()){
                Toast.makeText(this, "please fill data", Toast.LENGTH_SHORT).show();
                return;
            }
                Contact contact =new Contact(name,phone);
                ContactsDatabase.getInstance(this).contactDao().addContact(contact);
                Toast.makeText(this, "Contact Added", Toast.LENGTH_SHORT).show();
                finish();
            }


        }

