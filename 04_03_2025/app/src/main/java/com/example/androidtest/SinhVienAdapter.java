package com.example.androidtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class SinhVienAdapter extends ArrayAdapter<SinhVien> {
    private Context context;

    public SinhVienAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.sv_item, parent, false);
        }
        SinhVien sv = getItem(position);
        if (sv != null){
            TextView txtName = convertView.findViewById(R.id.itemName);
            TextView txtDateOfBirth = convertView.findViewById(R.id.itemBirth);
            txtName.setText(sv.getName());
            txtDateOfBirth.setText(String.valueOf(sv.getDataOfBirth()));
        }
        return convertView;
    }

    public SinhVienAdapter(@NonNull Context context, int resource, @NonNull List<SinhVien> objects) {
        super(context, resource, objects);
        this.context = context;
    }
}
