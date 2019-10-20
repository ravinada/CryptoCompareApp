package com.ravinada.cryptocompare;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ravinada.cryptocompare.modelclasses.CurrencyType;

import java.util.ArrayList;

public class CurrencyTypePurchaseAdapter extends RecyclerView.Adapter<CurrencyTypePurchaseAdapter.CurrencyTypeViewHolder> {
    private ArrayList<CurrencyType> currencyTypes;
    private CurrencyPurchaseType currencyTypeClick;

    public CurrencyTypePurchaseAdapter (CurrencyPurchaseType currencyTypeClick){
        this.currencyTypeClick = currencyTypeClick;
    }
    public void setCurrencies(ArrayList<CurrencyType> currencyTypes){
        this.currencyTypes = currencyTypes;
    }
    @NonNull
    @Override
    public CurrencyTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_currency_purchase_item,parent,false);
        return new CurrencyTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyTypeViewHolder holder, int position) {
        holder.bind(currencyTypes.get(position));
    }

    @Override
    public int getItemCount() {
        return currencyTypes.size();
    }

    public class CurrencyTypeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView currency;
        public CurrencyTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            currency = itemView.findViewById(R.id.btn_currency);
            itemView.setOnClickListener(this);
        }
        void bind(CurrencyType currencyType){
            currency.setText(currencyType.getAbr());
        }

        @Override
        public void onClick(View view) {
             int position = getAdapterPosition();
             if(position!=RecyclerView.NO_POSITION){
                 CurrencyType type = currencyTypes.get(position);
                 currencyTypeClick.onCurrencyClick(type);
             }
        }
    }
    public interface CurrencyPurchaseType{
        void onCurrencyClick(CurrencyType type);
    }
}
