package com.demo.qlsinhvien.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.demo.qlsinhvien.MonhocActivity;
import com.demo.qlsinhvien.R;
import com.demo.qlsinhvien.model.MonHoc;

import java.util.ArrayList;

public class adapterMonHoc extends BaseAdapter {

    MonhocActivity context;

    ArrayList<MonHoc> ArrayListMonhoc;

    public adapterMonHoc(MonhocActivity context, ArrayList<MonHoc> arrayListMonhoc) {
        this.context = context;
        ArrayListMonhoc = arrayListMonhoc;
    }

    @Override
    public int getCount() {
        return ArrayListMonhoc.size();
    }

    @Override
    public Object getItem(int i) {
        return ArrayListMonhoc.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view =inflater.inflate(R.layout.listmonhoc,null);

        TextView txtMon=view.findViewById(R.id.txtMonhoc);

        TextView txtSoTC=view.findViewById(R.id.txtSoTC);

        ImageButton xoa =view.findViewById(R.id.delete);
        ImageButton sua =view.findViewById(R.id.update);
        ImageButton info =view.findViewById(R.id.info);

        MonHoc monHoc =ArrayListMonhoc.get(i);

        txtMon.setText(monHoc.getTenMH());
            txtSoTC.setText(monHoc.getSoTc()+" ");

        String id=monHoc.getMaMh();
        String name=monHoc.getTenMH();



        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.information(id);
            }
        });

        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                context.delete(id,name);
            }
        });

        sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.update(id);
            }
        });

        return view;
    }


}
