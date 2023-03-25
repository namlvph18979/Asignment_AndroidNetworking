package com.example.assignment_androidnetworking;

import static com.example.assignment_androidnetworking.SplashScreen.urlll;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.assignment_androidnetworking.Adapter.ChuongTruyen_Adapter;
import com.example.assignment_androidnetworking.Model.Binh_luan;
import com.example.assignment_androidnetworking.Model.Truyen_tranh;
import com.example.assignment_androidnetworking.Model.User;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ScrollView scrollView;
    FloatingActionButton flb1;
    FloatingActionMenu flb_menu;
    GridView gridView;
    ArrayList<Truyen_tranh> arrayList;
    ChuongTruyen_Adapter adapter;
    User user;
    Truyen_tranh truyen_tranh;
    String idnguoidungnay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollView = findViewById(R.id.src_main);
        flb1 = findViewById(R.id.flb1);
        flb_menu = findViewById(R.id.flb_menu);
        gridView = findViewById(R.id.grid_view);
        arrayList = new ArrayList<>();

        truyen_tranh = new Truyen_tranh();
        Intent intent1 = getIntent();
        Bundle bundle = intent1.getExtras();
        idnguoidungnay = bundle.getString("idnguoidung");

        String url = "http://"+urlll+":1337/api/truyentranhs?populate=*";
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                    try {
                        JSONArray responseJSONArray = response.getJSONArray("data");

                        for (int i =0; i<responseJSONArray.length();i++){
                            JSONObject jsonObject = responseJSONArray.getJSONObject(i);
                            JSONObject obj1 = jsonObject.getJSONObject("attributes");

                            arrayList.add(new Truyen_tranh(responseJSONArray.getJSONObject(i).getString("id"),obj1.getString("ten_truyen"),obj1.getString("mo_ta_ngan"),obj1.getString("nam_xuat_ban"),obj1.getString("tac_gia"),
                                    obj1.getJSONObject("anh_bia").getJSONObject("data").getJSONObject("attributes").getString("url")));

                        }
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                    adapter = new ChuongTruyen_Adapter(MainActivity.this,0,arrayList);
                    gridView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override

                public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(request);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Truyen_tranh truyen_tranh = arrayList.get(i);

                Bundle b = new Bundle();
                b.putSerializable("tentruyen", truyen_tranh);
                b.putString("nguoidung1",idnguoidungnay);
                Intent intent = new Intent(getApplicationContext(),activity_truyen.class);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                if (i1 > 0){
                    flb_menu.setVisibility(View.GONE);
                }else {
                    flb_menu.setVisibility(View.VISIBLE);
                }
            }
        });

        flb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });

        ImageSlider imageSlider = findViewById(R.id.img_slide);
        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.pic1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.pic2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.pic6, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.pic7, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.naruto, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.pic8, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels,ScaleTypes.FIT);
    }


}