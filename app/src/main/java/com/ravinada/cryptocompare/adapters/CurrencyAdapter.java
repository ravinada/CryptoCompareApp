package com.ravinada.cryptocompare.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ravinada.cryptocompare.CurrencyDetailActivity;
import com.ravinada.cryptocompare.R;
import com.ravinada.cryptocompare.modelclasses.Currency;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.MyViewHolder> {

    private Context mContext;
    private List<Currency> currencyList;

    public CurrencyAdapter(Context mContext, List<Currency> currencyList) {
        this.mContext = mContext;
        this.currencyList = currencyList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.top_volume_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Currency currency = currencyList.get(position);
        String name = currency.getFullName();
        holder.coinTag.setText(currency.getCode());
        holder.coinName.setText(name);
        holder.coinPrice.setText(currency.getCurrentRate());
        holder.openDay.setText(currency.getOpenDay());
        Picasso.get().load(currency.getImageURL()).into(holder.coinImage);
    }

    @Override
    public int getItemCount() {
        return currencyList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView coinName, coinPrice,coinTag, openDay;
        public de.hdodenhof.circleimageview.CircleImageView coinImage;

        public MyViewHolder(View view) {
            super(view);
            coinImage = view.findViewById(R.id.coin_image);
            coinName = view.findViewById(R.id.coin_name);
            coinPrice = view.findViewById(R.id.coin_price);
            openDay = view.findViewById(R.id.openDay);
            coinTag=view.findViewById(R.id.coin_tag);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if(position!= RecyclerView.NO_POSITION){
                String fsyms = currencyList.get(position).getCode();
                String coinName= currencyList.get(position).getFullName();
                String imageUrl = currencyList.get(position).getImageURL();
                Intent intent = new Intent(mContext, CurrencyDetailActivity.class);
                intent.putExtra("COIN_TAG",fsyms);
                intent.putExtra("IMAGE_URL",imageUrl);
                intent.putExtra("COIN_NAME",coinName);
                mContext.startActivity(intent);
            }
        }
    }
}
