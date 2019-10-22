package com.ravinada.cryptocompare.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ravinada.cryptocompare.R;
import com.ravinada.cryptocompare.room.Portfolio;

import java.util.List;

public class PortfolioSelectorAdapter extends RecyclerView.Adapter<PortfolioSelectorAdapter.PortfolioChoiceViewHolder> {
    List<Portfolio> portfolios;
    private SetPortfolio portfolioClicked;
    Context context;

    public PortfolioSelectorAdapter(SetPortfolio portfolio, Context context){
        this.portfolioClicked = portfolio;
        this.context = context;
    }
    public void setPortfolios(List<Portfolio> portfolios){
        this.portfolios = portfolios;
    }
    @NonNull
    @Override
    public PortfolioChoiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.portfolio_list,parent,false);
        return new PortfolioChoiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PortfolioChoiceViewHolder holder, int position) {
        Portfolio portfolio = portfolios.get(position);
        holder.bind(portfolio);
    }

    @Override
    public int getItemCount() {
        return portfolios.size();
    }

    public class PortfolioChoiceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView portfolioName;
        ImageView selection;
        public PortfolioChoiceViewHolder(@NonNull View itemView) {
            super(itemView);
            portfolioName = itemView.findViewById(R.id.tv_dbItem);
            selection = itemView.findViewById(R.id.iv_selected);
            itemView.setOnClickListener(this);
        }
        void bind(Portfolio portfolio){
            portfolioName.setText(portfolio.getName());
            if(portfolio.getSelected()){
                selection.setImageDrawable(context.getDrawable(R.drawable.ic_check_circle_green));
                portfolio.setSelected(false);
            }
            else {
                selection.setImageDrawable(context.getDrawable(R.drawable.ic_radio_button_unchecked_grey));
            }
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if(position!=RecyclerView.NO_POSITION){
                Portfolio portfolio = portfolios.get(position);
                portfolio.setSelected(true);
                notifyDataSetChanged();
                portfolioClicked.onPortfolioClick(portfolio);
            }
        }
    }
    public interface SetPortfolio{
        void onPortfolioClick(Portfolio portfolio);
    }
}
