package com.demo.qlsinhvien;

import static android.provider.MediaStore.ACTION_IMAGE_CAPTURE;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.demo.qlsinhvien.database.database;
import com.demo.qlsinhvien.model.SinhVien;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class AddSVActivity extends AppCompatActivity {

    Button btnAddSV ;
    EditText edtTenSV, edtMaSV, edtSDT,edtDiaChi;
    RadioButton radNam, radNu;
    ImageView imganh;
    Spinner spinner;
    final int RESQUEST_TAKE_PHOTO = 123;
    final int RESQUEST_CHOOSE_PHOTO = 321;
    com.demo.qlsinhvien.database.database database;
    Toolbar toolbar;
    int count=0;
    ArrayAdapter<String> adapter;
    ArrayList<String> ArrayListMonhoc;

    String tenmh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_svactivity);
        toolbar=findViewById(R.id.tlbaddsv);
        setSupportActionBar(toolbar);
        btnAddSV =findViewById(R.id.btnThemSV);
        edtMaSV=findViewById(R.id.edtMaSV);
        edtTenSV=findViewById(R.id.edtTenSV);
        edtSDT=findViewById(R.id.edtSDT);
        spinner=findViewById(R.id.sptenMH);
        radNam=findViewById(R.id.radNam);
        radNu=findViewById(R.id.radNu);
        imganh=findViewById(R.id.imgsv);

        imganh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture();
            }
        });
        ArrayListMonhoc=new ArrayList<>();
        database =new database(this);
        Cursor cursor = database.getDataMonHoc1();
        while (cursor.moveToNext()){
            String ten=cursor.getString(0);
            ArrayListMonhoc.add(ten);

        }
        cursor.moveToFirst();
        cursor.close();
        adapter =new ArrayAdapter<String>
                (
                        this,
                        android.R.layout.simple_spinner_item,
                        ArrayListMonhoc
                );
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tenmh=ArrayListMonhoc.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        database=new database(this);

        btnAddSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAddSV();
            }
        });


    }
    public void dsmh(){
//        Cursor cursor = database.getDataMonHoc();
//        int i=0;
//        while (cursor.moveToNext()){
//            String tenmh=cursor.getString(1);
//            arr[i]=tenmh;
//            i++;
//        }

    }
    private void takePicture(){
        Intent intent;
        intent = new Intent(ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,RESQUEST_TAKE_PHOTO);

    }
    private  void choosePhoto(){
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,RESQUEST_CHOOSE_PHOTO);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK){
            if(requestCode==RESQUEST_CHOOSE_PHOTO){
                try {
                    Uri imageUri=data.getData();
                    InputStream is=getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap=BitmapFactory.decodeStream(is);
                    imganh.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else if(requestCode==RESQUEST_TAKE_PHOTO){
                Bitmap bitmap=(Bitmap) data.getExtras().get("data");
                imganh.setImageBitmap(bitmap);
            }
        }

        AddSVActivity.super.onActivityResult(requestCode, resultCode, data);
    }

    public byte[] ConverttoArrayByte(ImageView img)
    {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable();
        Bitmap bitmap=bitmapDrawable.getBitmap();

        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    public void DialogAddSV(){
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialogaddsinhvien);
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes =dialog.findViewById(R.id.btnYesSV);
        Button btnNo=dialog.findViewById(R.id.btnNoSV);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String masv=edtMaSV.getText().toString().trim();
                String tensv=edtTenSV.getText().toString().trim();
                byte[] anhsv=ConverttoArrayByte(imganh);
                Bitmap bitmap= BitmapFactory.decodeByteArray(anhsv,0,anhsv.length);
                imganh.setImageBitmap(bitmap);
                if(radNam.isChecked()){
                    String gioitinh="Nam";
                }
                else if(radNu.isChecked()){
                    String gioitinh="Nữ";
                }
                String sdt=edtSDT.getText().toString().trim();
                String tenMH=tenmh;


                if(masv.equals("")||tensv.equals("")|| sdt.equals("")){
                    Toast.makeText(AddSVActivity.this,"Mời bạn nhập đúng thông tin !",Toast.LENGTH_SHORT).show();
                }
                else {
                    SinhVien sinhVien=CreateSinhvien();
                    database.AddSinhVien(sinhVien);
                    Intent intent =new Intent(AddSVActivity.this, MainActivity2.class);
                    startActivity(intent);

                    Toast.makeText(AddSVActivity.this,"Thêm thành công !",Toast.LENGTH_SHORT).show();
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
    private SinhVien CreateSinhvien(){
        String masv=edtMaSV.getText().toString().trim();
        String tensv=edtTenSV.getText().toString().trim();
        String gioitinh="";
        if(radNam.isChecked()){
            gioitinh="Nam";
        }
        else if(radNu.isChecked()){
            gioitinh="Nữ";
        }
        String sdt=(edtSDT.getText().toString().trim());
        String tenMH=tenmh;
        byte[] anhsv=ConverttoArrayByte(imganh);

        Bitmap bitmap= BitmapFactory.decodeByteArray(anhsv,0,anhsv.length);
        imganh.setImageBitmap(bitmap);

        SinhVien sinhVien=new SinhVien(masv, tensv,gioitinh,sdt,tenMH,anhsv);
        return sinhVien;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.MenuAbout:
                Intent intent2=new Intent(AddSVActivity.this,AboutActivity.class);
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
    public void onBackPressed() {
        count++;
        if (count>=1){
            Intent intent=new Intent(AddSVActivity.this,MainActivity2.class);

            startActivity(intent);
            finish();
        }
    }
}