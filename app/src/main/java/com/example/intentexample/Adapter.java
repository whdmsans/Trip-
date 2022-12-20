package com.example.intentexample;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyHolder> {

    Context context;
    ArrayList<User> userList;
    String Uid;

    public Adapter(Context context, ArrayList<User> userList) {
        this.context = context;
        this.userList = userList;
        Uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    }


    @NonNull
    //아이템 뷰를 관리하는 viewHolder 객체 생성
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_user,parent,false);
        return new Adapter.MyHolder(view);
    }
    //position에 해당하는 데이터를 viewholder가 관리하는 view에 바인딩
    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
//        final String uid = userList.get(i).getUid();


        Glide.with(context).load(userList.get(i).getProfile()).into(myHolder.mprofile);
        final String userName = userList.get(i).getName();
        final String emailId = userList.get(i).getEmail();

//        Log.d(, " " + profile+" "+ userName+" " + emailId+" ");
        if(emailId!=null) {
            myHolder.muserName.setText(userName);
            myHolder.memailId.setText(emailId);
        }
    }
    @Override
    public int getItemCount() {

        return userList.size();
    }

    //각 list에 들어갈 객체의 맴버 변수
    class MyHolder extends RecyclerView.ViewHolder{
        ImageView mprofile;
        TextView muserName, memailId;

        public MyHolder(View itemView) {

            super(itemView);

            mprofile = itemView.findViewById(R.id.iv_profile);
            muserName = itemView.findViewById(R.id.tv_userName);
            memailId = itemView.findViewById(R.id.tv_email);
        }
    }
}
