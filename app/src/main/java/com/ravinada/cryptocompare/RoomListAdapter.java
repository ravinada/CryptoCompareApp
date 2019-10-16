package com.ravinada.cryptocompare;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.ravinada.cryptocompare.databinding.ItemRoomsListBinding;
import com.ravinada.cryptocompare.listner.ListItemClickListener;

import java.util.List;

public class RoomListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = RoomListAdapter.class.getSimpleName();
    private final int VIEW_TYPE_ITEM = 1;
    private Activity mActivity;
    private List<String> roomDataList;
    private ListItemClickListener listItemClickListener;

    public RoomListAdapter(Activity activity, List<String> roomDataList, ListItemClickListener allDeviceItemClickListener) {
        this.roomDataList = roomDataList;
        mActivity = activity;
        this.listItemClickListener = allDeviceItemClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        return VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            ItemRoomsListBinding itemBinding = ItemRoomsListBinding.inflate(layoutInflater, parent, false);
            return new ViewHolder(itemBinding);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder mViewHolder, final int position) {

        if (mViewHolder instanceof ViewHolder) {
            try {

                final ViewHolder holder = (ViewHolder) mViewHolder;
                holder.bind("Item");
                holder.binding.setItemClickListener(listItemClickListener);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getItemCount() {
        return roomDataList == null ? 0 : roomDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemRoomsListBinding binding;

        public ViewHolder(ItemRoomsListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(String item) {
            binding.txtDeviceName.setVisibility(View.VISIBLE);
            binding.executePendingBindings();
        }
    }

}
