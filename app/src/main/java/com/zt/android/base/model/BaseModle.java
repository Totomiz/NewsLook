package com.zt.android.base.model;

import android.content.Context;

import com.zt.android.mvp.modle.IModle;

/**
 * Created by Tony on 16/11/1.
 */

public abstract class BaseModle implements IModle {
    private Context context;

    public BaseModle(Context context){
        this.context = context;
    }
}
