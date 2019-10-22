package com.ravinada.cryptocompare.adapters;

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
import com.ravinada.cryptocompare.room.FavouriteCoin;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavouriteCurrencyAdapter extends RecyclerView.Adapter<FavouriteCurrencyAdapter.FavouriteViewHolder> {

    private Context mContext;
    private List<FavouriteCoin> favouriteCoins;

    public FavouriteCurrencyAdapter(Context mContext,List<FavouriteCoin> favouriteCoins){
        this.mContext = mContext;
        this.favouriteCoins = favouriteCoins;
    }
    @NonNull
    @Override
    public FavouriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_volume_card, parent, false);
        return new FavouriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteViewHolder holder, int position) {
        FavouriteCoin favouriteCoin = favouriteCoins.get(position);
        holder.coinTag.setText(favouriteCoin.getTag());
        holder.coinName.setText(favouriteCoin.getName());
        holder.coinPrice.setText(favouriteCoin.getCurrentCoinPrice());
        holder.openDay.setText(favouriteCoin.getRateChg());
        Picasso.get().load(favouriteCoin.getImageUrl()).into(holder.coinImage);
    }

    @Override
    public int getItemCount() {
        return favouriteCoins.size();
    }

    public class FavouriteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView coinName, coinPrice,coinTag, openDay;
        public de.hdodenhof.circleimageview.CircleImageView coinImage;

        public FavouriteViewHolder(@NonNull View itemView) {
            super(itemView);
            coinImage = itemView.findViewById(R.id.coin_image);
            coinName = itemView.findViewById(R.id.coin_name);
            coinPrice = itemView.findViewById(R.id.coin_price);
            openDay = itemView.findViewById(R.id.openDay);
            coinTag=itemView.findViewById(R.id.coin_tag);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if(position!= RecyclerView.NO_POSITION){
                String fsyms = favouriteCoins.get(position).getTag();
                String coinName= favouriteCoins.get(position).getName();
                String imageUrl = favouriteCoins.get(position).getImageUrl();
                Intent intent = new Intent(mContext, CurrencyDetailActivity.class);
                intent.putExtra("COIN_TAG",fsyms);
                intent.putExtra("IMAGE_URL",imageUrl);
                intent.putExtra("COIN_NAME",coinName);
                mContext.startActivity(intent);
            }
        }
    }

}
