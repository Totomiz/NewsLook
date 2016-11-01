package com.zt.android.newslook.adapter;

import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.zt.android.newslook.mode.Meitu;

/**
 * Created by Tony on 16/10/28.
 */

public class PicHolder extends BaseViewHolder<Meitu> {

    private ImageView imgPicture;
    public PicHolder(ViewGroup parent) {
        super(new ImageView(parent.getContext()));
        imgPicture = (ImageView) itemView;
        imgPicture.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        imgPicture.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    @Override
    public void setData(Meitu data) {
        final DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
        Glide.with(getContext())
                .load(data.getPicUrl())
                .asBitmap()
                .into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
                          @Override
                          public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
                              int width = dm.widthPixels / 2 - 10;//宽度为屏幕宽度一半
                              int height = bitmap.getHeight() * width / bitmap.getWidth();//计算View的高度
                              //获取bitmap信息，可赋值给外部变量操作，也可在此时行操作。
                              ViewGroup.LayoutParams params = imgPicture.getLayoutParams();
                              params.height = height;
                              params.width = width;
                              imgPicture.setLayoutParams(params);
                              imgPicture.setScaleType(ImageView.ScaleType.FIT_XY);
                              imgPicture.setImageBitmap(bitmap);

                          }
                      }
                );
    }
}
