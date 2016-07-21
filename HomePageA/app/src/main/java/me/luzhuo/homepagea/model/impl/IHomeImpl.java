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
package me.luzhuo.homepagea.model.impl;

import java.util.ArrayList;

import me.luzhuo.homepagea.bean.Banner;
import me.luzhuo.homepagea.bean.Entrance;
import me.luzhuo.homepagea.bean.ListviewData;
import me.luzhuo.homepagea.callback.GlobalNetCallBack;
import me.luzhuo.homepagea.model.IHome;

/**
 * =================================================
 * <p>
 * Author: 卢卓
 * <p>
 * Version: 1.0
 * <p>
 * Creation Date: 2016/7/20 16:02
 * <p>
 * Description:
 * <p>
 * Revision History:
 * <p>
 * Copyright:
 * <p>
 * =================================================
 **/
public class IHomeImpl implements IHome{
    @Override
    public void getListviewData(int pagenumber, final GlobalNetCallBack callback) {
        ArrayList<String> data = new ArrayList<>();
        for (int x = 0; x < IHome.pageSize; x++){
            data.add("ListviewData:".concat(String.valueOf(x)));
        }
        ListviewData listviewData = new ListviewData();
        listviewData.stringDatas = data;

        if(callback != null) callback.getListviewData(true, listviewData.stringDatas.size() == IHome.pageSize ? true : false, listviewData.stringDatas);
    }

    @Override
    public void getBannerData(final GlobalNetCallBack callback) {
        ArrayList<String> data = new ArrayList<>();
        for (int x = 0; x < 5; x++){
            data.add("http://dl.bizhi.sogou.com/images/2012/11/27/319296.jpg?f=download");
        }
        Banner banner = new Banner();
        banner.imagurls = data;

        if(callback != null) callback.getBannerData(true, banner.imagurls);
    }

    @Override
    public void getEntrance(GlobalNetCallBack callback) {
        ArrayList<Entrance.Data> data = new ArrayList<>();
        Entrance.Data entranceData;
        for (int x = 0; x < 20; x++){
            entranceData = new Entrance.Data();
            entranceData.title = "Title:".concat(String.valueOf(x));
            entranceData.imageurl = "http://dl.bizhi.sogou.com/images/2012/11/27/319296.jpg?f=download";
            data.add(entranceData);
        }
        Entrance entrance = new Entrance();
        entrance.datas = data;

        if(callback != null) callback.getEntrance(true, entrance.datas);
    }
}
