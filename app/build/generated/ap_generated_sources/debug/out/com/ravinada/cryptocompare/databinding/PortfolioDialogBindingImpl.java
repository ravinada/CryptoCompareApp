package com.ravinada.cryptocompare.databinding;
import com.ravinada.cryptocompare.R;
import com.ravinada.cryptocompare.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class PortfolioDialogBindingImpl extends PortfolioDialogBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.lbl_add_portfolio, 1);
        sViewsWithIds.put(R.id.lbl_name, 2);
        sViewsWithIds.put(R.id.lbl_currency, 3);
        sViewsWithIds.put(R.id.txt_private, 4);
        sViewsWithIds.put(R.id.txt_public, 5);
        sViewsWithIds.put(R.id.cancel, 6);
        sViewsWithIds.put(R.id.buy_price, 7);
        sViewsWithIds.put(R.id.lbl_descriptionPortfolio, 8);
        sViewsWithIds.put(R.id.et_portfolioName, 9);
        sViewsWithIds.put(R.id.et_description, 10);
        sViewsWithIds.put(R.id.selectCurrencyPurchaseCoin, 11);
        sViewsWithIds.put(R.id.btn_submit, 12);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public PortfolioDialogBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds));
    }
    private PortfolioDialogBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Button) bindings[12]
            , (android.widget.TextView) bindings[7]
            , (android.widget.ImageView) bindings[6]
            , (android.widget.EditText) bindings[10]
            , (android.widget.EditText) bindings[9]
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[2]
            , (androidx.recyclerview.widget.RecyclerView) bindings[11]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[5]
            );
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
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
        if (BR.CurrencyTypePurchaseAdapter == variableId) {
            setCurrencyTypePurchaseAdapter((com.ravinada.cryptocompare.CurrencyTypePurchaseAdapter) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setCurrencyTypePurchaseAdapter(@Nullable com.ravinada.cryptocompare.CurrencyTypePurchaseAdapter CurrencyTypePurchaseAdapter) {
        this.mCurrencyTypePurchaseAdapter = CurrencyTypePurchaseAdapter;
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
        flag 0 (0x1L): CurrencyTypePurchaseAdapter
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}