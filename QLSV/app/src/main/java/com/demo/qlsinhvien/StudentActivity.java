package com.demo.qlsinhvien;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.demo.qlsinhvien.adapter.adapterSinhVien;
import com.demo.qlsinhvien.database.database;
import com.demo.qlsinhvien.model.SinhVien;

import java.util.ArrayList;

public class StudentActivity extends AppCompatActivity {

    Toolbar toolbar;

    private ListView lvSinhVien;
    ArrayList<SinhVien> ArrayListSinhVien;

    com.demo.qlsinhvien.database.database database;
    com.demo.qlsinhvien.adapter.adapterSinhVien adapterSinhVien;
    int count=0;
    String tenmh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        toolbar =findViewById(R.id.tlbSinhVien);
        lvSinhVien=findViewById(R.id.lvSinhVien);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database =new database(this);
        ArrayListSinhVien=new ArrayList<>();

        Intent intent=getIntent();
        tenmh= intent.getStringExtra("tenmh");
        Cursor cursor = database.getDataSinhVien();
        while (cursor.moveToNext()){
            String tenMH=cursor.getString(4);
           if(tenmh.equals(tenMH)){
               String masv=cursor.getString(0);
               String gioitinh=cursor.getString(2);
               String sdt=cursor.getString(3);
               String tensv=cursor.getString(1);
               byte[] anh=cursor.getBlob(5);
               ArrayListSinhVien.add(new SinhVien(masv,tensv,gioitinh,sdt,tenMH,anh));
           }
        }
        adapterSinhVien =new adapterSinhVien(StudentActivity.this,ArrayListSinhVien);
        lvSinhVien.setAdapter(adapterSinhVien);
        cursor.moveToFirst();
        cursor.close();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuadd,menu);
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuadd:
                Intent intent=new Intent(StudentActivity.this,AddSVActivity.class);
                startActivity(intent);
                break;
            case R.id.MenuAbout:
                Intent intent2=new Intent(StudentActivity.this,AboutActivity.class);
                startActivity(intent2);
                break;
            case R.id.MenuExit:
                finishAffinity();
                break;
            default:
                onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        count++;
        if (count>=1){
            Intent intent=new Intent(StudentActivity.this,MonhocActivity.class);
//            intent.putExtra("maMH",mamh);
            startActivity(intent);
            finish();
        }
    }

    public void informationSV(final  String pos){
        Cursor cursor =database.getDataSinhVien();
        while(cursor.moveToNext()){
            String masv=cursor.getString(0);
            if(masv.equals(pos)){
                Intent intent= new Intent(StudentActivity.this, InformationSVActivity.class);
                intent.putExtra("masv",masv);

                String tensv= cursor.getString(1);
                String phai=cursor.getString(2);
                String sdt=cursor.getString(3);
                String diachi=cursor.getString(4);
                byte[] anhsv=cursor.getBlob(5);

                intent.putExtra("tensv",tensv);
                intent.putExtra("phai",phai);
                intent.putExtra("sdt",sdt);
                intent.putExtra("diachi",diachi);
                intent.putExtra("anhsv",anhsv);
                startActivity(intent);
            }

        }
    }

    public void delete(final String position){
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialogdeletesinhvien);
        dialog.setCanceledOnTouchOutside(false);
        Button btnyes=dialog.findViewById(R.id.btnYesXoaSV);
        Button btnno=dialog.findViewById(R.id.btnNoXoaSV);

        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                database=new database(StudentActivity.this);
                database.DeleteSinhVien(position);
                Intent intent=new Intent(StudentActivity.this, StudentActivity.class);
//                intent.putExtra("maMH",mamh);
                startActivity(intent);
            }

        });
        btnno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
    public void updatesv(final  String maSV){

        Cursor cursor =database.getDataSinhVien();
        while(cursor.moveToNext()){
            String masv=cursor.getString(0);
            if(masv.equals(maSV)){
                Intent intent= new Intent(StudentActivity.this, UpdateSVActivity2.class);
                intent.putExtra("masv",maSV);

                String tensv= cursor.getString(1);
                String phai=cursor.getString(2);
                String sdt=cursor.getString(3);
                String diachi=cursor.getString(4);
                byte[] anhsv=cursor.getBlob(5);

                intent.putExtra("tensv",tensv);
                intent.putExtra("phai",phai);
                intent.putExtra("sdt",sdt);
                intent.putExtra("diachi",diachi);
                intent.putExtra("anhsv",anhsv);

                startActivity(intent);
            }

        }
        cursor.close();
    }
}