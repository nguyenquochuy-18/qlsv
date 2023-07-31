package com.demo.qlsinhvien;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.demo.qlsinhvien.model.SinhVien;

import java.util.ArrayList;

public class InformationSVActivity extends AppCompatActivity {

    TextView txttensv, txtmasv, txtphai, txtsodt, txtdiachi;
    ImageView imginfosv;
    ArrayList<SinhVien> ArrayListSinhVien;
    Toolbar toolbar;
    int count=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_svactivity2);

        toolbar=findViewById(R.id.tlbInfosv);
        setSupportActionBar(toolbar);


        txttensv=findViewById(R.id.txttensv);
        txtmasv=findViewById(R.id.txtmasv);
        txtphai=findViewById(R.id.txtphai);
        txtsodt=findViewById(R.id.txtsdt);
        txtdiachi=findViewById(R.id.txtdiachi);
        imginfosv=findViewById(R.id.imginfosv);
        Intent intent=getIntent();
        String mamh=intent.getStringExtra("maMH");
        String tensv=intent.getStringExtra("tensv");
        String masv=intent.getStringExtra("masv");
        String phai=intent.getStringExtra("phai");
        String sdt=intent.getStringExtra("sdt");
        String diachi=intent.getStringExtra("diachi");
        byte[] anhsv= intent.getByteArrayExtra("anhsv");
        Bitmap bitmap= BitmapFactory.decodeByteArray(anhsv,0,anhsv.length);
        imginfosv.setImageBitmap(bitmap);
        txttensv.setText(tensv);
        txtmasv.setText(masv);
        txtphai.setText(phai);
        txtsodt.setText(sdt);
        txtdiachi.setText(diachi);


    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.MenuAbout:
                Intent intent=new Intent(InformationSVActivity.this,AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.MenuExit:
                finishAffinity();
                break;


            default:

                break;

        }
        return super.onOptionsItemSelected(item);
    }


}