package com.example.raiseyourvoice.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.raiseyourvoice.R;
import com.example.raiseyourvoice.model.SupervisedRoom;

import java.util.ArrayList;

public class SupervisedRoomsAdapter extends RecyclerView.Adapter<SupervisedRoomsViewHolder>{
    ArrayList<SupervisedRoom> rooms=new ArrayList<>();

    @NonNull
    @Override
    public SupervisedRoomsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SupervisedRoomsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room,null));
    }

    @Override
    public void onBindViewHolder(@NonNull SupervisedRoomsViewHolder holder, int position) {
        holder.bind(rooms.get(position));
    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }

    public void addItems(ArrayList<SupervisedRoom> items)
    {
        rooms.clear();
        rooms.addAll(items);

    }
}
