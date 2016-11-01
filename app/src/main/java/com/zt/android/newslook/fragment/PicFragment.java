package com.zt.android.newslook.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.zt.android.newslook.R;
import com.zt.android.newslook.adapter.PicAdapter;
import com.zt.android.newslook.api.ApiService;
import com.zt.android.newslook.mode.Meitu;
import com.zt.android.newslook.mode.MeituGson;
import com.zt.android.newslook.utils.SizeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class PicFragment extends BaseFragment {
    private int page = 0;


    @BindView(R.id.recycler_view)
    EasyRecyclerView recyclerView;
    private PicAdapter adapter;


    public PicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pic, container, false);
        ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter = new PicAdapter(getActivity()));
        //添加边框
        SpaceDecoration itemDecoration = new SpaceDecoration((int) SizeUtils.dp2px(getContext(), 10));
        itemDecoration.setPaddingEdgeSide(true);
        itemDecoration.setPaddingStart(true);
        itemDecoration.setPaddingHeaderFooter(false);
        recyclerView.addItemDecoration(itemDecoration);


        //更多加载
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                addData();
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
                        page = 0;
                        adapter.clear();
                        addData();
                    }
                }, 1000);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addData();
    }

    private void addData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())//添加 json 转换器
                //    compile 'com.squareup.retrofit2:adapter-rxjava:2.1.0'
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//添加 RxJava 适配器
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getPicData(ApiService.key, "10", page)
                .subscribeOn(Schedulers.io())
                .map(new Func1<MeituGson, List<Meitu>>() {
                    @Override
                    public List<Meitu> call(MeituGson meituGson) {
                        List<Meitu> meiNvList = new ArrayList<Meitu>();
                        for (MeituGson.NewslistBean newslistBean : meituGson.getNewslist()) {
                            Meitu m1 = new Meitu();
                            m1.setTitle(newslistBean.getTitle());
                            m1.setCtime(newslistBean.getCtime());
                            m1.setDescription(newslistBean.getDescription());
                            m1.setPicUrl(newslistBean.getPicUrl());
                            m1.setUrl(newslistBean.getUrl());
                            meiNvList.add(m1);
                        }
                        return meiNvList; // 返回类型
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Meitu>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getContext(),
                                "网络连接失败", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNext(List<Meitu> meitus) {
                        adapter.addAll(meitus);
                    }
                });
        page += 1;
    }

}
