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
package me.luzhuo.homepagea.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import me.luzhuo.homepagea.R;
import me.luzhuo.homepagea.bean.ListviewData;

/**
 * =================================================
 * <p/>
 * Author: 卢卓
 * <p/>
 * Version: 1.0
 * <p/>
 * Creation Date: 2016/3/25 15:04
 * <p/>
 * Description:首页适配器
 * <p/>
 * Revision History:
 * <p/>
 * Copyright:
 * <p/>
 * =================================================
 **/
public class HomeAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<String> listviewdatas;
    private LayoutInflater inflater;

    public HomeAdapter(Context context, ArrayList<String> listviewdatas) {
        this.listviewdatas = listviewdatas;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listviewdatas == null ? 0 : listviewdatas.size();
    }

    @Override
    public Object getItem(int position) {
        return listviewdatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_home_text,null);
            viewHolder = new ViewHolder();

            viewHolder.tv_home_text = (TextView)convertView.findViewById(R.id.tv_home_text);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String data = listviewdatas.get(position);

        viewHolder.tv_home_text.setText(data == null ? "" : data);

        return  convertView;
    }

    class ViewHolder{
        TextView tv_home_text;
    }

}
