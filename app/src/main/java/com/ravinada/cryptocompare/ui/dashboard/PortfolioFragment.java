package com.ravinada.cryptocompare.ui.dashboard;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.ravinada.cryptocompare.R;
import com.ravinada.cryptocompare.databinding.FragmentPotfolioBinding;
import com.ravinada.cryptocompare.dialogues.PortfolioDialogue;


public class PortfolioFragment extends Fragment {
    private final String TAG = PortfolioFragment.class.getSimpleName();

    FragmentPotfolioBinding binding;
    String BASE_URL = "https://min-api.cryptocompare.com";

    public static PortfolioFragment newInstance() {
        return new PortfolioFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_potfolio, container, false);
        final View dialogViewCoin = inflater.inflate(R.layout.coin_portfolio_dialog, null);
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.Theme_AppCompat_Light_NoActionBar));
        binding.btnAddPortfolio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddPortfolioDialog();
            }
        });


        return binding.getRoot();
    }
      private void showAddPortfolioDialog(){
        FragmentManager manager = getFragmentManager();
        PortfolioDialogue portfolioDialogue = new PortfolioDialogue();
          assert manager != null;
          portfolioDialogue.show(manager,"PORTFOLIO_DIALOG");
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        binding.swipeRefresh.setOnRefreshListener(
//                new SwipeRefreshLayout.OnRefreshListener() {
//                    @Override
//                    public void onRefresh() {
//                        //Log.i(LOG_TAG, "onRefresh called from SwipeRefreshLayout");
//
//                        // This method performs the actual data-refresh operation.
//                        // The method calls setRefreshing(false) when it's finished.
//                        prepareNews();
//                    }
//                }
//        );
//
//    }

//        private Dialog dialog; // class variable
//
//        private void showDialog
//        {
//            dialog = new Dialog(Activity.this);  // always give context of activity.
//            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//            dialog.setContentView(R.layout.customDialog);
//
//
//            Button dialogButton = (Button) dialog.findViewById(R.id.btnClose);
//
//            dialogButton.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dialog.dismiss();
//                }
//            });
//
//            dialog.show();
//        }
    }
}
