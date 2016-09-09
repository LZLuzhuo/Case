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
import me.luzhuo.homepagea.bean.Entrance;
import me.luzhuo.homepagea.utils.AndroidUniversalImageLoaderImpl;

/**
 * =================================================
 * <p>
 * Author: Luzhuo
 * <p>
 * Version: 1.0
 * <p>
 * Creation Date: 2016/9/9 11:27
 * <p>
 * Description:
 * <p>
 * Revision History:
 * <p>
 * Copyright: Copyright 2016 Luzhuo. All rights reserved.
 * <p>
 * =================================================
 **/
public class EntranceAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Entrance.Data> datas;
    private LayoutInflater inflater;

    public EntranceAdapter(Context context, ArrayList<Entrance.Data> datas) {
        this.context = context;
        this.datas = datas;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        // 限定最多8个条目
        return datas == null ? 0 : datas.size() > 8 ? 8 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_entrance,null);
            viewHolder = new ViewHolder();

            viewHolder.imageview = (ImageView)convertView.findViewById(R.id.imageview);
            viewHolder.textview = (TextView)convertView.findViewById(R.id.textview);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Entrance.Data entrance = datas.get(position);

        new AndroidUniversalImageLoaderImpl().displayNet(viewHolder.imageview, entrance.imageurl, R.mipmap.touming);
        viewHolder.textview.setText(entrance.title);

        return  convertView;
    }

    class ViewHolder{
        ImageView imageview;
        TextView textview;
    }

}
