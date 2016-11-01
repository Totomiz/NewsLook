package com.zt.android.mvp.view.impl;

import com.zt.android.mvp.view.IlceView;

/**
 * Created by Tony on 16/11/1.
 */

public abstract class MvpBaseLceView<M> implements IlceView<M>{
    @Override
    public void showLoading(boolean pullToRefresh) {

    }

    @Override
    public void showContent() {

    }

    @Override
    public void showError(Exception e, boolean pullToRefresh) {

    }

    @Override
    public void showData(M data) {

    }
}
