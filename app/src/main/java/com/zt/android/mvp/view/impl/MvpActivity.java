package com.zt.android.mvp.view.impl;

import android.support.v7.app.AppCompatActivity;

import com.zt.android.mvp.presenter.impl.MvpBasePresenter;
import com.zt.android.mvp.view.IView;

/**
 * Created by Tony on 16/11/1.
 */

public abstract class MvpActivity<P extends MvpBasePresenter> extends AppCompatActivity implements IView {
}
