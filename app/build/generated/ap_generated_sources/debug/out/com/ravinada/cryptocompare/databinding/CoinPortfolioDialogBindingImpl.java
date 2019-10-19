package com.ravinada.cryptocompare.databinding;
import com.ravinada.cryptocompare.R;
import com.ravinada.cryptocompare.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class CoinPortfolioDialogBindingImpl extends CoinPortfolioDialogBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.lbl_add_coin, 1);
        sViewsWithIds.put(R.id.lbl_coin, 2);
        sViewsWithIds.put(R.id.cancel, 3);
        sViewsWithIds.put(R.id.buy_price, 4);
        sViewsWithIds.put(R.id.lbl_boughton, 5);
        sViewsWithIds.put(R.id.lbl_description, 6);
        sViewsWithIds.put(R.id.lbl_currency, 7);
        sViewsWithIds.put(R.id.lbl_amount, 8);
        sViewsWithIds.put(R.id.select_coin, 9);
        sViewsWithIds.put(R.id.edit_amount, 10);
        sViewsWithIds.put(R.id.edit_buy_insert_amount, 11);
        sViewsWithIds.put(R.id.edit_description, 12);
        sViewsWithIds.put(R.id.edit_date, 13);
        sViewsWithIds.put(R.id.horizontalScrollView, 14);
        sViewsWithIds.put(R.id.btn_usd, 15);
        sViewsWithIds.put(R.id.btn_eur, 16);
        sViewsWithIds.put(R.id.btn_gbp, 17);
        sViewsWithIds.put(R.id.btn_btc, 18);
        sViewsWithIds.put(R.id.btn_eth, 19);
        sViewsWithIds.put(R.id.btn_gold, 20);
        sViewsWithIds.put(R.id.btn_aud, 21);
        sViewsWithIds.put(R.id.btn_brl, 22);
        sViewsWithIds.put(R.id.btn_cad, 23);
        sViewsWithIds.put(R.id.btn_cny, 24);
        sViewsWithIds.put(R.id.btn_hkd, 25);
        sViewsWithIds.put(R.id.btn_inr, 26);
        sViewsWithIds.put(R.id.btn_jpy, 27);
        sViewsWithIds.put(R.id.btn_krw, 28);
        sViewsWithIds.put(R.id.btn_nzd, 29);
        sViewsWithIds.put(R.id.btn_php, 30);
        sViewsWithIds.put(R.id.btn_pln, 31);
        sViewsWithIds.put(R.id.btn_ron, 32);
        sViewsWithIds.put(R.id.btn_rub, 33);
        sViewsWithIds.put(R.id.btn_sgd, 34);
        sViewsWithIds.put(R.id.btn_vef, 35);
        sViewsWithIds.put(R.id.btn_submit, 36);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public CoinPortfolioDialogBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 37, sIncludes, sViewsWithIds));
    }
    private CoinPortfolioDialogBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Button) bindings[21]
            , (android.widget.Button) bindings[22]
            , (android.widget.Button) bindings[18]
            , (android.widget.Button) bindings[23]
            , (android.widget.Button) bindings[24]
            , (android.widget.Button) bindings[19]
            , (android.widget.Button) bindings[16]
            , (android.widget.Button) bindings[17]
            , (android.widget.Button) bindings[20]
            , (android.widget.Button) bindings[25]
            , (android.widget.Button) bindings[26]
            , (android.widget.Button) bindings[27]
            , (android.widget.Button) bindings[28]
            , (android.widget.Button) bindings[29]
            , (android.widget.Button) bindings[30]
            , (android.widget.Button) bindings[31]
            , (android.widget.Button) bindings[32]
            , (android.widget.Button) bindings[33]
            , (android.widget.Button) bindings[34]
            , (android.widget.Button) bindings[36]
            , (android.widget.Button) bindings[15]
            , (android.widget.Button) bindings[35]
            , (android.widget.TextView) bindings[4]
            , (android.widget.ImageView) bindings[3]
            , (android.widget.EditText) bindings[10]
            , (android.widget.EditText) bindings[11]
            , (android.widget.EditText) bindings[13]
            , (android.widget.EditText) bindings[12]
            , (android.widget.HorizontalScrollView) bindings[14]
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[6]
            , (android.widget.EditText) bindings[9]
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