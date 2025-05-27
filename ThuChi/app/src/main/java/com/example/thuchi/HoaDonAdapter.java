package com.example.thuchi;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.ColorInt;
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
        HoaDon hoaDon = getItem(position);
        if(hoaDon != null){
            TextView txtTen = convertView.findViewById(R.id.txtTen);
            TextView txtTien = convertView.findViewById(R.id.txtTien);
            TextView txtNgay = convertView.findViewById(R.id.txtNgay);

            txtTen.setText(String.valueOf(hoaDon.getHoTen()));
            txtTien.setText(String.valueOf(hoaDon.tinhTienHD()));
            txtNgay.setText(String.valueOf(hoaDon.getSoNgay()));

            if(hoaDon.tinhTienHD() >= 3000000){
                convertView.setBackgroundColor(Color.BLUE);
            }
            else{
                convertView.setBackgroundColor(Color.WHITE);
            }
        }
        return convertView;
    }

    public HoaDonAdapter(@NonNull Context context, int resource, @NonNull List<HoaDon> objects) {
        super(context, resource, objects);
        this.context = context;
    }
}
