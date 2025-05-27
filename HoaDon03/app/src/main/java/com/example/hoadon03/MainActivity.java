package com.example.hoadon03;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    FloatingActionButton fab;
    ListView listView;
    List<HoaDon> list;
    HoaDonAdapter adapter;
    private static final int EDIT_REQUEST_CODE = 1;
    private static final int ADD_REQUEST_CODE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        listView = findViewById(R.id.listView);

        setupListView();

        setClickSort();
        registerForContextMenu(listView); // Đăng ký context menu cho ListView

    }

    private void setupListView() {
        list = new ArrayList<>();
        list = dbHelper.getAll();
        if (list.isEmpty()) {
            // Nếu danh sách rỗng, thêm một số dữ liệu mẫu
            list.add(new HoaDon("Vu Truong An", "22/5/2024", 10000, 30));
            list.add(new HoaDon("Le Hai Ha", "20/4/2024", 20000, 30));
            list.add(new HoaDon("Le Dinh Duc", "21/4/2024", 300000, 30));
            list.add(new HoaDon("Mai Thuy Linh", "18/5/2024", 100000, 30));

            dbHelper.addAll(list);
        }
        // Tạo adapter và gán cho ListView
        adapter = new HoaDonAdapter(this, list);
        listView.setAdapter(adapter);
    }

    private void setClickSort() {
        View btnSort = findViewById(R.id.btnSort);
        btnSort.setOnClickListener(view -> {
            list.sort(Comparator.comparing(HoaDon::tinhTien).reversed()); // Sắp xếp theo tiền
            adapter.notifyDataSetChanged();
        });
    }

    // mo contextmenu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu); // Load menu
    }

    // Xử lý sự kiện khi chọn mục trong context menu
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        if (item.getItemId() == R.id.menu_edit) {
            // Xử lý chỉnh sửa (ví dụ: hiển thị toast)
            // Mở EditActivity
//            HoaDon HoaDon = list.get(position);
//            Intent intent = new Intent(MainActivity.this, EditActivity.class);
//            intent.putExtra("position", position);
//            intent.putExtra("id", HoaDon.getId());
//            intent.putExtra("name", HoaDon.getName());
//            intent.putExtra("number", HoaDon.getPhone());
//            startActivityForResult(intent, EDIT_REQUEST_CODE);
//            return true;
        } else if (item.getItemId() == R.id.menu_delete) {
            HoaDon selected = list.get(position); // Hóa đơn được chọn
            double selectedAmount = selected.tinhTien(); // Lấy số tiền

            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Xác nhận xóa")
                    .setMessage("Bạn có chắc muốn xóa hóa đơn này và các hóa đơn có tổng tiền <= " + selectedAmount + " không?")
                    .setPositiveButton("Xóa", (dialog, which) -> {
                        List<HoaDon> toRemove = new ArrayList<>();

                        for (HoaDon hd : list) {
                            if (hd.tinhTien() <= selectedAmount) {
                                dbHelper.delete(hd.getId());  // Xóa khỏi database
                                toRemove.add(hd);             // Ghi lại để xóa khỏi list
                            }
                        }

                        list.removeAll(toRemove); // Xóa khỏi list đang hiển thị
                        adapter.notifyDataSetChanged(); // Cập nhật giao diện

                        Toast.makeText(this, "Đã xóa " + toRemove.size() + " hóa đơn", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Hủy", null)
                    .show();
        }
        else {
            return super.onContextItemSelected(item);
        }
        return false;
    }
}