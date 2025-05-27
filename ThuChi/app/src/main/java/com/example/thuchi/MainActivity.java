package com.example.thuchi;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    EditText edtTim;
    TextView txtSoDu;
    ListView listView;
    Button btnXep;

    SqlLite sqlLite;
    HoaDonAdapter hoaDonAdapter;
    ArrayList<HoaDon> hoaDons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edtTim = findViewById(R.id.edtTimKiem);
        txtSoDu = findViewById(R.id.txtSoSu);
        listView = findViewById(R.id.lstView);
        btnXep = findViewById(R.id.btnXep);

        sqlLite = new SqlLite(this);
        hoaDons = sqlLite.getList();

        hoaDonAdapter = new HoaDonAdapter(MainActivity.this, R.layout.hoa_don_item, hoaDons);

        listView.setAdapter(hoaDonAdapter);

        double tienTB = 0;
        for (var hoadon: hoaDons) {
            tienTB += hoadon.tinhTienHD();
        }
        tienTB /= hoaDons.size();

        txtSoDu.setText(String.format("Tien hoa don trung binh %s", tienTB));

        btnXep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(hoaDons, new Comparator<HoaDon>() {
                    @Override
                    public int compare(HoaDon h1, HoaDon h2) {
                        return Double.compare(h1.tinhTienHD(), h2.tinhTienHD());
                    }
                });

                hoaDonAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onCreateContextMenu(android.view.ContextMenu menu, android.view.View v, android.view.ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu, menu);
    }
    @Override
    public boolean onContextItemSelected(android.view.MenuItem item) {
        android.widget.AdapterView.AdapterContextMenuInfo info = (android.widget.AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position - listView.getHeaderViewsCount(); // Điều chỉnh vị trí vì có header
        HoaDon hoaDon = hoaDons.get(position); // Lấy trực tiếp từ hoaDons

        int itemId = item.getItemId();
        if (itemId == R.id.menu_delete) {
            new AlertDialog.Builder(this)
                    .setTitle("Xác nhận xoá")
                    .setMessage("Bạn có chắc chắn muốn xoá hóa đơn có số tiền " + hoaDon.getTongTien() + " này?")
                    .setPositiveButton("Xoá", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dataHelper.deleteHoaDon(hoaDon.getMaHoaDon());
                            hoaDons.remove(position); // Xóa khỏi danh sách
                            hoaDonAdapter.notifyDataSetChanged(); // Cập nhật giao diện

                            // Cập nhật lại trung bình tổng tiền
                            double averageTotal = calculateAverageTotal(hoaDons);
                            View headerView = listView.getChildAt(0); // Lấy header view
                            if (headerView != null) {
                                TextView tvAverageTotal = headerView.findViewById(R.id.tvAverageTotal);
                                tvAverageTotal.setText(String.format("%.2f", averageTotal));
                            }
                        }
                    })
                    .setNegativeButton("Huỷ", null)
                    .show();
            return true;
        } else if (itemId == R.id.menu_edit) {
            Intent intent = new Intent(MainActivity.this, EditActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("id", hoaDon.getMaHoaDon());
            bundle.putString("name", hoaDon.getTenKhachHang());
            bundle.putString("day", hoaDon.getNgayLap());
            bundle.putInt("dongia", hoaDon.getDonGia());
            bundle.putInt("songayluutru", hoaDon.getSoNgayLuuTru());
            intent.putExtra("hoadon", bundle);
            startActivity(intent);
            return true;
        } else {
            return super.onContextItemSelected(item);
        }
    }
}