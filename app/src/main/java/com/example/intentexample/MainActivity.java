package com.example.intentexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    //화면 전환 변수
    private BottomNavigationView bottomNavigationView; // 바텀 네비게이션 뷰
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Calendar calendar;
    private Search search;
    private Home home;
    private Friends friends;

    //뒤로가기 버튼 변수
    private long backBtnTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 버튼에 따른 화면 설정
        bottomNavigationView = findViewById(R.id.bottom_navi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_calendar:
                        setFrag(0);
                        break;
                    case R.id.action_search:
                        setFrag(1);
                        break;
                    case R.id.action_map:
                        setFrag(2);
                        break;
                    case R.id.action_friends:
                        setFrag(3);
                        break;
                }
                return true;
            }
        });

        // 각 화면 연결
        calendar = new Calendar();
        search = new Search();
        home = new Home();
        friends = new Friends();
        setFrag(2); // 첫 화면을 선택

    }

    // 화면 교체 실행문
    private void setFrag(int n){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n) {
            case 0:
                ft.replace(R.id.main_frame, calendar);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.main_frame, search);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.main_frame, home);
                ft.commit();
                break;
            case 3:
                ft.replace(R.id.main_frame, friends);
                ft.commit();
                break;
        }
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