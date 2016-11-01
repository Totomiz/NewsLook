package com.zt.android.mvp.presenter.impl;

import com.zt.android.mvp.presenter.IPresenter;
import com.zt.android.mvp.view.IView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by Tony on 16/11/1.
 */

public abstract class MvpBasePresenter<V extends IView> implements IPresenter<V> {
    private Reference<V> view;
    @Override
    public void bindView(V view) {
        if(view!=null){
            this.view=new WeakReference<V>(view);
        }
    }

    @Override
    public void unBindView(V view) {
        if(this.view!=null){
            this.view=null;
        }
    }
}
