package com.example.thuchi3;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    FloatingActionButton fab;
    ListView listView;
    List<ThuChi> list;
    ThuChiAdapter adapter;
    TextView txtSoDu;
    Button btnSearch;
    SearchView searchView;
    private static final int EDIT_REQUEST_CODE = 1;
    private static final int ADD_REQUEST_CODE = 2;
    private NetworkChangeReceiver networkChangeReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);

        listView = findViewById(R.id.listView);
        setupListView(); // Thiết lập ListView

        txtSoDu = findViewById(R.id.txtSoDu);
        double soDu = 0;
        for (var x: list) {
            if(x.getName() == 1) soDu += x.getCost();
            else soDu -= x.getCost();
        }
        txtSoDu.setText("So du " + soDu);

        registerForContextMenu(listView);

        btnSearch = findViewById(R.id.btnSearch);
        searchView = findViewById(R.id.searchView);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = searchView.getQuery().toString();
                if(!query.equals("")){
                    int loai = Integer.parseInt(query);
                    ArrayList<ThuChi> searchList = new ArrayList<>();
                    for (var x : list) {
                        if(loai == x.getName()) searchList.add(x);
                    }
                    adapter = new ThuChiAdapter(MainActivity.this, searchList);
                    listView.setAdapter(adapter);
                }
                else{
                    adapter = new ThuChiAdapter(MainActivity.this, list);
                    listView.setAdapter(adapter);
                }
            }
        });
    }

    private void setupListView() {
        list = new ArrayList<>();
        list = dbHelper.getAll();
        if (list.isEmpty()) {
            // Nếu danh sách rỗng, thêm một số dữ liệu mẫu
            list.add(new ThuChi("1", 1, "15/01/2023", 10000000));
            list.add(new ThuChi("2", 1, "30/01/2023", 12000000));
            list.add(new ThuChi("3", 0, "28/01/2023", 4100000));
            list.add(new ThuChi("4", 0, "20/01/2023", 2200000));

            dbHelper.addAll(list);
        }
        // Tạo adapter và gán cho ListView
        adapter = new ThuChiAdapter(this, list);
        listView.setAdapter(adapter);
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
//            SinhVien sinhVien = list.get(position);
//            Intent intent = new Intent(MainActivity.this, EditActivity.class);
//            intent.putExtra("position", position);
//            intent.putExtra("id", sinhVien.getId());
//            intent.putExtra("name", sinhVien.getName());
//            intent.putExtra("number", sinhVien.getPhone());
//            startActivityForResult(intent, EDIT_REQUEST_CODE);
            return true;
        } else if (item.getItemId() == R.id.menu_delete) {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Xác nhận xóa")
                    .setMessage("ban có chắc muốn xóa không?")
                    .setPositiveButton("Xóa", (dialog, which) -> {
                        // Xử lý xóa (ví dụ: hiển thị toast)
                        ThuChi sinhVien = list.get(position);
                        dbHelper.delete(sinhVien.getId());
                        list.remove(position);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(this, "Đã xóa mục " + position, Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Hủy", null)
                    .show();

        } else {
            return super.onContextItemSelected(item);
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbHelper != null) {
            dbHelper.close();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        networkChangeReceiver = new NetworkChangeReceiver();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(networkChangeReceiver);
    }

}