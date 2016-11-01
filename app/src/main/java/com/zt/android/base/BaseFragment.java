package com.zt.android.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zt.android.mvp.presenter.impl.MvpBasePresenter;
import com.zt.android.mvp.view.impl.MvpFragment;

/**
 * Created by Tony on 16/11/1.
 */

public abstract class BaseFragment<P extends MvpBasePresenter> extends MvpFragment<P>{
    private View viewContent;
    private boolean isInit;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(viewContent==null){
            inflater.inflate(getContentView(),container,false);
            initContentView(viewContent);
        }
        //判断Fragment对应的Activity是否存在这个视图
        ViewGroup parent = (ViewGroup) viewContent.getParent();
        if (parent != null){
            //如果存在,那么我就干掉,重写添加,这样的方式我们就可以缓存视图
            parent.removeView(viewContent);
        }
        return viewContent;
    }

    @Override
    public P bindPresenter() {
        return null;
    }

    protected abstract void initContentView(View viewContent);

    protected abstract int getContentView();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(!isInit){
            initData();
        }

    }

    public void initData() {

    }
}
