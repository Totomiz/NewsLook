package com.zt.android.mvp.presenter;

import com.zt.android.mvp.view.IView;

/**
 * Created by Tony on 16/11/1.
 */

public interface IPresenter<V extends IView> {
    void bindView(V view);
    void unBindView(V view);
}
