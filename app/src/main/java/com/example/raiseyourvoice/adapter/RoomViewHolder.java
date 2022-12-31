package com.example.raiseyourvoice.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.raiseyourvoice.R;
import com.example.raiseyourvoice.model.PollingRoom;

public class RoomViewHolder extends RecyclerView.ViewHolder {
    TextView tv1,tv2,tv3,tv4;


    public RoomViewHolder(@NonNull View itemView) {
        super(itemView);

        tv1 =itemView.findViewById(R.id.tvRoomId);
        tv2 =itemView.findViewById(R.id.tvRoomOpen);
        tv3=itemView.findViewById(R.id.tvRoomClose);
        tv4=itemView.findViewById(R.id.tvRoomTitle);

    }
    public void bind(PollingRoom room)
    {
        tv1.setText(room.getRoom_identifier());
        tv2.setText(room.getOpen_time());
        tv3.setText(room.getClose_time());
        tv4.setText(room.getTitle());
    }
}
