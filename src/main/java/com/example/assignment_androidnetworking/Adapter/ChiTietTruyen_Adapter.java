package com.example.assignment_androidnetworking.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.assignment_androidnetworking.Model.Binh_luan;
import com.example.assignment_androidnetworking.Model.Truyen_tranh;
import com.example.assignment_androidnetworking.R;

import java.util.ArrayList;
import java.util.List;

public class ChiTietTruyen_Adapter extends ArrayAdapter<Binh_luan> {

    Context context;
    ArrayList<Binh_luan> arrayList;
    Binh_luan binh_luan;

    public ChiTietTruyen_Adapter(@NonNull Context context, int resource, ArrayList<Binh_luan> arrayList) {
        super(context, 0, arrayList);
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_binhluan,null);
        }

        binh_luan = this.arrayList.get(position);
        TextView ten = convertView.findViewById(R.id.tv_nguoibinhluan);
        TextView ngay = convertView.findViewById(R.id.tv_thoigian);
        TextView noidung = convertView.findViewById(R.id.tv_noidung);

        ten.setText(binh_luan.getId_nguoidung());
        ngay.setText(binh_luan.getThoigian());
        noidung.setText(binh_luan.getNoidung());

        return convertView;
    }
}
