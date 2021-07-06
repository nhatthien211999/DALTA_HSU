package com.cuong.project_q;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHandlerActivity extends SQLiteOpenHelper {
    //Database
    private static final String DATABASE_NAME = "Gallery";
    private static final int DATABASE_VERSION = 1;

    //Các table
    //Table album
    private static final String TABLE_POINT = "POINT";
    private static final String POINT_ID = "id";
    private static final String POINT = "point";
    private static final String LEVEL = "level";
    //Các table khác viết sau đây
    //...

    //Singleton
    private static DatabaseHandlerActivity databaseObject = null;

    public static DatabaseHandlerActivity getInstance(Context context) {
        if (databaseObject == null) {
            databaseObject = new DatabaseHandlerActivity(context);
        }
        return databaseObject;

    }

    private DatabaseHandlerActivity(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    //end Singleton

    //
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Mai mốt có nhiều table thì nhó copy 2 dòng này
        //Sửa lại các biến cần thiết để tạo bảng mới


        String create_point_table = String.format("CREATE TABLE %s" +
                "(%s INTEGER , %s INTEGER, %s INTEGER, " +
                "PRIMARY KEY (%s, %s))", TABLE_POINT, POINT_ID, POINT, LEVEL, POINT_ID, LEVEL);
        db.execSQL(create_point_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_point_table = String.format("DROP TABLE IF EXISTS %s", TABLE_POINT);
        db.execSQL(drop_point_table);

        onCreate(db);
    }

    public boolean addPoint(int id, int point, int _level) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(POINT_ID, id);
        values.put(POINT, point);
        values.put(LEVEL, _level);

        db.insert(TABLE_POINT, null, values);
        db.close();
        return true;
    }
//
//    public int getPoint(int idPoint) {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.query(TABLE_POINT, null, POINT_ID + " = ?", new String[]{String.valueOf(idPoint)}, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();
//        int point = cursor.getInt(1);
//        return point;
//    }

    ///COI LẠI CÁI NÀY
    public int getNumberOfPoint(int _level) {
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = String.format(
                "SELECT COUNT(*) " +
                        "FROM %s " + "WHERE %s = %d"
                       , TABLE_POINT, LEVEL, _level);
        Cursor answer = db.rawQuery(sql, null);
        int amount = 0;
        answer.moveToFirst();
        if (answer.isAfterLast() == false)
            amount = answer.getInt(0);
        answer.close();

        db.close();
        return amount;
    }
    //Lấy tất các điểm trong dâtbase
    public List<Integer> getAllPoint(int _level) {

        List<Integer> listImage = new ArrayList<Integer>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM POINT WHERE level = ?",new String[]{String.valueOf(_level)});

        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {

            int point = cursor.getInt(1);
            listImage.add(point);
            cursor.moveToNext();
        }

        cursor.close();

        db.close();
        return listImage;
    }
    public List<Integer> getTopPoint(int _level) {

        List<Integer> listImage = new ArrayList<Integer>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM POINT WHERE level = ? ORDER BY POINT DESC LIMIT 10",new String[]{String.valueOf(_level)});

        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {

            int point = cursor.getInt(1);
            listImage.add(point);
            cursor.moveToNext();
        }

        cursor.close();

        db.close();
        return listImage;
    }
}