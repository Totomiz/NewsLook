package com.zt.android.newslook.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zt.android.newslook.mode.Meitu;
import com.zt.android.newslook.mode.MeituGson;

/**
 * Created by Tony on 16/10/28.
 */

public class PicAdapter extends RecyclerArrayAdapter<Meitu> {
    public PicAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new PicHolder(parent);
    }
}
