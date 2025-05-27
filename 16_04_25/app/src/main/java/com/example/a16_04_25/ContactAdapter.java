package com.example.a16_04_25;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {
    private Context context;
    public ContactAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.contact_item, parent, false);
        }
        Contact bh = getItem(position);
        if(bh != null){
            TextView txtId = convertView.findViewById(R.id.txtViewId);
            TextView txtName =convertView.findViewById(R.id.txtViewName);
            TextView txtNumber = convertView.findViewById(R.id.txtViewNumber);

            txtId.setText(String.valueOf(bh.getId()));
            txtName.setText(bh.getName());
            txtNumber.setText(bh.getPhoneNumber());
        }
        return convertView;
    }

    public ContactAdapter(@NonNull Context context, int resource, @NonNull List<Contact> objects) {
        super(context, resource, objects);
        this.context = context;
    }
}
