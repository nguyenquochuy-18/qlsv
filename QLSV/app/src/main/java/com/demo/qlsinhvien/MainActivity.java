package com.demo.qlsinhvien;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    EditText edtTaiKhoan, edtMatKhau;
    Toolbar toolbar;
    ImageButton vn, usa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin=findViewById(R.id.btnLogin);
        vn=findViewById(R.id.VN);
        usa=findViewById(R.id.USA);
        edtTaiKhoan=findViewById(R.id.edtTaiKhoan);
        edtMatKhau=findViewById(R.id.edtMatKhau);

        toolbar=findViewById(R.id.tlbSV);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, MonhocActivity.class);
//                startActivity(intent);
                DialogDangNhap();
            }
        });
        vn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.daNgonNgu(MainActivity.this, "vi");
                Intent intent2 = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent2);
            }
        });
        usa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.daNgonNgu(MainActivity.this, "en");
                Intent intent1 = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });

    }
    //Hien thi thong tin
    private void DialogDangNhap(){
        //Tao doi tuong cua dialog

        if(edtTaiKhoan.getText().toString().equals("quochuy") && edtMatKhau.getText().toString().equals("123456")){
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(MainActivity.this,"Sai tên đăng nhập và mật khẩu !",Toast.LENGTH_LONG);
        }


    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.MenuAbout:
                finish();
                Intent intent = new Intent(MainActivity.this,AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.MenuExit:
                finishAffinity();
                break;

            default:

        }
        return super.onOptionsItemSelected(item);
    }

}