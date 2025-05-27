package com.example.thuchi;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
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
    List<BaiHat> list;
    BaiHatAdapter adapter;
    TextView txtTB;

    private static final int EDIT_REQUEST_CODE = 1;
    private static final int ADD_REQUEST_CODE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        listView = findViewById(R.id.listView);
        setupListView(); // Thiết lập ListView

        registerForContextMenu(listView); // Đăng ký context menu cho ListView
        double sum = 0;
        for (BaiHat BaiHat : list) {
            sum += BaiHat.tinhDiem();
        }
        txtTB.setText(String.format("Tổng điểm: %s", sum / list.size()));
    }



    private void setupListView() {
        list = new ArrayList<>();
        list = dbHelper.getAll();
        if (list.isEmpty()) {
            // Nếu danh sách rỗng, thêm một số dữ liệu mẫu
            int newID = 68;
            list.add(new BaiHat(newID, "Ha Noi Mua Thu", "My Tam", 105, 10));
            list.add(new BaiHat(newID + 14, "Phut Cuoi", "My Linh", 117, 10));
            list.add(new BaiHat(newID + 14 + 14, "Got Hong", "Quang Dung", 157, 21));
            list.add(new BaiHat(newID + 14 + 14 + 14, "Ba Toi", "Ha Anh", 78, 8));


            dbHelper.addAll(list);
        }
        list.sort(Comparator.comparing(BaiHat::getTenCuoiCung).reversed());
        // Tạo adapter và gán cho ListView
        adapter = new BaiHatAdapter(this, list);
        listView.setAdapter(adapter);

        txtTB = findViewById(R.id.txtTB);

    }

    // mo contextmenu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu); // Load menu
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        if (item.getItemId() == R.id.menu_edit) {
            // Xử lý chỉnh sửa (ví dụ: hiển thị toast)
            // Mở EditActivity
//            BaiHat BaiHat = list.get(position);
//            Intent intent = new Intent(MainActivity.this, EditActivity.class);
//            intent.putExtra("position", position);
//            intent.putExtra("id", BaiHat.getId());
//            intent.putExtra("name", BaiHat.getName());
//            intent.putExtra("number", BaiHat.getPhone());
//            startActivityForResult(intent, EDIT_REQUEST_CODE);
            return true;
        } else if (item.getItemId() == R.id.menu_delete) {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Xác nhận xóa")
                    .setMessage("ban có chắc muốn xóa không?")
                    .setPositiveButton("Xóa", (dialog, which) -> {
                        // Xử lý xóa (ví dụ: hiển thị toast)
                        BaiHat BaiHat = list.get(position);
                        dbHelper.delete(BaiHat.getId());
                        list.remove(position);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(this, "Đã xóa mục " + position, Toast.LENGTH_SHORT).show();
                        if (!list.isEmpty()) {
                            double sum = 0;
                            for (BaiHat bh : list) {
                                sum += bh.tinhDiem();
                            }
                            txtTB.setText(String.format("Tổng điểm: %.2f", sum / list.size()));
                        } else {
                            txtTB.setText("Danh sách rỗng");
                        }
                        adapter.notifyDataSetChanged();
                    })
                    .setNegativeButton("Hủy", null)
                    .show();


        } else {
            return super.onContextItemSelected(item);
        }
        return false;
    }
}