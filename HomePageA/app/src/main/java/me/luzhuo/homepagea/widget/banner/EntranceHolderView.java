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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;

import me.luzhuo.homepagea.R;
import me.luzhuo.homepagea.bean.Entrance;
import me.luzhuo.homepagea.callback.OnEntranceClickListener;

/**
 * =================================================
 * <p>
 * Author: 卢卓
 * <p>
 * Version: 1.0
 * <p>
 * Creation Date: 2016/7/20 18:16
 * <p>
 * Description:
 * <p>
 * Revision History:
 * <p>
 * Copyright:
 * <p>
 * =================================================
 **/
public class EntranceHolderView implements Holder<ArrayList<Entrance.Data>>, View.OnClickListener {
    private View view;
    private OnEntranceClickListener onEntranceClickListener;

    private ImageView[] images = new ImageView[8];
    private TextView[] texts = new TextView[8];
    private RelativeLayout[] rels = new RelativeLayout[8];

    public EntranceHolderView(OnEntranceClickListener onEntranceClickListener){
        this.onEntranceClickListener = onEntranceClickListener;
    }

    @Override
    public View createView(Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.fragment_entrance, null, false);
        find(view);
        click();
        return view;
    }

    private void click() {
        rels[0].setOnClickListener(this);
        rels[1].setOnClickListener(this);
        rels[2].setOnClickListener(this);
        rels[3].setOnClickListener(this);
        rels[4].setOnClickListener(this);
        rels[5].setOnClickListener(this);
        rels[6].setOnClickListener(this);
        rels[7].setOnClickListener(this);
    }

    private void find(View view) {
        images[0] = (ImageView) view.findViewById(R.id.iv_entrance_item01);
        images[1] = (ImageView) view.findViewById(R.id.iv_entrance_item02);
        images[2] = (ImageView) view.findViewById(R.id.iv_entrance_item03);
        images[3] = (ImageView) view.findViewById(R.id.iv_entrance_item04);
        images[4] = (ImageView) view.findViewById(R.id.iv_entrance_item05);
        images[5] = (ImageView) view.findViewById(R.id.iv_entrance_item06);
        images[6] = (ImageView) view.findViewById(R.id.iv_entrance_item07);
        images[7] = (ImageView) view.findViewById(R.id.iv_entrance_item08);

        texts[0] = (TextView) view.findViewById(R.id.tv_entrance_item1);
        texts[1] = (TextView) view.findViewById(R.id.tv_entrance_item2);
        texts[2] = (TextView) view.findViewById(R.id.tv_entrance_item3);
        texts[3] = (TextView) view.findViewById(R.id.tv_entrance_item4);
        texts[4] = (TextView) view.findViewById(R.id.tv_entrance_item5);
        texts[5] = (TextView) view.findViewById(R.id.tv_entrance_item6);
        texts[6] = (TextView) view.findViewById(R.id.tv_entrance_item7);
        texts[7] = (TextView) view.findViewById(R.id.tv_entrance_item8);

        rels[0] = (RelativeLayout) view.findViewById(R.id.rl_entrance_click1);
        rels[1] = (RelativeLayout) view.findViewById(R.id.rl_entrance_click2);
        rels[2] = (RelativeLayout) view.findViewById(R.id.rl_entrance_click3);
        rels[3] = (RelativeLayout) view.findViewById(R.id.rl_entrance_click4);
        rels[4] = (RelativeLayout) view.findViewById(R.id.rl_entrance_click5);
        rels[5] = (RelativeLayout) view.findViewById(R.id.rl_entrance_click6);
        rels[6] = (RelativeLayout) view.findViewById(R.id.rl_entrance_click7);
        rels[7] = (RelativeLayout) view.findViewById(R.id.rl_entrance_click8);
    }

    @Override
    public void UpdateUI(Context context, int position, ArrayList<Entrance.Data> data) {
        if(data == null) return;

        for(int x = 0; x < data.size(); x++){
            Entrance.Data Entrance = data.get(x);
            new BitmapUtils(context).display(images[x], Entrance.imageurl);
            texts[x].setText(Entrance.title == null ? "" : Entrance.title);
            rels[x].setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        if(onEntranceClickListener != null) if(rels[0] == v) onEntranceClickListener.onEntranceClick(texts[0].getText().toString().trim());
        if(onEntranceClickListener != null) if(rels[1] == v) onEntranceClickListener.onEntranceClick(texts[1].getText().toString().trim());
        if(onEntranceClickListener != null) if(rels[2] == v) onEntranceClickListener.onEntranceClick(texts[2].getText().toString().trim());
        if(onEntranceClickListener != null) if(rels[3] == v) onEntranceClickListener.onEntranceClick(texts[3].getText().toString().trim());
        if(onEntranceClickListener != null) if(rels[4] == v) onEntranceClickListener.onEntranceClick(texts[4].getText().toString().trim());
        if(onEntranceClickListener != null) if(rels[5] == v) onEntranceClickListener.onEntranceClick(texts[5].getText().toString().trim());
        if(onEntranceClickListener != null) if(rels[6] == v) onEntranceClickListener.onEntranceClick(texts[6].getText().toString().trim());
        if(onEntranceClickListener != null) if(rels[7] == v) onEntranceClickListener.onEntranceClick(texts[7].getText().toString().trim());
    }
}
