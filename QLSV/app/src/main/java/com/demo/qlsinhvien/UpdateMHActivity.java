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

public class UpdateMHActivity extends AppCompatActivity {

    EditText edtSuaTenMH, edtSuaSoTC,edtSuaSoTH;
    Button btnSua;
    com.demo.qlsinhvien.database.database database;
    Toolbar toolbar;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_mhactivity);
        toolbar=findViewById(R.id.tlbupdatemh);
        setSupportActionBar(toolbar);
        edtSuaTenMH=findViewById(R.id.edtSuaTenMH);
        edtSuaSoTC=findViewById(R.id.edtSuaSoTC);
        edtSuaSoTH=findViewById(R.id.edtSuaSoTH);
        btnSua=findViewById(R.id.btnSua);

        Intent intent=getIntent();
        String mamh=intent.getStringExtra("mamh");
        String tenmh= intent.getStringExtra("tenmh");
        String sotc= intent.getStringExtra("sotc");
        String soth= intent.getStringExtra("soth");
        edtSuaTenMH.setText(tenmh);
        edtSuaSoTC.setText(sotc);
        edtSuaSoTH.setText(soth);

        database=new database(this);
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUpdate();
            }
        });

    }

    private void DialogUpdate() {
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialogupdate);
        dialog.setCanceledOnTouchOutside(false);
        Button btnyes=dialog.findViewById(R.id.btnYesSua);
        Button btnno=dialog.findViewById(R.id.btnNoSua);
        Intent intent=getIntent();
        String mamh=intent.getStringExtra("mamh");
        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tenmh=edtSuaTenMH.getText().toString().trim();
                String sotc=edtSuaSoTC.getText().toString().trim();
                String soth=edtSuaSoTH.getText().toString().trim();
                if(tenmh.equals("")|| sotc.equals("")||soth.equals("")){
                    Toast.makeText(UpdateMHActivity.this,"Bạn vui lòng điền đầy đủ thông tin!",Toast.LENGTH_SHORT).show();
                }
                else {
                    MonHoc monHoc=updatemonhoc();
                    database.UpdateMonHoc(monHoc,mamh);
                    Intent intent =new Intent(UpdateMHActivity.this,MonhocActivity.class);
                    startActivity(intent);
                    Toast.makeText(UpdateMHActivity.this,"Sửa thành công!",Toast.LENGTH_SHORT).show();
                }
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
    private MonHoc updatemonhoc(){
        String tenmh=edtSuaTenMH.getText().toString().trim();
        String sotc=edtSuaSoTC.getText().toString().trim();
        String soth=edtSuaSoTH.getText().toString().trim();

        MonHoc monHoc=new MonHoc(tenmh,sotc,soth);
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
                Intent intent=new Intent(UpdateMHActivity.this,AboutActivity.class);
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