/* Copyright 2018 Luzhuo. All rights reserved.
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
package me.luzhuo.share;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SharePopWindow extends PopupWindow implements View.OnClickListener {
    private View view;
    private RecyclerView rvShare;
    private Context context;
    private int[] icons = {R.mipmap.share_wechat, R.mipmap.share_wxcircle, R.mipmap.share_qq, R.mipmap.share_sina, R.mipmap.share_qzone, R.mipmap.share_kindle, R.mipmap.share_more};
    private String[] titles = {"微信", "朋友圈", "QQ", "微博", "QQ空间", "Kindle", "更多"};

    public SharePopWindow(Context context) {
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.pop_share, null);

        view.findViewById(R.id.bt_close).setOnClickListener(this);
        view.findViewById(R.id.bt_close2).setOnClickListener(this);
        rvShare = view.findViewById(R.id.rv_share_grid);

        this.setContentView(view);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        this.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.setAnimationStyle(R.style.AnimSharePop); // 淡入淡出动画

        // init recyclerview
        initRecyclerView();
    }

    private void initRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(context, 4);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rvShare.setLayoutManager(layoutManager);
        ShareAdapter adapter = new ShareAdapter(context, icons, titles);
        rvShare.setAdapter(adapter);
    }

    /**
     * 展示pop
     *
     * @param view
     */
    public void show(View view) {
        if (!this.isShowing()) {
            this.showAsDropDown(view, 0, 0);
        } else {
            this.dismiss();
        }
    }

    @Override
    public void onClick(View view) {
        this.dismiss();
    }

    public class ShareAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private Context context;
        private int[] icons;
        private String[] titles;

        public ShareAdapter(Context context, int[] icons, String[] titles) {
            this.context = context;
            this.icons = icons;
            this.titles = titles;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_share_icon, parent, false);
            return new RecyclerHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((RecyclerHolder) holder).setData(position);
        }

        @Override
        public int getItemCount() {
            return titles == null ? 0 : titles.length;
        }

        public class RecyclerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            public ImageView icon;
            public TextView title;
            public RelativeLayout btIcon;

            public RecyclerHolder(View itemView) {
                super(itemView);
                icon = itemView.findViewById(R.id.iv_icon);
                title = itemView.findViewById(R.id.tv_title);
                btIcon = itemView.findViewById(R.id.bt_icon);

                this.btIcon.setOnClickListener(this);
            }

            public void setData(int position) {
                icon.setImageResource(icons[position]);
                title.setText(titles[position]);
            }

            @Override
            public void onClick(View v) {
                Toast.makeText(context, titles[getLayoutPosition()], Toast.LENGTH_SHORT).show();
            }
        }
    }
}
