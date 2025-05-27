package com.example.khachsan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class HoaDonAdapter extends ArrayAdapter<HoaDon> {
    private Context context;
    public HoaDonAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.hoa_don_item, parent, false);
        }
        HoaDon item = getItem(position);
        if(item != null){
            TextView txtName = convertView.findViewById(R.id.txtTen);
            TextView txtNumber = convertView.findViewById(R.id.txtGia);
            TextView txtNgay = convertView.findViewById(R.id.txtNgay);

            txtName.setText(item.getName());
            txtNumber.setText(String.valueOf(item.getCost()));
            txtNgay.setText(String.valueOf(item.getDateTime()));

        }
        return convertView;
    }

    public HoaDonAdapter(@NonNull Context context, int resource, @NonNull List<HoaDon> objects) {
        super(context, resource, objects);
        this.context = context;
    }
}
