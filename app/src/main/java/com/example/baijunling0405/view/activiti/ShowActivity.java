package com.example.baijunling0405.view.activiti;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.baijunling0405.R;
import com.example.baijunling0405.view.fragment.OneFragment;
import com.example.baijunling0405.view.fragment.TwoFragment;
import com.example.baijunling0405.view.interfaces.IMainView;
import com.hjm.bottomtabbar.BottomTabBar;

public class ShowActivity extends AppCompatActivity  {

    private BottomTabBar banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        initView();
        initData();
    }

    private void initView() {
        banner = findViewById(R.id.banner);
    }

    private void initData() {
         banner.init(getSupportFragmentManager())
                 .setImgSize(50,50)
                 .setFontSize(9)
                 .setTabPadding(4,6,10)
                 .addTabItem("首页",R.drawable.loading_01,OneFragment.class)
                 .addTabItem("我的",R.drawable.loading_01,TwoFragment.class);

    }


}
