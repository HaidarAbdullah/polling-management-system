package com.example.raiseyourvoice.adapter;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.raiseyourvoice.R;
import com.example.raiseyourvoice.RVmodels.Option;

public class OptionViewHolder extends RecyclerView.ViewHolder {
    TextView tvOptionName;
    EditText etOptionValue;

    public OptionViewHolder(@NonNull View itemView) {
        super(itemView);

        tvOptionName=itemView.findViewById(R.id.tvOptionName);
        etOptionValue=itemView.findViewById(R.id.etOptionValue);
    }

    public void bind(Option option)
    {
        tvOptionName.setText(option.optionName);

    }
}
