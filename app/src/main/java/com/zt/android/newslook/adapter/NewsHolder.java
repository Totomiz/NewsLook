package com.zt.android.newslook.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.zt.android.newslook.R;
import com.zt.android.newslook.mode.News;

/**
 * Created by Tony on 16/10/28.
 */

public class NewsHolder extends BaseViewHolder<News> {

    private TextView mTvName;
    private ImageView mImgFace;
    private TextView mTvSign;

    public NewsHolder(ViewGroup parent) {
        super(parent, R.layout.news_glance_item);
        mTvName = $(R.id.person_name);
        mTvSign = $(R.id.person_sign);
        mImgFace = $(R.id.person_face);
    }

    @Override
    public void setData(final News data) {
        mTvName.setText(data.getTitle());
        mTvSign.setText(data.getCtime());

        Glide.with(getContext())
                .load(data.getPicUrl())
                .placeholder(R.mipmap.ic_launcher)
                .centerCrop()
                .into(mImgFace);
    }
}
