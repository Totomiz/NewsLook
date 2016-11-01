package com.zt.android.newslook.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.zt.android.newslook.R;
import com.zt.android.newslook.adapter.NewsAdapter;
import com.zt.android.newslook.api.ApiService;
import com.zt.android.newslook.mode.News;
import com.zt.android.newslook.mode.NewsGson;
import com.zt.android.newslook.utils.SizeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends BaseFragment {
    private static final String TAG = "NewsFragment";


    @BindView(R.id.recycler_view)
    EasyRecyclerView recyclerView;
    private NewsAdapter adapter;
    private int page = 0;


    public NewsFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, view);
        adapter = new NewsAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        SpaceDecoration itemDecoration = new SpaceDecoration(SizeUtils.dp2px(getActivity(), 8));
        //        DividerDecoration itemDecoration = new DividerDecoration(Color.GRAY, SizeUtils.dp2px(getActivity(), 0.5f), SizeUtils.dp2px(getActivity(), 72));//颜色 & 高度 & 左边距 & 右边距
        //        itemDecoration.setDrawLastItem(true);//有时候你不想让最后一个item有分割线,默认true.
        //        itemDecoration.setDrawHeaderFooter(false);//是否对Header于Footer有效,默认false.
        itemDecoration.setPaddingEdgeSide(true);
        itemDecoration.setPaddingStart(true);
        itemDecoration.setPaddingHeaderFooter(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(adapter);
        //更多加载
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                getData();
            }

            @Override
            public void onMoreClick() {

            }
        });

        //写刷新事件
        recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.clear();
                        page = 0;
                        getData();
                    }
                }, 1000);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getData();
    }

    private void getData() {
        Log.d(TAG, "getData: " + page);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiService.baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())//添加 json 转换器
                //    compile 'com.squareup.retrofit2:adapter-rxjava:2.1.0'
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//添加 RxJava 适配器
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<NewsGson> newsData = apiService.getNewsData(ApiService.key, "10", page);
        newsData.subscribeOn(Schedulers.io())
                .map(new Func1<NewsGson, List<News>>() {
                    @Override
                    public List<News> call(NewsGson newsgson) { //
                        List<News> newsList = new ArrayList<News>();
                        for (NewsGson.NewslistBean newslistBean : newsgson.getNewslist()) {
                            News new1 = new News();
                            new1.setTitle(newslistBean.getTitle());
                            new1.setCtime(newslistBean.getCtime());
                            new1.setDescription(newslistBean.getDescription());
                            new1.setPicUrl(newslistBean.getPicUrl());
                            new1.setUrl(newslistBean.getUrl());
                            newsList.add(new1);
                        }
                        return newsList; // 返回类型
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<News>>() {
                    @Override
                    public void onNext(List<News> newsList) {
                        adapter.addAll(newsList);
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getContext(),
                                "网络连接失败", Toast.LENGTH_LONG).show();
                    }
                });
        page += 1;
    }


}

