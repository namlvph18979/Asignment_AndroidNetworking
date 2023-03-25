package com.example.assignment_androidnetworking;

import static com.example.assignment_androidnetworking.SplashScreen.urlll;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.assignment_androidnetworking.Model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {


    EditText username,password;
    Button btn_login;
    TextView dangky;
    ArrayList<User> arrayList;
    User userb;
    String idnay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.edt_username);
        password = findViewById(R.id.edt_password);
        dangky = findViewById(R.id.tv_dangky);
        btn_login = findViewById(R.id.btn_login);
        arrayList = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
                JSONObject object = new JSONObject();
                    try {
                        object.put("identifier",username.getText().toString().trim());
                        object.put("password",password.getText().toString().trim());
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                String duong_dan = "http://"+urlll+":1337/api/auth/local";
                JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, duong_dan, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONObject object1 = response.getJSONObject("user");
                            idnay = object1.getString("id");
                            //userb = new User(object1.getString("id"),object1.getString("username"),object1.getString("email"),object1.getString("Fullname"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Bundle b = new Bundle();
                        b.putString("idnguoidung",idnay);
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        intent.putExtras(b);
                        startActivity(intent);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("a",""+error);
                        Toast.makeText(getApplicationContext(),"Username or Password incorrect",Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    public Map<String, String> getHeaders() {
                        Map<String, String> params = new HashMap<String, String>();
//                        params.put("x-vacationtoken", "secret_token");
                        params.put("content-type", "application/json");
                        return params;
                    }
                    @Override
                    public byte[] getBody() {
                        try {
                            Log.i("json", object.toString());
                            return object.toString().getBytes("UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                };
                queue.add(objectRequest);
            }
        });



        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SignupActivity.class));
            }
        });
    }

    public void validate(){
        String user = username.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if (user.isEmpty() || pass.isEmpty()){
            Toast.makeText(getApplicationContext(),"Vui lòng điền đủ thông tin",Toast.LENGTH_SHORT).show();
        }
    }
}