package com.example.sanpham;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sanpham.SanPham;

import java.util.ArrayList;
import java.util.List;

public class SanPhamAdapter extends ArrayAdapter<SanPham> {
    private Context context;
    public SanPhamAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.san_pham_item, parent, false);
        }
        SanPham sanPham = getItem(position);
        if(sanPham != null){
            TextView txtName = convertView.findViewById(R.id.txtTen);
            TextView txtNumber = convertView.findViewById(R.id.txtGia);
            TextView txtKhuyeMai = convertView.findViewById(R.id.txtKhuyenMai);

            txtName.setText(sanPham.getTenSp());
            txtNumber.setText(String.valueOf(sanPham.getGiaTien()));
            String discount = "";
            if(sanPham.isKhuyenMai()){
                discount = "Giam gia con: " + String.valueOf(sanPham.getGiaTien() * 0.9);
            }
            txtKhuyeMai.setText(discount);

        }
        return convertView;
    }

    public SanPhamAdapter(@NonNull Context context, int resource, @NonNull List<SanPham> objects) {
        super(context, resource, objects);
        this.context = context;
    }
}
