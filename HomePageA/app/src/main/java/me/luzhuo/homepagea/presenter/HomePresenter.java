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
package me.luzhuo.homepagea.presenter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;

import java.util.ArrayList;

import me.luzhuo.homepagea.R;
import me.luzhuo.homepagea.bean.Entrance;
import me.luzhuo.homepagea.callback.GlobalNetCallBack;
import me.luzhuo.homepagea.callback.OnEntranceClickListener;
import me.luzhuo.homepagea.callback.impl.BaseGlobalNetCallBack;
import me.luzhuo.homepagea.callback.impl.OnPageChangeListenerImpl;
import me.luzhuo.homepagea.callback.impl.OnScrollListenerImpl;
import me.luzhuo.homepagea.model.IHome;
import me.luzhuo.homepagea.model.impl.IHomeImpl;
import me.luzhuo.homepagea.ui.adapter.HomeAdapter;
import me.luzhuo.homepagea.ui.view.IHomeView;
import me.luzhuo.homepagea.utils.DisplayUtil;
import me.luzhuo.homepagea.widget.banner.BannerHolderView;
import me.luzhuo.homepagea.widget.banner.EntranceHolderView;
import me.luzhuo.homepagea.widget.banner.LZConvenientBanner;

/**
 * =================================================
 * <p>
 * Author: Luzhuo
 * <p>
 * Version: 1.0
 * <p>
 * Creation Date: 2016/7/20 16:04
 * <p>
 * Description:
 * <p>
 * Revision History:
 * <p>
 * Copyright:
 * <p>
 * =================================================
 **/
public class HomePresenter implements AdapterView.OnItemClickListener {
    private IHome ihome;
    private IHomeView iHomeView;

    private int pagenumber = 0; //0开始
    private int requestType = 0; //0 刷新, 1 分页加载
    private boolean haveMoreData = true;

    public HomePresenter(IHomeView iHomeView){
        ihome = new IHomeImpl();
        this.iHomeView = iHomeView;
    }

    GlobalNetCallBack callback = new BaseGlobalNetCallBack(){
        @Override
        public void getListviewData(boolean isSuccess, boolean isHaveMoreData, ArrayList<String> data) {
            haveMoreData = isHaveMoreData;
            showIndentDatas(isSuccess, data);
        }
        @Override
        public void getBannerData(boolean isSuccess, ArrayList<String> data) {
            if(isSuccess){
                banners.clear();
                banners.addAll(data);
                initRollViewPager();
            }
        }

        @Override
        public void getEntrance(boolean isSuccess, ArrayList<Entrance.Data> data) {
            if(isSuccess){
                entrances.clear();
                entrances.addAll(data);
                initEntranceViewPager();
                initEntranceDots();
            }
        }
    };

    // ------------------------------------------------------------ HeaderData ----------
    private ListView listView;
    private View viewHeader;
    private HomeAdapter homeadapter;
    private ArrayList<String> listviewdatas = new ArrayList<>();
    public void initHeaderData(ListView listView, View viewHeader) {
        // 初始化listview
        this.listView = listView;
        this.viewHeader = viewHeader;

        listView.setOnItemClickListener(this);
        listView.addHeaderView(viewHeader);
        listView.setOnScrollListener(scroll);
        homeadapter = new HomeAdapter((Context)iHomeView, listviewdatas);
        listView.setAdapter(homeadapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // listview 的点击事件
        if(position == 0) return;
        int pos = position - 1;

        Toast.makeText((Context)iHomeView, "HeaderData:onItemClick:".concat(String.valueOf(pos)), Toast.LENGTH_SHORT).show();
    }

    /**
     * Banner的高度
     */
    private float bannerHigh = 155f - 49; // dp banner高度 - toolbar高度
    AbsListView.OnScrollListener scroll = new OnScrollListenerImpl(){
        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            if(listView == null) return;
            if(listView.getFirstVisiblePosition() == 0){ // == 0 为头
                // 按比例计算显示透明度
                View c = listView.getChildAt(0);
                if (c == null ) return;
                int top = Math.abs(c.getTop()); // 滑动的高度 负数

                int bannerHighDp = DisplayUtil.pxTodip((Context)iHomeView, top);
                if(bannerHighDp >= bannerHigh) iHomeView.onHeaderScroll(1f);
                else iHomeView.onHeaderScroll((float)bannerHighDp / bannerHigh);
            }else{
                iHomeView.onHeaderScroll(1f); // 全显示
            }
        }
    };
    // ------------------------------------------------------------ HeaderData ----------



    // ------------------------------------------------------------ Entrance ----------
    private ArrayList<Entrance.Data> entrances = new ArrayList<>();
    private LZConvenientBanner convenientEntrance;
    private LinearLayout home_dots;
    public void initEntrance(LZConvenientBanner convenientEntrance, LinearLayout home_dots) {
        this.convenientEntrance = convenientEntrance;
        this.home_dots = home_dots;
        ihome.getEntrance(callback);
    }

    private ArrayList<ArrayList<Entrance.Data>> entranceslist = new ArrayList<>(); // 存放分组后的集合
    private void initEntranceViewPager(){
        if(entrances.size() == 0) return;
        entranceslist.clear();

        // 将获取到的数据分成每8个一组
        ArrayList<Entrance.Data> data = null;
        int temp = 0;
        int maxSize = 8;
        for(int x = 0; x < entrances.size(); x++){
            if(temp == 0) data = new ArrayList<>();
            data.add(entrances.get(x));
            temp++;
            if(temp >= maxSize || x == entrances.size() - 1){
                temp = 0;
                entranceslist.add(data);
            }
        }

        convenientEntrance.setPages(new CBViewHolderCreator<EntranceHolderView>() {
            @Override
            public EntranceHolderView createHolder() {
                return new EntranceHolderView(onEntranceClickListener);
            }
        }, entranceslist).setOnPageChangeListener(onPageChangeListener) //监听翻页事件
                .setCanLoop(false);
    }

    ViewPager.OnPageChangeListener onPageChangeListener = new OnPageChangeListenerImpl(){
        int oldPosition = 0;
        @Override
        public void onPageSelected(int position) {
            if (dots != null && dots.size() > 0) {
                dots.get(position).setBackgroundResource(R.mipmap.ic_page_indicator_focused_blue);
                dots.get(oldPosition).setBackgroundResource(R.mipmap.ic_page_indicator_gray);
            }
            oldPosition = position;
        }
    };

    OnEntranceClickListener onEntranceClickListener = new OnEntranceClickListener() {
        @Override
        public void onEntranceClick(String title) {
            Toast.makeText((Context) iHomeView, "Entrance:onEntranceClick:".concat(title), Toast.LENGTH_SHORT).show();
        }
    };

    private ArrayList<View> dots;
    private void initEntranceDots(){
        home_dots.removeAllViews();

        // 添加圆点
        dots = new ArrayList<View>();
        for (int i = 0; i < entranceslist.size(); i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DisplayUtil.dipTopx((Context)iHomeView, 5), DisplayUtil.dipTopx((Context)iHomeView, 5));  //点View的大小
            params.setMargins(DisplayUtil.dipTopx((Context)iHomeView, 2), 0, DisplayUtil.dipTopx((Context)iHomeView, 2), 0); // 点View的间距
            View m = new View((Context)iHomeView);
            if (i == 0){
                m.setBackgroundResource(R.mipmap.ic_page_indicator_focused_blue);
            }else{
                m.setBackgroundResource(R.mipmap.ic_page_indicator_gray);
            }
            m.setLayoutParams(params);
            dots.add(m);
            home_dots.addView(m);
        }

        // 只有一页的时候把点隐藏
        if(entranceslist.size() == 1) home_dots.setVisibility(View.GONE);
        else home_dots.setVisibility(View.VISIBLE);
    }
    // ------------------------------------------------------------ Entrance ----------



    // ------------------------------------------------------------ Banner ----------
    private ArrayList<String> banners = new ArrayList<>();
    private LZConvenientBanner convenientBanner;
    public void initBanner(LZConvenientBanner convenientBanner) {
        this.convenientBanner = convenientBanner;
        ihome.getBannerData(callback);
    }

    private void initRollViewPager(){
        if(banners.size() == 0) return;

        convenientBanner.setPages(new CBViewHolderCreator<BannerHolderView>() {
            @Override
            public BannerHolderView createHolder() {
                return new BannerHolderView();
            }
        }, banners)
                // 设置两个点图片作为翻页指示器，不设置则没有指示器
                .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused})
                // 设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(banneronitemclick);
                // .setManualPageable(false); // 设置不能手动影响(手指不能滑动);

        // 控制是否循环
        if(banners.size() == 1) convenientBanner.setCanLoop(false);
        else convenientBanner.setCanLoop(true);
    }

    OnItemClickListener banneronitemclick = new OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            Toast.makeText((Context) iHomeView, "Banner:onItemClick:".concat(String.valueOf(position)), Toast.LENGTH_SHORT).show();
        }
    };
    // ------------------------------------------------------------ Banner ----------



    // ------------------------------------------------------------ listview ----------
    private MaterialRefreshLayout home_refresh;
    public void initListView(MaterialRefreshLayout home_refresh) {
        // 初始化listview条目数据
        // 初始化上拉加载,下拉刷新控件  (自动刷新获取)
        this.home_refresh = home_refresh;
        // home_refresh.autoRefresh(); // 首页自动刷新,体验极差
        home_refresh.setMaterialRefreshListener(refreshlis);
        refreshlis.onRefresh(home_refresh);
    }

    MaterialRefreshListener refreshlis = new MaterialRefreshListener() {
        @Override
        public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
            // 刷新
            requestType = 0;
            pagenumber = 0;
            ihome.getListviewData(pagenumber, callback);
            materialRefreshLayout.finishRefreshLoadMore();
        }
        @Override
        public void onRefreshLoadMore(final MaterialRefreshLayout materialRefreshLayout) {
            // 加载
            if(!haveMoreData){
                materialRefreshLayout.finishRefresh();
                materialRefreshLayout.finishRefreshLoadMore();
                return;
            }

            requestType = 1;
            pagenumber ++;
            ihome.getListviewData(pagenumber, callback);
            materialRefreshLayout.finishRefresh();
        }
    };

    private void showIndentDatas(boolean isSuccess, ArrayList<String> datas) {
        if(!isSuccess) return;

        if(requestType == 0){
            listviewdatas.clear();
            listviewdatas.addAll(datas);
        }else{
            listviewdatas.addAll(datas);
        }
        homeadapter.notifyDataSetChanged();

        home_refresh.finishRefresh();
        home_refresh.finishRefreshLoadMore();
    }
    // ------------------------------------------------------------ listview ----------

}
