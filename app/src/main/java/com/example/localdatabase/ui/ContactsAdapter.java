package com.example.localdatabase.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.localdatabase.MainActivity;
import com.example.localdatabase.R;
import com.example.localdatabase.room.Contact;
import com.example.localdatabase.room.ContactsDatabase;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactHolder> {
    private List<Contact> contacts;
    private Context context;
    private ContactI contactI;

    public ContactsAdapter(List<Contact> contacts, Context context, ContactI contactI) {
        this.contacts = contacts;
        this.context = context;
        this.contactI = contactI;
    }

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ContactHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.textViewName.setText(contact.getName());
        holder.textViewPhone.setText(contact.getPhone());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + contact.getPhone()));
                context.startActivity(intent);
            }
        });
        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.add("Delete").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                       contactI.onDeleteClick(contact);
                        return true;
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    class ContactHolder extends RecyclerView.ViewHolder  {
        TextView textViewName, textViewPhone;

        public ContactHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewPhone = itemView.findViewById(R.id.text_view_phone);
        }
    }

    public interface ContactI {
        void onDeleteClick(Contact contact);
    }
}
