package com.example.assignment_androidnetworking.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.assignment_androidnetworking.Model.Truyen_tranh;
import com.example.assignment_androidnetworking.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ChuongTruyen_Adapter extends ArrayAdapter<Truyen_tranh> {
    Context context;
    ArrayList<Truyen_tranh> arrayList;
    Truyen_tranh truyen_tranh;

    public ChuongTruyen_Adapter(@NonNull Context context, int resource, ArrayList<Truyen_tranh> arrayList) {
        super(context, 0, arrayList);
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_truyentranh,null);
        }


        truyen_tranh = this.arrayList.get(position);
        TextView ten_truyen = convertView.findViewById(R.id.tv_tentruyen);
        TextView ngay_dang = convertView.findViewById(R.id.tv_ngaydang);
        ImageView img_avatar = convertView.findViewById(R.id.img_truyentranh);
        String url_image = "http://192.168.1.169:1337"+truyen_tranh.getAnhbia();

        ten_truyen.setText(truyen_tranh.getTentruyen());
        ngay_dang.setText("Xuất bản: "+truyen_tranh.getNamxuatban());
        Picasso.get().load(url_image).into(img_avatar);

        return convertView;
    }
}
