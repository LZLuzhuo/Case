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
package me.luzhuo.homepagea.callback;

import java.util.ArrayList;

import me.luzhuo.homepagea.bean.Entrance;

/**
 * =================================================
 * <p>
 * Author: 卢卓
 * <p>
 * Version: 1.0
 * <p>
 * Creation Date: 2016/7/20 16:54
 * <p>
 * Description:
 * <p>
 * Revision History:
 * <p>
 * Copyright:
 * <p>
 * =================================================
 **/
public interface GlobalNetCallBack {

    /**
     * 获取listview数据
     * @param isSuccess 时候成功
     * @param isHaveMoreData 时候还有数据, 没有数据了就不再访问
     * @param data
     */
    void getListviewData(boolean isSuccess, boolean isHaveMoreData, ArrayList<String> data);

    /**
     * 获取banner数据
     * @param isSuccess
     * @param data
     */
    void getBannerData(boolean isSuccess, ArrayList<String> data);

    /**
     * 获取动态入口数据
     * @param isSuccess
     * @param data
     */
    void getEntrance(boolean isSuccess, ArrayList<Entrance.Data> data);
}
