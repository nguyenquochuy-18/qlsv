package com.demo.qlsinhvien.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.qlsinhvien.R;
import com.demo.qlsinhvien.StudentActivity;
import com.demo.qlsinhvien.model.SinhVien;

import java.util.ArrayList;

public class adapterSinhVien extends BaseAdapter {

    StudentActivity context;
    ImageView imganhsv;
    ArrayList<SinhVien> ArrayListSinhVien;

    public adapterSinhVien(StudentActivity context, ArrayList<SinhVien> arrayListSinhVien) {
        this.context = context;
        ArrayListSinhVien = arrayListSinhVien;
    }

    @Override
    public int getCount() {
        return ArrayListSinhVien.size();
    }

    @Override
    public Object getItem(int i) {
        return ArrayListSinhVien.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view =inflater.inflate(R.layout.listsinhvien,null);

        TextView txtTen=view.findViewById(R.id.txtTenSV);
        TextView txtTenMH=view.findViewById(R.id.txtTenMH);
        TextView txtMa=view.findViewById(R.id.txtMasv);
        imganhsv = view.findViewById(R.id.imganhsv);
        ImageButton xoa =view.findViewById(R.id.deleteSV);
        ImageButton sua =view.findViewById(R.id.updateSV);
        ImageButton thongtin =view.findViewById(R.id.infoSV);

        SinhVien sinhVien =ArrayListSinhVien.get(i);

        txtTen.setText(sinhVien.getTen());
        txtTenMH.setText(sinhVien.getTenmh()+" ");
        txtMa.setText(sinhVien.getMa());

        String id=sinhVien.getMa();

        Bitmap bitmap= BitmapFactory.decodeByteArray(sinhVien.getHinhanh(), 0,sinhVien.getHinhanh().length);
        imganhsv.setImageBitmap(bitmap);

        thongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.informationSV(id);
            }
        });

        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.delete(id);
            }
        });

        sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.updatesv(id);
            }
        });

        return view;
    }


}