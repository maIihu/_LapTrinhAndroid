package com.example.thuchi3;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ThuChiAdapter extends ArrayAdapter<ThuChi> {
    public ThuChiAdapter(Context context, List<ThuChi> sinhViens) {
        super(context, 0, sinhViens);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // implement đến View
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list, parent, false);
        }

        // Lấy đối tượng từ danh sách
        ThuChi sinhVien = getItem(position);

        // Hiển thị thông tin
        TextView itemName = convertView.findViewById(R.id.itemName);
        TextView itemDate = convertView.findViewById(R.id.itemDate);
        TextView itemCost = convertView.findViewById(R.id.itemCost);

        String loai = "";
        if(sinhVien.getName() == 1) loai = "Thu";
        else loai = "Chi";

        itemName.setText(loai);

        itemDate.setText(sinhVien.getDate());
        itemCost.setText(String.valueOf(sinhVien.getCost()));

        if(sinhVien.getName() == 1) convertView.setBackgroundColor(Color.GREEN);
        else convertView.setBackgroundColor(Color.WHITE);

        return convertView;
    }
}
