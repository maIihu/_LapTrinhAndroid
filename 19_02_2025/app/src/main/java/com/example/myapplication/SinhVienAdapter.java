package com.example.myapplication;

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
    public SinhVienAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public SinhVienAdapter(@NonNull Context context, int resource, @NonNull List<SinhVien> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v ==  null){
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.sv_item, null);
        }
        SinhVien sv = getItem(position);
        if (sv!=null){
            TextView tv1 = (TextView) v.findViewById(R.id.textViewHoten);
            TextView tv2 = (TextView) v.findViewById(R.id.textViewNamsinh);
            tv1.setText(sv.HoTen);
            tv2.setText(String.valueOf(sv.NamSinh));
        }
        return v;
    }
}
