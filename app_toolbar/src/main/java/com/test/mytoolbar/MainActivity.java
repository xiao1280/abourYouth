package com.test.mytoolbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;

import cn.iwgang.countdownview.CountdownView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //先隐藏掉系统原先的导航栏: https://blog.csdn.net/itguangit/article/details/52042203
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//功能无标题
        setContentView(R.layout.activity_main);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //APP 的图标。
     //   toolbar.setLogo(R.mipmap.ic_launcher);
        // 主标题。
        toolbar.setTitle("My Title");
        // 副标题。
        toolbar.setSubtitle("Sub title");
        //Toolbar即能取代原本的 actionbar
        setSupportActionBar(toolbar);



        CountdownView mCvCountdownView = (CountdownView)findViewById(R.id.cv_countdownViewTest1);
        mCvCountdownView.start(1000*60*60 * 25); // 毫秒

        CountdownView mCvCountdownView_07 = (CountdownView)findViewById(R.id.cv_countdownViewTest_07);
        mCvCountdownView_07.start(1000*60*60 * 25); // 毫秒

         // 或者自己编写倒计时逻辑，然后调用updateShow来更新UI
//        for (int time=0; time<1000; time++) {
//            mCvCountdownView.updateShow(time);
//        }

    }



}
