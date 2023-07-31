package com.demo.qlsinhvien;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.demo.qlsinhvien.database.database;
import com.demo.qlsinhvien.model.SinhVien;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class UpdateSVActivity2 extends AppCompatActivity {

    EditText edtSuaTenSV, edtSuaSDT;
    RadioButton radSuaNam, radSuaNu;
    Button btnSuaSV;
    TextView txtSuaMaSV;
    ImageView imganh;
    Spinner spSuaMH;
    final int RESQUEST_TAKE_PHOTO = 123;
    Toolbar toolbar;
    int count=0;
    ArrayList<String> ArrayListMonhoc;
    String tenmh;

    com.demo.qlsinhvien.database.database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_svactivity3);
        toolbar=findViewById(R.id.tlbupdatesv);
        setSupportActionBar(toolbar);

        edtSuaTenSV=findViewById(R.id.edtSuaTenSV);
        txtSuaMaSV=findViewById(R.id.txtSuaMaSV);
        btnSuaSV=findViewById(R.id.btnSuaSV);
        radSuaNam=findViewById(R.id.radSuaNam);
        radSuaNu=findViewById(R.id.radSuaNu);
        edtSuaSDT=findViewById(R.id.edtSuaSDT);
        spSuaMH=findViewById(R.id.spSuaMH);
        imganh=findViewById(R.id.imgSuaSV);

        Intent intent=getIntent();
        String masv=intent.getStringExtra("masv");
        String tensv= intent.getStringExtra("tensv");
        String phai= intent.getStringExtra("phai");
        String sdt= intent.getStringExtra("sdt");
        String tenMH= intent.getStringExtra("tenmh");
        byte[] anhsv= intent.getByteArrayExtra("anhsv");
        ArrayListMonhoc=new ArrayList<>();
        database =new database(this);
        Cursor cursor = database.getDataMonHoc1();
        while (cursor.moveToNext()){
            String ten=cursor.getString(0);
            ArrayListMonhoc.add(ten);

        }
        cursor.moveToFirst();
        cursor.close();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>
                (
                        this,
                        android.R.layout.simple_spinner_item,
                        ArrayListMonhoc
                );
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        spSuaMH.setAdapter(adapter);
        spSuaMH.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tenmh=ArrayListMonhoc.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                for(int i=0;i<15;i++){
                    if(tenMH.equals(ArrayListMonhoc.get(i))){
                        tenmh=ArrayListMonhoc.get(i);
                        break;
                    }
                }
            }
        });

        Bitmap bitmap= BitmapFactory.decodeByteArray(anhsv,0,anhsv.length);
        imganh.setImageBitmap(bitmap);
        edtSuaTenSV.setText(tensv);
        txtSuaMaSV.setText(masv);
        if(phai.equals("Nam")){
            radSuaNam.setChecked(true);
            radSuaNu.setChecked(false);
        }
        else {
            radSuaNu.setChecked(true);
            radSuaNam.setChecked(false);
        }
        edtSuaSDT.setText(sdt);


        imganh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture();
            }
        });

        database=new database(this);
        btnSuaSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogUpdate();
            }
        });
    }
    private void takePicture(){

        Intent intent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,RESQUEST_TAKE_PHOTO);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK){
            if(requestCode==RESQUEST_TAKE_PHOTO){
                Bitmap bitmap=(Bitmap) data.getExtras().get("data");
                imganh.setImageBitmap(bitmap);
            }
        }

        UpdateSVActivity2.super.onActivityResult(requestCode, resultCode, data);
    }

    private void DialogUpdate() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogupdatesv);
        dialog.setCanceledOnTouchOutside(false);
        Button btnyes = dialog.findViewById(R.id.btnYesSuaSV);
        Button btnno = dialog.findViewById(R.id.btnNoSuaSV);
        Intent intent = getIntent();

        String maSV = intent.getStringExtra("masv");
        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tensv = edtSuaTenSV.getText().toString().trim();

                if(radSuaNam.isChecked()){
                    String gioitinh="Nam";
                }
                else if(radSuaNu.isChecked()){
                    String gioitinh="Nữ";
                }
                String sdt = edtSuaSDT.getText().toString().trim();

                SinhVien sinhVien=CreateSV();

                if ( tensv.equals("") || sdt.equals("")) {
                    Toast.makeText(UpdateSVActivity2.this, "Bạn vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                } else {

                    database.UpdateSinhVien(sinhVien,maSV);
                    Intent intent =new Intent(UpdateSVActivity2.this, StudentActivity.class);
                    intent.putExtra("tenmh",tenmh);
                    startActivity(intent);
                    Toast.makeText(UpdateSVActivity2.this, "Sửa thành công!", Toast.LENGTH_SHORT).show();
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
    public byte[] ConverttoArrayByte(ImageView img)
    {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable();
        Bitmap bitmap=bitmapDrawable.getBitmap();

        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    private SinhVien CreateSV(){

        String tensv=edtSuaTenSV.getText().toString().trim();
        String gioitinh="";
        if(radSuaNam.isChecked()){
            gioitinh="Nam";
        }
        else if(radSuaNu.isChecked()){
            gioitinh="Nữ";
        }
        String sdt=(edtSuaSDT.getText().toString().trim());
        String tenMH=tenmh;
        String masv=txtSuaMaSV.getText().toString().trim();
        byte[] anhsv=ConverttoArrayByte(imganh);

        Bitmap bitmap= BitmapFactory.decodeByteArray(anhsv,0,anhsv.length);
        imganh.setImageBitmap(bitmap);

        SinhVien sinhVien=new SinhVien(masv,tensv,gioitinh,sdt,tenMH,anhsv);
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
                Intent intent=new Intent(UpdateSVActivity2.this,AboutActivity.class);
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