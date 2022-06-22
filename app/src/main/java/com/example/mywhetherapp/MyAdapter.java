package com.example.mywhetherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private  List<Post> list;

    MyAdapter(Context context,List<Post> list){
        this.context=context;
        this.list=list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  MyAdapter.MyViewHolder holder, int position) {
        holder.uidTV.setText(""+list.get(position).getUserId());
        holder.idTV.setText(""+list.get(position).getId());
        holder.titleTV.setText(list.get(position).getTitle());
        holder.textTV.setText(list.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView uidTV;
        TextView idTV;
        TextView textTV;
        TextView titleTV;
        public MyViewHolder( View itemView) {
            super(itemView);
            uidTV=itemView.findViewById(R.id.uidTV);
            idTV=itemView.findViewById(R.id.idTV);
            titleTV=itemView.findViewById(R.id.titleTV);
            textTV= itemView.findViewById(R.id.textTV);

        }
    }
}
