package com.smallbee.youth.activity.coordinator_layout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.smallbee.youth.R;
import com.smallbee.youth.adapter.NormalAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoqiao
 * @time 2019/6/5  14:33
 * @describe CoordinatorLayout三种用法总结
 * https://blog.csdn.net/qq_34882418/article/details/81190363
 *
 * 二、 CoordinatorLayout+AppBarLayout
        toolbar根据滑动隐藏
 */
public class CoordinatorLayoutActivity_02 extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout_02);

        List<String> data = new ArrayList<>();
        for(int i = 0; i <20; i++) {
            data.add("我是time "+i);
        }
        NormalAdapter  recycleAdapter= new NormalAdapter(data);


        RecyclerView   recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
       //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        recyclerView.setAdapter(recycleAdapter);
        //设置分隔线
     //   recyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        //设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }
}
