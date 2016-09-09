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
package me.luzhuo.homepagea.widget.banner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.bigkoo.convenientbanner.holder.Holder;

import java.util.ArrayList;

import me.luzhuo.homepagea.R;
import me.luzhuo.homepagea.bean.Entrance;
import me.luzhuo.homepagea.callback.OnEntranceClickListener;
import me.luzhuo.homepagea.ui.adapter.EntranceAdapter;

/**
 * =================================================
 * <p>
 * Author: Luzhuo
 * <p>
 * Version: 1.0
 * <p>
 * Creation Date: 2016/7/20 18:16
 * <p>
 * Description:
 * <p>
 * Revision History:
 * <p>
 * Copyright: Copyright 2016 Luzhuo. All rights reserved.
 * <p>
 * =================================================
 **/
public class EntranceHolderView implements Holder<ArrayList<Entrance.Data>> {
    private View view;
    private OnEntranceClickListener onEntranceClickListener;

    private GridView gridview;
    private EntranceAdapter entranceAdapter;
    private ArrayList<Entrance.Data> datas = new ArrayList<>();

    public EntranceHolderView(OnEntranceClickListener onEntranceClickListener){
        this.onEntranceClickListener = onEntranceClickListener;
    }

    @Override
    public View createView(Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.fragment_entrance, null, false);
        gridview = (GridView) view.findViewById(R.id.gridview);

        initGridView(context);
        return view;
    }

    private void initGridView(Context context) {
        entranceAdapter = new EntranceAdapter(context, datas);
        gridview.setAdapter(entranceAdapter);
        gridview.setOnItemClickListener(listener);
    }

    AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(onEntranceClickListener != null) onEntranceClickListener.onEntranceClick(datas.get(position).title.trim());
        }
    };

    @Override
    public void UpdateUI(Context context, int position, ArrayList<Entrance.Data> data) {
        if(data == null) return;

        datas.clear();
        datas.addAll(data);
        entranceAdapter.notifyDataSetChanged();
    }
}
