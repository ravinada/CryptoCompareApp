package com.ravinada.cryptocompare.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;
import com.ravinada.cryptocompare.R;
import com.ravinada.cryptocompare.databinding.MainListFragmentBinding;

import java.util.Objects;

public class MainListFragment extends Fragment {
    private final String TAG = MainListFragment.class.getSimpleName();
    private MainListFragmentBinding binding;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    public static MainListFragment newInstance() {
        return new MainListFragment();
    }
    private TextView following,topVolume;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_list_fragment, container, false);
        following = binding.getRoot().findViewById(R.id.tvFollowing);
        topVolume = binding.getRoot().findViewById(R.id.tvTopVolume);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.llDashboardMain, FollowingListFragment.newInstance()).commit();
        following.setOnClickListener(view -> {
            following.setBackground(getActivity().getDrawable(R.drawable.topvolume_bg_green));
            topVolume.setBackground(getActivity().getDrawable(R.drawable.topvolumebg));
            following.setTextColor(getActivity().getColor(R.color.white));
            topVolume.setTextColor(getActivity().getColor(R.color.colorBlack));
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.llDashboardMain, FollowingListFragment.newInstance()).commit();
        });
        topVolume.setOnClickListener(view -> {
            topVolume.setBackground(getActivity().getDrawable(R.drawable.topvolume_bg_green));
            following.setBackground(getActivity().getDrawable(R.drawable.topvolumebg));
            following.setTextColor(getActivity().getColor(R.color.colorBlack));
            topVolume.setTextColor(getActivity().getColor(R.color.white));
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.llDashboardMain, TopVolumeFragment.newInstance()).commit();
        });

    }
}
