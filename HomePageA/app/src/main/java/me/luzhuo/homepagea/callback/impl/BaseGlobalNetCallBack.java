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
package me.luzhuo.homepagea.callback.impl;

import java.util.ArrayList;

import me.luzhuo.homepagea.bean.Entrance;
import me.luzhuo.homepagea.callback.GlobalNetCallBack;

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
public class BaseGlobalNetCallBack implements GlobalNetCallBack{

    @Override
    public void getListviewData(boolean isSuccess, boolean isHaveMoreData, ArrayList<String> data) {

    }

    @Override
    public void getBannerData(boolean isSuccess, ArrayList<String> data) {

    }

    @Override
    public void getEntrance(boolean isSuccess, ArrayList<Entrance.Data> data) {

    }
}
