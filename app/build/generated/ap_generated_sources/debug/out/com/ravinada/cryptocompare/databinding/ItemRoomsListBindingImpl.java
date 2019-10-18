package com.ravinada.cryptocompare.databinding;
import com.ravinada.cryptocompare.R;
import com.ravinada.cryptocompare.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemRoomsListBindingImpl extends ItemRoomsListBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.imgDevices, 1);
        sViewsWithIds.put(R.id.txtDeviceName, 2);
        sViewsWithIds.put(R.id.lineView, 3);
        sViewsWithIds.put(R.id.llViewCount, 4);
        sViewsWithIds.put(R.id.txtShare, 5);
        sViewsWithIds.put(R.id.txtShareCount, 6);
        sViewsWithIds.put(R.id.txtDownload, 7);
        sViewsWithIds.put(R.id.txtDownloadCount, 8);
        sViewsWithIds.put(R.id.txtView, 9);
        sViewsWithIds.put(R.id.txtViewCount, 10);
    }
    // views
    @NonNull
    private final android.widget.FrameLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemRoomsListBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }
    private ItemRoomsListBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.ImageView) bindings[1]
            , (android.view.View) bindings[3]
            , (android.widget.LinearLayout) bindings[4]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[10]
            );
        this.mboundView0 = (android.widget.FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.itemClickListener == variableId) {
            setItemClickListener((com.ravinada.cryptocompare.listner.ListItemClickListener) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setItemClickListener(@Nullable com.ravinada.cryptocompare.listner.ListItemClickListener ItemClickListener) {
        this.mItemClickListener = ItemClickListener;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): itemClickListener
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}