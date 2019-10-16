package com.ravinada.cryptocompare.ui.dashboard;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class MainListViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    ArrayList<String> likeList = new ArrayList<>();

    public ArrayList<String> getLikeList(){
        for (int i=0;i<25;i++){
            likeList.add("MAIN ITEM "+i);
        }
        return likeList;
    }
}
