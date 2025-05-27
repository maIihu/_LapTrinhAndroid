package com.example.thuchi;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "baihats_db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_CONTACTS = "baihats";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SINGER = "singer";
    private static final String COLUMN_LIKE = "like1";
    private static final String COLUMN_SHARE = "share";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + COLUMN_ID + " INTEGER ,"
                + COLUMN_NAME + " TEXT, "
                + COLUMN_SINGER + " TEXT, "
                + COLUMN_LIKE + " INTEGER,"
                + COLUMN_SHARE + " INTEGER"
                + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    // Thêm mới
    public void add(BaiHat BaiHat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, BaiHat.getId());
        values.put(COLUMN_NAME, BaiHat.getName());
        values.put(COLUMN_SINGER, BaiHat.getSinger());
        values.put(COLUMN_LIKE, BaiHat.getLike());
        values.put(COLUMN_SHARE, BaiHat.getShare());

        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    // Thêm danh sách
    public void addAll(List<BaiHat> BaiHats) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (BaiHat BaiHat : BaiHats) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, BaiHat.getId());
            values.put(COLUMN_NAME, BaiHat.getName());
            values.put(COLUMN_SINGER, BaiHat.getSinger());
            values.put(COLUMN_LIKE, BaiHat.getLike());
            values.put(COLUMN_SHARE, BaiHat.getShare());

            db.insert(TABLE_CONTACTS, null, values);
        }
        db.close();
    }

    // Cập nhật thông tin liên hệ
    public int updateContact(BaiHat BaiHat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, BaiHat.getName());
        values.put(COLUMN_NAME, BaiHat.getName());
        values.put(COLUMN_SINGER, BaiHat.getSinger());
        values.put(COLUMN_LIKE, BaiHat.getLike());
        values.put(COLUMN_SHARE, BaiHat.getShare());

        return db.update(TABLE_CONTACTS, values, COLUMN_ID + " = ?",
                new String[] {String.valueOf(BaiHat.getId())});
    }

    // Xóa liên hệ
    public void delete(int studentId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, COLUMN_ID + " = ?", new String[] {String.valueOf(studentId)});
        db.close();
    }

    // Lấy danh sách tất cả liên hệ
    @SuppressLint("Range")
    public List<BaiHat> getAll() {
        List<BaiHat> BaiHats = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                BaiHat BaiHat = new BaiHat();
                BaiHat.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                BaiHat.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                BaiHat.setSinger(cursor.getString(cursor.getColumnIndex(COLUMN_SINGER)));
                BaiHat.setLike(cursor.getInt(cursor.getColumnIndex(COLUMN_LIKE)));
                BaiHat.setShare(cursor.getInt(cursor.getColumnIndex(COLUMN_SHARE)));
                BaiHats.add(BaiHat);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return BaiHats;
    }

    // Lấy thông tin 1 liên hệ
    @SuppressLint("Range")
    public BaiHat get(String studentId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { COLUMN_ID,
                        COLUMN_ID, COLUMN_NAME, COLUMN_SINGER, COLUMN_LIKE, COLUMN_SHARE },
                COLUMN_ID + "=?", new String[] { studentId }, null, null, null);

        if (cursor.moveToFirst()) {
            BaiHat BaiHat = new BaiHat();
            BaiHat.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
            BaiHat.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            BaiHat.setSinger(cursor.getString(cursor.getColumnIndex(COLUMN_SINGER)));
            BaiHat.setLike(cursor.getInt(cursor.getColumnIndex(COLUMN_LIKE)));
            BaiHat.setShare(cursor.getInt(cursor.getColumnIndex(COLUMN_SHARE)));
            cursor.close();
            db.close();
            return BaiHat;
        }

        cursor.close();
        db.close();
        return null;
    }

//    // Cập nhật lại số thứ tự trong database
//    public void updateStudentIds() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        int newId = 1;
//        if (cursor.moveToFirst()) {
//            do {
//                @SuppressLint("Range") String currentStudentId = cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_ID));
//                ContentValues values = new ContentValues();
//                values.put(COLUMN_STUDENT_ID, String.valueOf(newId));
//                db.update(TABLE_CONTACTS, values, COLUMN_STUDENT_ID + " = ?",
//                        new String[] { currentStudentId });
//                newId++;
//            } while (cursor.moveToNext());
//        }
//
//        cursor.close();
//        db.close();
//    }
}
