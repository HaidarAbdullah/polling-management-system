package com.example.raiseyourvoice.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.raiseyourvoice.RVmodels.Supervisor;
import com.example.raiseyourvoice.R;

public class SearchSupervisorsViewHolder extends RecyclerView.ViewHolder {
    TextView tv1,tv2;

    public SearchSupervisorsViewHolder(@NonNull View itemView) {
        super(itemView);
        tv1 =itemView.findViewById(R.id.tvName);
        tv2 =itemView.findViewById(R.id.tvEmail);
    }

    public void bind(Supervisor supervisor)
    {
        tv1.setText(supervisor.name);
        tv2.setText(supervisor.contact_email);
    }
}

