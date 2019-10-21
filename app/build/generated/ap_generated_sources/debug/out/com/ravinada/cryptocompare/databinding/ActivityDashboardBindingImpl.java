package com.ravinada.cryptocompare.databinding;
import com.ravinada.cryptocompare.R;
import com.ravinada.cryptocompare.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityDashboardBindingImpl extends ActivityDashboardBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.container, 1);
        sViewsWithIds.put(R.id.toolbar, 2);
        sViewsWithIds.put(R.id.user_menu, 3);
        sViewsWithIds.put(R.id.searchBar, 4);
        sViewsWithIds.put(R.id.currencyTag, 5);
        sViewsWithIds.put(R.id.txtPortfolioName, 6);
        sViewsWithIds.put(R.id.portfolio_edit, 7);
        sViewsWithIds.put(R.id.viewPager, 8);
        sViewsWithIds.put(R.id.tabsBottom, 9);
        sViewsWithIds.put(R.id.tabMenuWatchList, 10);
        sViewsWithIds.put(R.id.tabMenuPortfolio, 11);
        sViewsWithIds.put(R.id.tabMenuNews, 12);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityDashboardBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds));
    }
    private ActivityDashboardBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.coordinatorlayout.widget.CoordinatorLayout) bindings[0]
            , (android.widget.RelativeLayout) bindings[1]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[7]
            , (androidx.appcompat.widget.SearchView) bindings[4]
            , (com.google.android.material.tabs.TabItem) bindings[12]
            , (com.google.android.material.tabs.TabItem) bindings[11]
            , (com.google.android.material.tabs.TabItem) bindings[10]
            , (com.google.android.material.tabs.TabLayout) bindings[9]
            , (android.widget.RelativeLayout) bindings[2]
            , (android.widget.TextView) bindings[6]
            , (android.widget.ImageButton) bindings[3]
            , (androidx.viewpager.widget.ViewPager) bindings[8]
            );
        this.activityMain.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
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
            return variableSet;
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
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}