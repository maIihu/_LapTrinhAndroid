package com.example.a12_03_2025;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.os.MessageCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ShowContact extends AppCompatActivity {

    Button buttonBack;
    ListView listViewContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_contact);

        buttonBack = findViewById(R.id.button2);

        ShowContacts();
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void ShowContacts() {
        listViewContact = findViewById(R.id.listView);
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        ArrayList<String> arrayList = new ArrayList<>();
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            String s = "";
            // ID
            String idColumnName = ContactsContract.Contacts._ID;
            int idIndex = cursor.getColumnIndex(idColumnName);
            s = cursor.getString(idIndex) + "-";
            // NAME
            String nameColumnName = ContactsContract.Contacts.DISPLAY_NAME;
            int nameIndex = cursor.getColumnIndex(nameColumnName);
            s += cursor.getString(nameIndex);
            arrayList.add(s);
            cursor.moveToNext();
        }
        cursor.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listViewContact.setAdapter(adapter);
    }


}