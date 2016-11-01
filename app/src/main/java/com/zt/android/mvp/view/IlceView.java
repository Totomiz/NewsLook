package com.zt.android.mvp.view;

/**
 *  加载类
 * Created by Tony on 16/11/1.
 */

public interface IlceView<M> extends IView{
    //显示加载中的视图(说白了监听加载类型:下啦刷新或者上啦加载)
    public void showLoading(boolean pullToRefresh);

    //显示ContentView
    public void showContent();

    //加载错误
    public void showError(Exception e,boolean pullToRefresh);

    //绑定数据
    public void showData(M data);
}
