package com.demo.qlsinhvien;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class InformationMHActivity extends AppCompatActivity {

    TextView txttenmh, txtsotc, txtsoth,txtmamh;
    int count=0;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        toolbar=findViewById(R.id.tlbInfo);
        setSupportActionBar(toolbar);

        txttenmh=findViewById(R.id.txttenmh);
        txtsotc=findViewById(R.id.txtsotc);
        txtsoth=findViewById(R.id.txtsoth);
        Intent intent=getIntent();

        String tenmh=intent.getStringExtra("tenmh");
        String sotc=intent.getStringExtra("sotc");
        String soth=intent.getStringExtra("soth");

        txttenmh.setText(tenmh);
        txtsotc.setText(sotc);
        txtsoth.setText(soth);



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.MenuAbout:
                Intent intent2=new Intent(InformationMHActivity.this,AboutActivity.class);
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