package com.demo.qlsinhvien;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.demo.qlsinhvien.adapter.adapterMonHoc;
import com.demo.qlsinhvien.database.database;
import com.demo.qlsinhvien.model.MonHoc;

import java.util.ArrayList;

public class MonhocActivity extends AppCompatActivity {

    Toolbar toolbar;
    private ListView lvMonHoc;
    ArrayList<MonHoc> ArrayListMonhoc;
    ArrayList<String> dsmh=new ArrayList<>();

    com.demo.qlsinhvien.database.database database;
    com.demo.qlsinhvien.adapter.adapterMonHoc adapterMonHoc;
    int count=0,i=0;

    String arr[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monhoc);

        toolbar =findViewById(R.id.tlbMonhoc);
        lvMonHoc=findViewById(R.id.lvMonhoc);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database =new database(this);
        ArrayListMonhoc=new ArrayList<>();

        Cursor cursor = database.getDataMonHoc();
        while (cursor.moveToNext()){
            String mamh=cursor.getString(0);
            String ten=cursor.getString(1);
            String sotc=cursor.getString(2);
            String soth=cursor.getString(3);
            ArrayListMonhoc.add(new MonHoc(mamh,ten,sotc,soth));
//            arr[i]=cursor.getString(1);
//            i++;

        }

        adapterMonHoc =new adapterMonHoc(MonhocActivity.this,ArrayListMonhoc);
        lvMonHoc.setAdapter(adapterMonHoc);
        cursor.moveToFirst();
        cursor.close();


        lvMonHoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MonhocActivity.this,StudentActivity.class);
                String ten=ArrayListMonhoc.get(i).getTenMH();
                intent.putExtra("tenmh",ten);

                startActivity(intent);
            }
        });
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

            case R.id.MenuAbout:
                Intent intent2=new Intent(MonhocActivity.this,AboutActivity.class);
                startActivity(intent2);
                break;
            case R.id.MenuExit:
                finishAffinity();
                break;
            case R.id.menuadd:
                Intent intent=new Intent(MonhocActivity.this,AddMHActivity.class);
                startActivity(intent);
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
            Intent intent=new Intent(MonhocActivity.this,MainActivity2.class);
            startActivity(intent);
            finish();
        }
    }

    public void information(final  String pos){
        Cursor cursor =database.getDataMonHoc();
        while(cursor.moveToNext()){
            String mamh=cursor.getString(0);
            if(mamh.equals(pos)){
                Intent intent= new Intent(MonhocActivity.this, InformationMHActivity.class);
                intent.putExtra("maMH: ",mamh);
                String tenmh= cursor.getString(1);
                String sotc=cursor.getString(2);
                String soth=cursor.getString(3);
                intent.putExtra("tenmh",tenmh);
                intent.putExtra("sotc",sotc);
                intent.putExtra("soth",soth);
                startActivity(intent);
            }

        }
    }

    public void delete(final String position, String name){
        database=new database(MonhocActivity.this);

        Cursor cursor=database.getDataSinhVien1(name);
        if(cursor.getCount()==0){
            database.DeleteMonHoc(position);
            database.DeleteSinhVien(position);
            Intent intent=new Intent(MonhocActivity.this, MonhocActivity.class);
            startActivity(intent);
        }
        else {
            Dialog dialog=new Dialog(this);
            dialog.setContentView(R.layout.dialogdeletemonhoc);
            dialog.setCanceledOnTouchOutside(false);
            Button btnyes=dialog.findViewById(R.id.btnYesXoa);
            Button btnno=dialog.findViewById(R.id.btnNoXoa);

            btnyes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    database.DeleteMonHoc(position);
                    database.DeleteSinhVien(position);
                    Intent intent=new Intent(MonhocActivity.this, MonhocActivity.class);
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
    }

    public void update(final String pos){
        Cursor cursor=database.getDataMonHoc();
        while (cursor.moveToNext()){
            String id=cursor.getString(0);
            if(id.equals(pos)){
                Intent intent=new Intent(MonhocActivity.this,UpdateMHActivity.class);
                intent.putExtra("mamh",id);
                String tenmh= cursor.getString(1);
                String sotc=cursor.getString(2);
                String soth=cursor.getString(3);
                intent.putExtra("tenmh",tenmh);
                intent.putExtra("sotc",sotc);
                intent.putExtra("soth",soth);
                startActivity(intent);
            }
        }
    }
}