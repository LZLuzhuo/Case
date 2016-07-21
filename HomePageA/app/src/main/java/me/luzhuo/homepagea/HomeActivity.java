/* Copyright 2016 Luzhuo. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.luzhuo.homepagea;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import me.luzhuo.homepagea.presenter.HomePresenter;
import me.luzhuo.homepagea.ui.view.IHomeView;
import me.luzhuo.homepagea.widget.banner.LZConvenientBanner;

/**
 * =================================================
 * <p>
 * Author: Luzhuo
 * <p>
 * Version: 1.0
 * <p>
 * Creation Date: 2016/7/20 14:02
 * <p>
 * Description: 首页案例A
 * <p>
 * Revision History:
 * <p>
 * Copyright: Copyright 2016 Luzhuo. All rights reserved.
 * <p>
 * =================================================
 **/
public class HomeActivity extends Activity implements IHomeView{
    @ViewInject(R.id.home_refresh)
    MaterialRefreshLayout home_refresh;
    @ViewInject(R.id.home_list)
    ListView home_list;
    @ViewInject(R.id.home_toolbar)
    RelativeLayout home_toolbar;
    @ViewInject(R.id.home_title_back)
    TextView home_title_back;
    @ViewInject(R.id.home_title_white)
    TextView home_title_white;

    private LZConvenientBanner home_banner, home_entrance;
    private LinearLayout home_dots;

    private HomePresenter homePresenter;
    private View viewHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ViewUtils.inject(this); //注入view和事件
        viewHeader = View.inflate(this, R.layout.activity_header, null);
        homePresenter = new HomePresenter(this);
        initData();
    }

    private void initData() {
        home_banner = (LZConvenientBanner) viewHeader.findViewById(R.id.home_banner);
        home_entrance = (LZConvenientBanner) viewHeader.findViewById(R.id.home_entrance);
        home_dots = (LinearLayout) viewHeader.findViewById(R.id.home_dots);

        // 初始化头部数据
        homePresenter.initHeaderData(home_list, viewHeader);
        // 初始化动态入口
        homePresenter.initEntrance(home_entrance, home_dots);
        // 初始化Banner数据
        homePresenter.initBanner(home_banner);
        // 初始化Listview数据
        homePresenter.initListView(home_refresh);
    }

    @Override
    public void onHeaderScroll(float percent) {
        home_toolbar.setAlpha(percent);
        home_title_white.setAlpha(1 - percent);
        home_title_back.setAlpha(percent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        home_banner.startTurning(5000); //开始自动翻页
    }

    @Override
    protected void onPause() {
        super.onPause();
        home_banner.stopTurning(); //停止翻页
    }

}
