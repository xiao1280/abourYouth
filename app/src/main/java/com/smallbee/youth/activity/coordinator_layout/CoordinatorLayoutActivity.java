package com.smallbee.youth.activity.coordinator_layout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.smallbee.youth.R;

/**
 * @author xiaoqiao
 * @time 2019/6/5  14:33
 * @describe CoordinatorLayout三种用法总结
 * https://blog.csdn.net/qq_34882418/article/details/81190363
 *
 *
 * 一、CoordinatorLayout+FloatingActionButton
     他们两个不是必须组合在一起的。之所以放在一起，
     是因为CoordinatorLayout为父布局时，弹出的Snackbar会自动顶起FloatingActionButton，
     而不至于压在后者之上。故组合

 */
public class CoordinatorLayoutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout);

        // Snackbar用法
        findViewById(R.id.faBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "已删除一个会话", Snackbar.LENGTH_SHORT)
                        .setAction("撤销", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                Toast.makeText(CoordinatorLayoutActivity.this, "撤销了删除", Toast.LENGTH_SHORT).show();

                            }
                        }).show();
            }
        });

    }
}
