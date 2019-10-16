package com.ravinada.cryptocompare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CurrencyChoiceAdapter extends RecyclerView.Adapter<CurrencyChoiceAdapter.CurrencyChoiceViewHolder> {
    private ArrayList<CurrencyType> currencyTypes;
    private Context context;

    public CurrencyChoiceAdapter(Context context, ArrayList<CurrencyType> currencyTypes) {
        this.currencyTypes = currencyTypes;
        this.context = context;
    }

    @NonNull
    @Override
    public CurrencyChoiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.currency_item, parent, false);
        return new CurrencyChoiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyChoiceViewHolder holder, int position) {
        holder.bind(currencyTypes.get(position));
    }

    @Override
    public int getItemCount() {
        return currencyTypes.size();
    }

    public class CurrencyChoiceViewHolder extends RecyclerView.ViewHolder {
        TextView symbol;
        TextView abr;
        TextView name;

        public CurrencyChoiceViewHolder(@NonNull View itemView) {
            super(itemView);
            symbol = itemView.findViewById(R.id.currencySymbol);
            abr = itemView.findViewById(R.id.currencyAbr);
            name = itemView.findViewById(R.id.currencyDescription);
        }
        void bind(CurrencyType currencyType){
            symbol.setText(currencyType.getSymbol());
            abr.setText(currencyType.getAbr());
            name.setText(currencyType.getName());
        }
    }
}
