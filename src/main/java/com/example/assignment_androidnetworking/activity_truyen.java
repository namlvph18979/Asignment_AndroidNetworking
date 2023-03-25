package com.example.assignment_androidnetworking;

import static com.example.assignment_androidnetworking.SplashScreen.urlll;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.assignment_androidnetworking.Adapter.ChiTietTruyen_Adapter;
import com.example.assignment_androidnetworking.Adapter.ChuongTruyen_Adapter;
import com.example.assignment_androidnetworking.Model.Binh_luan;
import com.example.assignment_androidnetworking.Model.Truyen_tranh;
import com.example.assignment_androidnetworking.Model.User;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class activity_truyen extends AppCompatActivity {

    ImageView back,avatar;
    EditText comment;
    TextView tentruyen,tieude;
    Button doctruyen,gui;
    ListView listView;
    ChiTietTruyen_Adapter adapter;
    ArrayList<Binh_luan> arrayList;
    ArrayList<Binh_luan> arrayListchitiet;
    Truyen_tranh truyen_tranh;

    String id_nguoidunghientai;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truyen);

        back = findViewById(R.id.img_back);
        avatar = findViewById(R.id.img_anhnen);
        tentruyen = findViewById(R.id.tv_namecomic);
        tieude = findViewById(R.id.tv_tieude);
        doctruyen =findViewById(R.id.btn_doctruyen);
        listView = findViewById(R.id.lv_listcomment);
        comment = findViewById(R.id.tv_comment);
        gui = findViewById(R.id.btn_sendmsg);

        arrayList = new ArrayList<>();
        arrayListchitiet = new ArrayList<>();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        truyen_tranh = (Truyen_tranh) bundle.getSerializable("tentruyen");
        id_nguoidunghientai = bundle.getString("nguoidung1");

        tentruyen.setText(truyen_tranh.getTentruyen());
        tieude.setText(truyen_tranh.getMota());
        String url1 = "http://"+urlll+":1337"+truyen_tranh.getAnhbia();
        Picasso.get().load(url1).into(avatar);

        laycomment(truyen_tranh.getId());
        gui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (comment.getText().toString().trim().isEmpty()){
                    comment.setError("Comment is Empty");
                }else {
                    Guimessage();
                }


            }
        });


        doctruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent());
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(),MainActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("idnguoidung",id_nguoidunghientai);
                intent1.putExtras(bundle1);
                startActivity(intent1);
            }
        });
    }


    public void laycomment(String idtruyen){
        String url = "http://"+urlll+":1337/api/binh-luans?populate=*";
        RequestQueue queue = Volley.newRequestQueue(activity_truyen.this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray responseJSONArray = response.getJSONArray("data");

                    for (int i =0; i<responseJSONArray.length();i++){
                        JSONObject jsonObject = responseJSONArray.getJSONObject(i);
                        JSONObject obj1 = jsonObject.getJSONObject("attributes");
                        arrayList.add(new Binh_luan(obj1.getJSONObject("id_truyentranh").getJSONObject("data").getString("id"),
                                obj1.getJSONObject("id_user").getJSONObject("data").getJSONObject("attributes").getString("Fullname")
                                ,obj1.getString("noi_dung"),obj1.getString("thoi_gian")));

                                if (idtruyen.equals(arrayList.get(i).getId_truyen())){
                                    arrayListchitiet.add(new Binh_luan(arrayList.get(i).getId_truyen(),arrayList.get(i).getId_nguoidung(),arrayList.get(i).getNoidung(),arrayList.get(i).getThoigian()));
                                    adapter = new ChiTietTruyen_Adapter(activity_truyen.this,0, arrayListchitiet);
                                    listView.setAdapter(adapter);
                                }

                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override

            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(request);


    }

    public void Guimessage(){
        String url = "http://"+urlll+":1337/api/binh-luans";
        RequestQueue queue = Volley.newRequestQueue(activity_truyen.this);
        LocalDate mydate = LocalDate.now();

        JSONObject data = new JSONObject();
        try {
            JSONObject object = new JSONObject();
            object.put("noi_dung",comment.getText().toString());
            object.put("thoi_gian", mydate);
            object.put("id_truyentranh",truyen_tranh.getId());
            object.put("id_user",id_nguoidunghientai);
            data.put("data",object);
        }catch (JSONException e){
            e.printStackTrace();
        }

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(getApplicationContext(),"Thanh cong",Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),"That bai",Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                public Map<String, String> getHeaders(){
                    Map<String, String> params = new HashMap<>();
                    params.put("content-type","application/json");
                    return params;
                }

                @Override
                public byte[] getBody() {
                    try {
                        return data.toString().getBytes("UTF-8");
                    }catch (UnsupportedEncodingException e){
                        e.printStackTrace();
                    }
                    return null;
                }
            };
            queue.add(request);

    }


}