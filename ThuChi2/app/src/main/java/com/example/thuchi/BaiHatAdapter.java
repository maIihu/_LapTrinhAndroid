package com.example.thuchi;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class BaiHatAdapter extends ArrayAdapter<BaiHat> {
    public BaiHatAdapter(Context context, List<BaiHat> BaiHats) {
        super(context, 0, BaiHats);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // implement đến View
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list, parent, false);
        }

        // Lấy đối tượng từ danh sách
        BaiHat BaiHat = getItem(position);

        // Hiển thị thông tin
        TextView itemName = convertView.findViewById(R.id.itemName);
        TextView itemScore = convertView.findViewById(R.id.itemScore);
        TextView itemSinger = convertView.findViewById(R.id.itemSinger);
        TextView itemLike = convertView.findViewById(R.id.itemLike);
        TextView itemShare = convertView.findViewById(R.id.itemShare);

        itemName.setText(BaiHat.getName());
        itemScore.setText(String.valueOf(BaiHat.tinhDiem()));
        itemSinger.setText(BaiHat.getSinger());
        itemLike.setText(String.valueOf(BaiHat.getLike()));
        itemShare.setText(String.valueOf(BaiHat.getShare()));

        if(BaiHat.tinhDiem() >= 160) convertView.setBackgroundColor(Color.RED);
        else convertView.setBackgroundColor(Color.WHITE);

        return convertView;
    }
}
