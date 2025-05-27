package com.example.a02_04_2025;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class BaiHatAdapter extends ArrayAdapter<BaiHat> {
    private Context context;
    public BaiHatAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.bai_hat_item, parent, false);
        }
        BaiHat bh = getItem(position);
        if(bh != null){
            TextView txtName = convertView.findViewById(R.id.txtName);
            TextView txtSinger =convertView.findViewById(R.id.txtSinger);
            TextView txtTime = convertView.findViewById(R.id.txtTime);

            txtName.setText(bh.getName());
            txtSinger.setText(bh.getSinger());
            float time = bh.getTime();
            txtTime.setText(String.valueOf(time));
        }
        return convertView;
    }

    public BaiHatAdapter(@NonNull Context context, int resource, @NonNull List<BaiHat> objects) {
        super(context, resource, objects);
        this.context = context;
    }
}
