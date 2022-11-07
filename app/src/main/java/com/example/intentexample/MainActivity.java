package com.example.intentexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

//  버튼 선언 수정, 캘린더,   친구,    홈,   검색,  설정
    Button btn_1, btn_2, btn_3, btn_4, btn_5;
    private long backBtnTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //버튼들 아이디 매칭
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        //캘린더 버튼
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Calendar Calendar = new Calendar();
                transaction.replace(R.id.frame, Calendar);
                transaction.commit();
            }
        });
        //친구 버튼
        btn_2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Friends Friends = new Friends();
                transaction.replace(R.id.frame, Friends);
                transaction.commit();
            }
        });
        //홈 버튼
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Home Home = new Home();
                transaction.replace(R.id.frame, Home);
                transaction.commit();
            }
        });
        //검색 버튼
        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Search Search = new Search();
                transaction.replace(R.id.frame, Search);
                transaction.commit();
            }
        });
        //설정 버튼
        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Option Option = new Option();
                transaction.replace(R.id.frame, Option);
                transaction.commit();
            }
        });
    }
//뒤로가기 버튼 2번 눌러서 종료
    @Override
    public void onBackPressed() {
        long curTime = System.currentTimeMillis();
        long gapTime = curTime - backBtnTime;

        if(0<=gapTime && 2000 >= gapTime){
            super.onBackPressed();
        }
        else{
            backBtnTime = curTime;
            Toast.makeText(this, "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
    }
}