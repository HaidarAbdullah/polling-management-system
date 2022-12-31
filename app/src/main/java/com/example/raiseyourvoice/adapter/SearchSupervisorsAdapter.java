package com.example.raiseyourvoice.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.raiseyourvoice.R;
import com.example.raiseyourvoice.RVmodels.Supervisor;

import java.util.ArrayList;
import java.util.List;

public class SearchSupervisorsAdapter extends RecyclerView.Adapter<SearchSupervisorsViewHolder> {
    ArrayList<Supervisor> supervisors=new ArrayList<>();

    public void setFilteredList(ArrayList<Supervisor> filteredList)
    {
        this.supervisors=filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchSupervisorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchSupervisorsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_supervisor,null));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchSupervisorsViewHolder holder, int position) {
        holder.bind(supervisors.get(position));
    }

    @Override
    public int getItemCount() {
        return supervisors.size();
    }

    public void addItems(ArrayList<Supervisor> items)
    {
        supervisors.clear();
        supervisors.addAll(items);
        notifyDataSetChanged();
    }
}
