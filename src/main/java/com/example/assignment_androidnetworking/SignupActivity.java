package com.example.assignment_androidnetworking;

import static com.example.assignment_androidnetworking.SplashScreen.urlll;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    EditText edt_username,edt_email,edt_password,edt_repassword,edt_fullname;
    Button btn_dangky,btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edt_username = findViewById(R.id.edt_username_dk);
        edt_email = findViewById(R.id.edt_email_dk);
        edt_password = findViewById(R.id.edt_password_dk);
        edt_repassword = findViewById(R.id.edt_repassword_dk);
        edt_fullname = findViewById(R.id.edt_fullname_dk);
        btn_dangky = findViewById(R.id.btn_accept_dk);
        btn_login = findViewById(R.id.btn_login_dk);

        RequestQueue queue = Volley.newRequestQueue(SignupActivity.this);

        btn_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JSONObject user = new JSONObject();
                Validate();

                try {
                    user.put("username",edt_username.getText().toString().trim());
                    user.put("email",edt_email.getText().toString().trim());
                    user.put("password",edt_password.getText().toString().trim());
                    user.put("Fullname",edt_fullname.getText().toString().trim());
                }catch (JSONException e){
                    e.printStackTrace();
                }
                String url = "http://"+urlll+":1337/api/auth/local/register";
                JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.POST, url,
                        null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("Response", response.toString());
                        Toast.makeText(SignupActivity.this, "Dang ky thanh cong", Toast.LENGTH_SHORT).show();

                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("a",""+error);
                    }


                }){    //this is the part, that adds the header to the request
                    @Override
                    public Map<String, String> getHeaders() {
                        Map<String, String> params = new HashMap<String, String>();
                        //params.put("x-vacationtoken", "secret_token");
                        params.put("content-type", "application/json");
                        return params;
                    }
                    @Override
                    public byte[] getBody() {

                        try {
                            Log.i("json", user.toString());
                            return user.toString().getBytes("UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                };

                queue.add(jsonObjectRequest1);
            }
        });


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });

    }


    public void Validate(){
        String username = edt_username.getText().toString().trim();
        String email = edt_email.getText().toString().trim();
        String password = edt_password.getText().toString().trim();
        String repassword = edt_repassword.getText().toString().trim();
        String Fullname = edt_fullname.getText().toString().trim();

        Pattern uppercase = Pattern.compile("[A-Z]");
        Pattern lowercase = Pattern.compile("[a-z]");
        Pattern digit = Pattern.compile("[0-9]");
        Pattern err = Pattern.compile("[[!@#$%^&*+=?-]]");


        if (username.isEmpty()){
            edt_username.setError("không để trống");
        }
        if (username.length() < 5) {
            edt_username.setError("tối thiểu 5 ký tự");
        }
        if (email.isEmpty()) {
            edt_email.setError("không để trống");
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edt_email.setError("Email không hợp lệ");
        }
        if (password.isEmpty()){
            edt_password.setError("Không để trống mật khẩu");
        }
        if (password.length()<8) {
            edt_password.setError("tối thiểu 8 ký tự");
        }
        if (!lowercase.matcher(password).find()) {
            edt_password.setError("cần ít nhất 1 chữ thường");
        }
        if (!uppercase.matcher(password).find()) {
            edt_password.setError("cần ít nhất 1 chữ in Hoa");
        }
        if (!digit.matcher(password).find()) {
            edt_password.setError("cần ít nhất 1 chữ số");
        }
        if (err.matcher(password).find()) {
            edt_password.setError("không chứa ký tự đặc biệt");
        }
        if (!repassword.equals(password)){
            edt_repassword.setError("Không trùng khớp");
        }
        if (Fullname.isEmpty()) {
            edt_fullname.setError("không để trống");
        }
    }

}