package com.example.thuchi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class SqlLite extends SQLiteOpenHelper {
    private static final String DB_NAME = "SQL";
    private static final int VERSION = 1;
    private static final String TABLE_NAME = "HoaDon";
    private static final String MA = "MA";
    private static final String HO_TEN = "HO_TEN";
    private static final String NGAY_THANG = "NGAY_THANG";
    private static final String DON_GIA = "DON_GIA";
    private static final String SO_NGAY = "SO_NGAY";
    private SQLiteDatabase sqLiteDatabase;

    public SqlLite(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String QUERY = "CREATE TABLE " + TABLE_NAME + " ( "
                + MA + " TEXT NOT NULL, "
                + HO_TEN + " TEXT NOT NULL, "
                + NGAY_THANG + " TEXT NOT NULL, "
                + DON_GIA + " REAL NOT NULL, "
                + SO_NGAY + " INTEGER NOT NULL "
                + " )";

        db.execSQL(QUERY);

        insertHoaDon(db, "Vu Truong An", "22/5/2024", 2280000, 2);
        insertHoaDon(db, "Le Hai Ha", "20/4/2024", 3420000, 1);
        insertHoaDon(db, "Le Dinh Duc", "21/4/2024", 3420000, 3);
        insertHoaDon(db, "Mai Thuy Linh", "18/5/2024", 3420000, 1);


    }

    private String generateNewId(SQLiteDatabase db) {
        String query = "SELECT " + MA + " FROM " + TABLE_NAME + " ORDER BY " + MA + " DESC LIMIT 1";
        Cursor cursor = db.rawQuery(query, null);

        int newNumber = 11; // Mặc định nếu chưa có hóa đơn nào

        if (cursor.moveToFirst()) {
            String lastId = cursor.getString(cursor.getColumnIndexOrThrow(MA)); // ví dụ: HD20
            try {
                int lastNumber = Integer.parseInt(lastId.replaceAll("\\D+", "")); // lấy 20 từ HD20
                newNumber = lastNumber + 9; // tăng thêm 9
            } catch (NumberFormatException e) {
                e.printStackTrace(); // đề phòng ID sai định dạng
            }
        }

        cursor.close();
        return "HD" + newNumber;
    }


    private void insertHoaDon(SQLiteDatabase db, String hoTen, String ngayThang, double donGia, int soNgay){
        ContentValues contentValues = new ContentValues();

        // Chuyển Date sang String
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//        String ngayThangStr = sdf.format(ngayThang);

        String ma = generateNewId(db);

        contentValues.put(MA, ma);
        contentValues.put(HO_TEN, hoTen);
        contentValues.put(NGAY_THANG, ngayThang);
        contentValues.put(DON_GIA, donGia);
        contentValues.put(SO_NGAY, soNgay);

        db.insert(TABLE_NAME, null, contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public synchronized void close() {
        if(sqLiteDatabase != null && sqLiteDatabase.isOpen())
            sqLiteDatabase.close();

        super.close();
    }

    public long insertTable(String hoTen, Date ngayThang, double donGia, int soNgay){
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase db = getWritableDatabase();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String ngayThangStr = sdf.format(ngayThang);

        String ma = generateNewId(db);

        contentValues.put(MA, ma);
        contentValues.put(HO_TEN, hoTen);
        contentValues.put(NGAY_THANG, ngayThangStr);
        contentValues.put(DON_GIA, donGia);
        contentValues.put(SO_NGAY, soNgay);

        return db.insert(TABLE_NAME, null, contentValues);
    }

    public ArrayList<HoaDon> getList(){
        ArrayList<HoaDon> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME , null);

        if(cursor.moveToFirst()){
            do{
                String id = cursor.getString(cursor.getColumnIndexOrThrow(MA));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(HO_TEN));
                String dateString = cursor.getString(cursor.getColumnIndexOrThrow(NGAY_THANG));
                double cost = cursor.getInt(cursor.getColumnIndexOrThrow(DON_GIA));
                int timeLimit = cursor.getInt(cursor.getColumnIndexOrThrow(SO_NGAY));

                // chuyển String qua Date
                SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy", Locale.getDefault());
                Date date = null;
                try {
                    date = sdf.parse(dateString);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                HoaDon hoaDon = new HoaDon(id, name, date, cost, timeLimit);
                list.add(hoaDon);

            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }


}
