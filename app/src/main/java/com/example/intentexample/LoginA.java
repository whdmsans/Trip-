package com.example.intentexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginA extends AppCompatActivity {

    private EditText et_id, et_pass;
    private Button btn_login, btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        et_id = findViewById(R.id.et_ida);
        et_pass = findViewById(R.id.et_passa);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_registera);

        // 회원가입 버튼 클릭 시 수행
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginA.this, Register.class);
                startActivity(intent);
            }
        });

        // 로그인 버튼 클릭 시 클릭
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // EditText에 현재 입력되어 있는 값을 가져온다.
                String userID = et_id.getText().toString();
                String userPass = et_pass.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success){ // 로그인 성공
                                String userID = jsonObject.getString("userID");
                                String userPass = jsonObject.getString("userPassword");
                                Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginA.this, Login.class); // MainActivity로 수정
                                intent.putExtra("userID", userID);
                                intent.putExtra("userPass", userPass);
                                startActivity(intent);
                            }else{ // 로그인 실패
                                Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(userID, userPass, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginA.this);
                queue.add(loginRequest);
            }
        });
    }
}