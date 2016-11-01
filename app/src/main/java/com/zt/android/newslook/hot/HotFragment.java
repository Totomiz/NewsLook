package com.zt.android.newslook.hot;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.zt.android.base.BaseFragment;
import com.zt.android.newslook.R;
import com.zt.android.newslook.adapter.NewsAdapter;
import com.zt.android.newslook.utils.SizeUtils;

/**
 * Created by Tony on 16/11/1.
 */

public class HotFragment extends BaseFragment {
    EasyRecyclerView recyclerView;
    private NewsAdapter adapter;
    private int page = 0;
    @Override
    protected void initContentView(View viewContent) {
        recyclerView= (EasyRecyclerView) viewContent.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        SpaceDecoration itemDecoration = new SpaceDecoration(SizeUtils.dp2px(getActivity(), 8));
        //        DividerDecoration itemDecoration = new DividerDecoration(Color.GRAY, SizeUtils.dp2px(getActivity(), 0.5f), SizeUtils.dp2px(getActivity(), 72));//颜色 & 高度 & 左边距 & 右边距
        //        itemDecoration.setDrawLastItem(true);//有时候你不想让最后一个item有分割线,默认true.
        //        itemDecoration.setDrawHeaderFooter(false);//是否对Header于Footer有效,默认false.
        itemDecoration.setPaddingEdgeSide(true);
        itemDecoration.setPaddingStart(true);
        itemDecoration.setPaddingHeaderFooter(false);
        recyclerView.addItemDecoration(itemDecoration);
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_news;
    }

    @Override
    public void initData() {
        super.initData();
        adapter = new NewsAdapter(getActivity());
        recyclerView.setAdapter(adapter);

    }
}
