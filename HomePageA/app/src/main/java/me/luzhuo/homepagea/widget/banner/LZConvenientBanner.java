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
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.bigkoo.convenientbanner.ConvenientBanner;

/**
 * =================================================
 * <p>
 * Author: 卢卓
 * <p>
 * Version: 1.0
 * <p>
 * Creation Date: 2016/7/19 16:02
 * <p>
 * Description: 对 ConvenientBanner 进行了事件的优化
 * <p>
 * Revision History:
 * <p>
 * Copyright:
 * <p>
 * =================================================
 **/
public class LZConvenientBanner extends ConvenientBanner {
    public LZConvenientBanner(Context context) {
        super(context);
    }

    public LZConvenientBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LZConvenientBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LZConvenientBanner(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private int abc = 1;
    private float mLastMotionX;
    private float mLastMotionY;

    public boolean dispatchTouchEvent(MotionEvent ev) {
        final float x = ev.getX();
        final float y = ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                abc = 1;
                mLastMotionX = x;
                mLastMotionY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                if (abc == 1) {
                    if (Math.abs(x - mLastMotionX) < Math.abs(y - mLastMotionY)) {
                        abc = 0;
                        getParent().requestDisallowInterceptTouchEvent(false);
                    } else {
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                // 当从本空间移到外部控件时,本控件不再处理,交由父类处理
            case MotionEvent.ACTION_CANCEL: //从本控件移到外部控件时触发
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
