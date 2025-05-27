package com.example.hoadon03;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class HoaDonAdapter extends ArrayAdapter<HoaDon> {

    public HoaDonAdapter(Context context, List<HoaDon> HoaDons) {
        super(context, 0, HoaDons);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // implement đến View
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list, parent, false);
        }

        // Lấy đối tượng từ danh sách
        HoaDon HoaDon = getItem(position);

        // Hiển thị thông tin
        TextView itemName = convertView.findViewById(R.id.itemName);
        TextView itemDate = convertView.findViewById(R.id.itemDate);
        TextView itemCost = convertView.findViewById(R.id.itemCost);

        itemName.setText(HoaDon.getName());
        itemDate.setText(HoaDon.getDate());
        itemCost.setText(String.valueOf(HoaDon.tinhTien()));

        if(HoaDon.tinhTien() >= 3000000) convertView.setBackgroundColor(Color.RED);
        else convertView.setBackgroundColor(Color.WHITE);

        return convertView;
    }
}
