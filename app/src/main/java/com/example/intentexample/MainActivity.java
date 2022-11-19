package com.example.intentexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

    // 날짜 변수
    private TextView date;

    // 드로워 변수
    private DrawerLayout drawerLayout;
    private View drawerView;
    
    // 드로워 추가 변수
    private TextView tv_id, tv_email;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //날짜 변수 연결 및 출력
        date = findViewById(R.id.Date);
        date.setText(getTime());

        // 드로워 변수 연결
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerView = (View)findViewById(R.id.drawer);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        
        // open 버튼 작동
        ImageView open = (ImageView)findViewById(R.id.open);
        open.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                drawerLayout.openDrawer(Gravity.RIGHT);
                return false;
            }
        });

        // close 버튼 작동
        ImageView close = (ImageView)findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
            }
        });

        // 추가 상황 설정
        drawerLayout.setDrawerListener(listener);
        drawerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });


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

    // 현재 날짜 함수
    private String getTime() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd(EE)", Locale.KOREAN);
        String getTime = dateFormat.format(date);

        return getTime;
    }

    // 드로워 함수
    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
            // 드로워 추가 변수 연결 및 값 설정
            tv_id = findViewById(R.id.userID);
            tv_email = findViewById(R.id.userEmail);

            Intent intent = getIntent();
            String userID = intent.getStringExtra("userID");
            String userEmail = intent.getStringExtra("userEmail");

            tv_id.setText(userID);
//            tv_email.setText(userEmail);
        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerClosed(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };

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