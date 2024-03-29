//package com.example.intentexample;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//
//import java.util.ArrayList;
//
//public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
//
//    private ArrayList<User> arrayList;
//    private Context context;
//
//    public CustomAdapter(ArrayList<User> arrayList, Context context) {
//        this.arrayList = arrayList;
//        this.context = context;
//    }
//
//    @NonNull
//    @Override
//    public CustomAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_user, parent, false);
//        CustomViewHolder holder = new CustomViewHolder(view);
//        return holder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull CustomAdapter.CustomViewHolder holder, int position) {
//        Glide.with(holder.itemView)
//                .load(arrayList.get(position).getProfile())
//                .into(holder.iv_profile);
//        holder.tv_id.setText(arrayList.get(position).getId());
//        holder.tv_na.setText(arrayList.get(position).getUserName());
//    }
//
//    @Override
//    public int getItemCount() {
//        return (arrayList != null ? arrayList.size() : 0);
//    }
//
//    public class CustomViewHolder extends RecyclerView.ViewHolder {
//        ImageView iv_profile;
//        TextView tv_id;
//        TextView tv_na;
//
//        public CustomViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            this.iv_profile = itemView.findViewById(R.id.iv_profile);
//            this.tv_id = itemView.findViewById(R.id.tv_id);
//            this.tv_na = itemView.findViewById(R.id.tv_Na);
//        }
//    }
//}
