package com.ravinada.cryptocompare.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ravinada.cryptocompare.R;
import com.ravinada.cryptocompare.modelclasses.Currency;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CoinSelectorAdapter extends RecyclerView.Adapter<CoinSelectorAdapter.CoinSelectorViewHolder> {

private List<Currency> currencyList;
private SetCoin coinClicked;

public CoinSelectorAdapter(SetCoin coinClicked) {

        this.coinClicked =coinClicked;
        }
public void setCurrencyList( List<Currency> currencyList){
    this.currencyList = currencyList;
}

@NonNull
@Override
public CoinSelectorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.select_coin_dialog_item, parent, false);

        return new CoinSelectorViewHolder(itemView);
        }

@Override
public void onBindViewHolder(final CoinSelectorViewHolder holder, int position) {
        Currency currency = currencyList.get(position);
        String name = currency.getFullName();
        holder.coinName.setText(name);
        Picasso.get().load(currency.getImageURL()).into(holder.coinImage);
        }

@Override
public int getItemCount() {
        return currencyList.size();
        }

public class CoinSelectorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView coinName;
    public de.hdodenhof.circleimageview.CircleImageView coinImage;

    public CoinSelectorViewHolder(View view) {
        super(view);
        coinImage = view.findViewById(R.id.coin_selector_image);
        coinName = view.findViewById(R.id.selector_coin_name);
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
             coinClicked.onCoinClick(currencyList.get(getAdapterPosition()),1);
        }
    }
    public interface SetCoin{
    void onCoinClick(Currency currency,int flag);
    }
}
