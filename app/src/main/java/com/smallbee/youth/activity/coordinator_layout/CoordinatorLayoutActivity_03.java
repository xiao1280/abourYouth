package com.smallbee.youth.activity.coordinator_layout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.smallbee.youth.R;
import com.smallbee.youth.adapter.NormalAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * @author xiaoqiao
 * @time 2019/6/5  14:33
 * 用约束布局:显示商品详情


 */
public class CoordinatorLayoutActivity_03 extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout_03);


        List<String> data = new ArrayList<>();
        for(int i = 0; i <50; i++) {
            data.add("我是time "+i);
        }
        NormalAdapter recycleAdapter= new NormalAdapter(data);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvComment);
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
    }


}
