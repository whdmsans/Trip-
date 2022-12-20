package com.example.intentexample;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.ims.RcsUceAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Friends extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<User> arrayList = new ArrayList<>();
    private DatabaseReference databaseReference;
    private RecyclerView.LayoutManager layoutManager;

    private void show(){
        adapter = new Adapter(getActivity(), arrayList);
        recyclerView.setAdapter(adapter);
    }

    public Friends(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_friends, container, false);

        recyclerView = view.findViewById(R.id.recycler);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                arrayList.clear();
//                for (DataSnapshot snapshot1 : snapshot.getChildren()){
//                    User user = snapshot1.getValue(User.class);
//                    arrayList.add(user);
//                }
//                adapter.notifyDataSetChanged();
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Log.w("Database", "Failed to read value.", error.toException());
//            }
//        });
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    User user = snapshot1.getValue(User.class);
                    arrayList.add(user);
                }
                show();

                adapter.notifyDataSetChanged();

//                for (DataSnapshot snapshot1 : snapshot.getChildren()){
////                    User user = snapshot1.getValue(User.class);
////                    arrayList.add(user);
//
//                    String key = snapshot1.getKey();
//                    HashMap<String, HashMap<String, Object>> user = (HashMap<String, HashMap<String, Object>>) snapshot1.getValue();
//                    String[] getData = {user.get("xYzR1ofCXaQjIbyQyWsVbMWfQ6C3").get("email").toString(), user.get("xYzR1ofCXaQjIbyQyWsVbMWfQ6C3").get("name").toString()};
//                    User user1 = null;
//                    user1.setEmail(getData[1]);
//                    user1.setName(getData[0]);
//                    arrayList.add(user1);
//                }
//                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }

    @Override
    public void startActivity(Intent intent, @Nullable Bundle options) {
        super.startActivity(intent, options);
        show();
    }
}