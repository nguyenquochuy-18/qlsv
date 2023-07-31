package com.demo.qlsinhvien.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.demo.qlsinhvien.model.MonHoc;
import com.demo.qlsinhvien.model.SinhVien;

public class database extends SQLiteOpenHelper {
    ListView lvSinhVien;
    ArrayAdapter<SinhVien> adapter;
    //Ten DATABASE
    private static String DATABASE_NAME ="QUẢN LÝ SINH VIÊN";

    private static String BANG_SV = "SinhVien";
    private static String HINH="Hinh ";
    private static String MA_SV="MaSV ";
    private static String TEN_SV="TenSV ";
    private static String GIOTINH ="GioiTinh";
    private static String SDT="SDT";

    private static int VERSION=1;

    private static String BANG_MH="MonHoc";
    private static String MA_MH="MaMH ";
    private static String TEN_MH="TenMH";
    private static String SO_TC="SoTinChi";
    private static String SO_TH="SoTietHoc";

    private String SQLQuery1 = " CREATE TABLE " + BANG_MH + " ( " + MA_MH + " integer primary key autoincrement, " + TEN_MH + " TEXT, " + SO_TC + " TEXT, " + SO_TH + " TEXT)";

    private String SQLQuery2=" CREATE TABLE " + BANG_SV + " ( " + MA_SV + " integer primary key AUTOINCREMENT, " + TEN_SV + " TEXT, " + GIOTINH + " TEXT, " + SDT + " TEXT, " + TEN_MH + " TEXT, " + HINH + " Blob)";

    public database(@Nullable Context context) {
        super(context,DATABASE_NAME,null, VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(SQLQuery1);
        sqLiteDatabase.execSQL(SQLQuery2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.setVersion(oldVersion);
    }
    public void AddSinhVien(SinhVien sinhVien){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values =new ContentValues();

        values.put(MA_SV,sinhVien.getMa());
        values.put(TEN_SV,sinhVien.getTen());
        values.put(GIOTINH,sinhVien.getGioiTinh());
        values.put(SDT,sinhVien.getSdt());
        values.put(TEN_MH,sinhVien.getTenmh());
        values.put(HINH,sinhVien.getHinhanh());

        db.insert(BANG_SV, null,values);
        db.close();
    }


    public boolean UpdateSinhVien(SinhVien sinhVien,String sv){
        SQLiteDatabase db =this.getWritableDatabase();

        ContentValues values =new ContentValues();
        values.put(TEN_SV,sinhVien.getTen());
        values.put(GIOTINH,sinhVien.getGioiTinh());
        values.put(SDT,sinhVien.getSdt());
        values.put(TEN_MH,sinhVien.getTenmh());
        values.put(HINH,sinhVien.getHinhanh());
        db.update(BANG_SV,values,MA_SV +" = "+sv,null);
        return true;
    }

    public Cursor getDataSinhVien(){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor=db.rawQuery(" SELECT*FROM " + BANG_SV ,null);
        return cursor;
    }
    public Cursor getDataSinhVien1(String tenmh){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor=db.rawQuery(" SELECT*FROM " + BANG_SV + " where "+ TEN_MH +" like "+"'"+tenmh+"'",null);
        return cursor;
    }

    public int DeleteSinhVien(String i){
        SQLiteDatabase db =this.getWritableDatabase();
        int res=db.delete(BANG_SV,MA_SV+" = "+i,null);
        return res;
    }

    public void  AddMonhoc(MonHoc monHoc){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values =new ContentValues();
        values.put(MA_MH,monHoc.getMaMh());
        values.put(TEN_MH,monHoc.getTenMH());
        values.put(SO_TC,monHoc.getSoTc());
        values.put(SO_TH,monHoc.getSoTh());

        db.insert(BANG_MH, null,values);
        db.close();
    }


    public boolean UpdateMonHoc(MonHoc monHoc,String ma){
        SQLiteDatabase db =this.getWritableDatabase();

        ContentValues values =new ContentValues();

        values.put(TEN_MH,monHoc.getTenMH());
        values.put(SO_TC,monHoc.getSoTc());
        values.put(SO_TH,monHoc.getSoTh());

        db.update(BANG_MH,values,MA_MH+" = "+ma,null);
        return true;
    }

    public Cursor getDataMonHoc(){

        SQLiteDatabase db =getReadableDatabase();
        Cursor cursor=db.rawQuery(" SELECT*FROM "+ BANG_MH,null);
        return cursor;
    }

    public Cursor getDataMonHoc1(){

        SQLiteDatabase db =getReadableDatabase();
        Cursor cursor=db.rawQuery(" SELECT " + TEN_MH+ " FROM "+ BANG_MH ,null);
        return cursor;
    }

    public int DeleteMonHoc(String i){
        //Chu y: getWritetableDatabase la doc va ghi
        // getReadtableDatabase chi doc k ghi
        SQLiteDatabase db =this.getWritableDatabase();
        int res=db.delete(BANG_MH,MA_MH+" = "+i,null);
        return res;
    }



}
