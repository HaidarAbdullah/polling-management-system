package com.example.raiseyourvoice.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.raiseyourvoice.R;
import com.example.raiseyourvoice.model.PollingRoom;
import com.example.raiseyourvoice.model.SupervisedRoom;

import java.util.ArrayList;

public class RoomsAdapter extends RecyclerView.Adapter<RoomViewHolder> {
    ArrayList<PollingRoom> rooms=new ArrayList<>();
    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RoomViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
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
        notifyDataSetChanged();
    }
}
