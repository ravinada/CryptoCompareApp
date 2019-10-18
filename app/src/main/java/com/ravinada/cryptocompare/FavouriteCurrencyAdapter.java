package com.ravinada.cryptocompare;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ravinada.cryptocompare.room.FavouriteCoin;

import java.util.ArrayList;

public class FavouriteCurrencyAdapter extends RecyclerView.Adapter<FavouriteCurrencyAdapter.FavouriteViewHolder> {
    private ArrayList<FavouriteCoin> favouriteCoins;
    private OpenCoinDetail coinClicked;

    public FavouriteCurrencyAdapter(OpenCoinDetail coinClicked){
        this.coinClicked = coinClicked;
    }
    public void setFavouriteCoins(ArrayList<FavouriteCoin> favouriteCoins){
        this.favouriteCoins = favouriteCoins;
    }
    @NonNull
    @Override
    public FavouriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.currency_item, parent, false);
        return new FavouriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return favouriteCoins.size();
    }

    public class FavouriteViewHolder extends RecyclerView.ViewHolder {

        public TextView coinName, coinPrice,coinTag, openDay;
        public de.hdodenhof.circleimageview.CircleImageView coinImage;
        public FavouriteViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    public interface OpenCoinDetail{
        void onCoinClick(FavouriteCoin coin);
    }
}
