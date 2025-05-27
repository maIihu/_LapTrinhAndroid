package com.example.sanpham;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.sanpham.SanPham;

import java.util.ArrayList;

public class SqlLite extends SQLiteOpenHelper {
    private static final String DB_NAME = "SQL";
    private static final int VERSION = 1;
    private static final String TABLE_NAME = "SanPham";
    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String COST = "COST";
    private static final String DISCOUNT = "DISCOUNT";
    private SQLiteDatabase sqLiteDatabase;

    public SqlLite(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String QUERY = "CREATE TABLE " + TABLE_NAME + " ( "
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " TEXT NOT NULL, "
                + COST + " INTEGER NOT NULL, "
                + DISCOUNT + " INTEGER NOT NULL"
                + " )";
        db.execSQL(QUERY);

        insertSanPham(db, "Tu Lanh", 1000, false);
        insertSanPham(db, "Ti vi", 3000, false);
        insertSanPham(db, "Ti vihêh", 222222000, true);
        insertSanPham(db, "Ti vihê2222h", 222222000, true);
    }

    private void insertSanPham(SQLiteDatabase db,  String name, int giaTien, boolean khuyenMai){
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(COST, giaTien);
        contentValues.put(DISCOUNT, khuyenMai);
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

    public long insertTable(String name, int giaTien, boolean khuyenMai){
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase db = getWritableDatabase();
        contentValues.put(NAME, name);
        contentValues.put(COST, giaTien);
        contentValues.put(DISCOUNT, khuyenMai);
        return db.insert(TABLE_NAME, null, contentValues);
    }

    public ArrayList<SanPham> getList(){
        ArrayList<SanPham> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY " + NAME , null);

        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(NAME));
                int cost = cursor.getInt(cursor.getColumnIndexOrThrow(COST));
                boolean discount = cursor.getInt(cursor.getColumnIndexOrThrow(DISCOUNT)) == 1;

                SanPham sanPham = new SanPham(id, name, cost, discount);
                list.add(sanPham);
            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }
}
