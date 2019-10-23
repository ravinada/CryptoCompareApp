package com.ravinada.cryptocompare.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.ravinada.cryptocompare.R;
import com.ravinada.cryptocompare.modelclasses.UserPortfolioCoins;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PortfolioCoinAdapter extends RecyclerView.Adapter<PortfolioCoinAdapter.PortfolioViewHolder> {

    private List<UserPortfolioCoins> portfolioCoins;
    Context context;
    public PortfolioCoinAdapter(List<UserPortfolioCoins> portfolioCoins) {
        this.portfolioCoins = portfolioCoins;
    }

    @NonNull
    @Override
    public PortfolioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_coin, parent, false);
        context = parent.getContext();
        return new PortfolioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PortfolioViewHolder holder, int position) {
        holder.coindetails.setOnClickListener(view -> {
            holder.expandedView.setVisibility(View.VISIBLE);
        });

           holder.options.setOnClickListener(view -> {
           holder.optionsBack.setVisibility(View.VISIBLE);
           holder.expandedDetails.setVisibility(View.GONE);
           holder.options.setVisibility(View.GONE);
           holder.expandedActivity.setVisibility(View.VISIBLE);
        });
        holder.optionsBack.setOnClickListener(view -> {
            holder.expandedDetails.setVisibility(View.VISIBLE);
           holder.expandedActivity.setVisibility(View.GONE);
           holder.options.setVisibility(View.VISIBLE);
           holder.optionsBack.setVisibility(View.GONE);
        });
        holder.bind(portfolioCoins.get(position));
    }

    @Override
    public int getItemCount() {
        return portfolioCoins.size();
    }

    public class PortfolioViewHolder extends RecyclerView.ViewHolder{
       public ImageView coinImage, options, optionsBack;
       public TextView coinName, coinSymbol, currentValue, profitPercent, valueInPorfolioCurrency, valueInPurchaseCurrency, plInPurchaseCurrency, plInportfolioCurrency;
       public Button delete, edit;
       public LinearLayout expandedActivity;
       public FrameLayout expandedView;
       public ConstraintLayout expandedDetails, coindetails;


        public PortfolioViewHolder(@NonNull View itemView) {
            super(itemView);
            coinImage = itemView.findViewById(R.id.coin_image);
            options = itemView.findViewById(R.id.iv_options);
            optionsBack = itemView.findViewById(R.id.iv_options_back);
            coinName = itemView.findViewById(R.id.coin_name);
            coinSymbol = itemView.findViewById(R.id.coin_tag);
            currentValue = itemView.findViewById(R.id.coin_price);
            profitPercent = itemView.findViewById(R.id.openDay);
            valueInPorfolioCurrency = itemView.findViewById(R.id.txt_total_value1);
            valueInPurchaseCurrency = itemView.findViewById(R.id.txt_total_value2);
            plInPurchaseCurrency = itemView.findViewById(R.id.txt_profit_loss2);
            plInportfolioCurrency = itemView.findViewById(R.id.txt_profit_loss1);
            delete = itemView.findViewById(R.id.btn_delete);
            edit = itemView.findViewById(R.id.btn_edit);
            expandedActivity = itemView.findViewById(R.id.expandedActivity);
            expandedView = itemView.findViewById(R.id.expandedCoinDetails);
            expandedDetails = itemView.findViewById(R.id.expandedDetails);
            coindetails = itemView.findViewById(R.id.porfolioCoinDetails);
            expandedView.setVisibility(View.GONE);
            expandedActivity.setVisibility(View.GONE);
            optionsBack.setVisibility(View.GONE);
        }
        public void bind(UserPortfolioCoins userPortfolioCoin) {
            Picasso.get().load(userPortfolioCoin.getCoinUrl()).into(coinImage);
            coinName.setText(userPortfolioCoin.getCoinFullName());
            coinSymbol.setText(userPortfolioCoin.getCoinTag());
            currentValue.setText(Float.toString(userPortfolioCoin.getCurrentCoinValue()));
            profitPercent.setText(Float.toString((userPortfolioCoin.getGetValueHoldingsPurchaseCurrency()/userPortfolioCoin.getCurrentCoinValue())*100));
            valueInPorfolioCurrency.setText(Float.toString(userPortfolioCoin.getValueHoldingsPortfolioCurrency()));
            valueInPurchaseCurrency.setText(Float.toString(userPortfolioCoin.getValueHoldingsPortfolioCurrency()));
            plInportfolioCurrency.setText(Float.toString(userPortfolioCoin.getPlPortfolioCurrency()));
            plInPurchaseCurrency.setText(Float.toString(userPortfolioCoin.getPlPurchaseCurrency()));
            if(userPortfolioCoin.getPl()==1){
                valueInPurchaseCurrency.setTextColor(context.getColor(R.color.colorGreen));
                valueInPorfolioCurrency.setTextColor(context.getColor(R.color.colorGreen));
                plInPurchaseCurrency.setTextColor(context.getColor(R.color.colorGreen));
                plInportfolioCurrency.setTextColor(context.getColor(R.color.colorGreen));
            }
            else {
                valueInPurchaseCurrency.setTextColor(context.getColor(R.color.colorRed));
                valueInPorfolioCurrency.setTextColor(context.getColor(R.color.colorRed));
                plInPurchaseCurrency.setTextColor(context.getColor(R.color.colorRed));
                plInportfolioCurrency.setTextColor(context.getColor(R.color.colorRed));
            }
        }
    }
}
