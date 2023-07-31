package com.demo.qlsinhvien;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.demo.qlsinhvien.database.database;
import com.demo.qlsinhvien.model.MonHoc;

public class AddMHActivity extends AppCompatActivity {


    Button btnAddMH ;
    EditText edtSoTH, edtSoTC, edtMaMH, edtTenMH;
    com.demo.qlsinhvien.database.database database;
    Toolbar toolbar;

    int count=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mhactivity);
        toolbar=findViewById(R.id.tlbaddmh);
        setSupportActionBar(toolbar);
        btnAddMH =findViewById(R.id.btnAdd);
        edtMaMH=findViewById(R.id.edtMaMH);
        edtTenMH=findViewById(R.id.edtTenMH);
        edtSoTC=findViewById(R.id.edtSoTC);
        edtSoTH=findViewById(R.id.edtSoTH);

        database=new database(this);

        btnAddMH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAdd();
            }
        });


    }

    public void DialogAdd(){
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialogadd);
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes =dialog.findViewById(R.id.btnYes);
        Button btnNo=dialog.findViewById(R.id.btnNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mamh=edtMaMH.getText().toString().trim();
                String tenMH=edtTenMH.getText().toString().trim();
                String sotc=edtSoTC.getText().toString().trim();
                String soth=edtSoTH.getText().toString().trim();

                if(mamh.equals("")||tenMH.equals("")|| sotc.equals("")||soth.equals("")){
                    Toast.makeText(AddMHActivity.this,"Nhập đúng thông tin!",Toast.LENGTH_SHORT).show();
                }
                else {
                    MonHoc monHoc=CreateMonhoc();
                    database.AddMonhoc(monHoc);
                    Intent intent =new Intent(AddMHActivity.this, MonhocActivity.class);
                    startActivity(intent);

                    Toast.makeText(AddMHActivity.this,"thành công",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
    private MonHoc CreateMonhoc(){

        String mamh=edtMaMH.getText().toString().trim();
        String tenMH=edtTenMH.getText().toString().trim();
        String sotc=(edtSoTC.getText().toString().trim());
        String soth=(edtSoTH.getText().toString().trim());

        MonHoc monHoc=new MonHoc(mamh, tenMH,sotc,soth);
        return monHoc;
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.MenuAbout:
                Intent intent2=new Intent(AddMHActivity.this,AboutActivity.class);
                startActivity(intent2);
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