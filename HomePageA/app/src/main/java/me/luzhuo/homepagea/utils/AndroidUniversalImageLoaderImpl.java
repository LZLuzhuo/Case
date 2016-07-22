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
package me.luzhuo.homepagea.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.File;

/**
 * =================================================
 * <p>
 * Author: Luzhuo
 * <p>
 * Version: 1.0
 * <p>
 * Creation Date: 2016/7/22 10:48
 * <p>
 * Description:
 * <p>
 * Revision History:
 * <p>
 * Copyright: Copyright 2016 Luzhuo. All rights reserved.
 * <p>
 * =================================================
 **/
public class AndroidUniversalImageLoaderImpl {
    public void init(Context context) {
        // 缓存路径
        File cacheDir;
        try {
            //  /storage/emulated/0/Android/data/me.luzhuo.eventdispatching/files/Pictures
            cacheDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        }catch (Exception e){ return; }

        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(context)
                // .memoryCacheExtraOptions(480, 800) // max width, max height，即保存的每个缓存文件的最大长宽
                // .discCacheExtraOptions(480, 800, CompressFormat.JPEG, 75, null) // Can slow ImageLoader, use it carefully (Better don't use it)/设置缓存的详细信息，最好不要设置这个
                .threadPoolSize(3)//线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) // You can pass your own memory cache implementation/你可以通过自己的内存缓存实现
                .memoryCacheSize(2 * 1024 * 1024)
                .discCacheSize(50 * 1024 * 1024)
                // 将缓存下来的文件以什么方式命名
                // 1.new Md5FileNameGenerator() //使用MD5对UIL进行加密命名
                // 2.new HashCodeFileNameGenerator()//使用HASHCODE对UIL进行加密命名
                .discCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .discCacheFileCount(100) //缓存的文件数量
                .discCache(new UnlimitedDiskCache(cacheDir))//自定义缓存路径
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                // .imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                //.writeDebugLogs() // Remove for release app
                .build();//开始构建
        // Initialize ImageLoader with configuration.

        ImageLoader.getInstance().init(config);//全局初始化此配置
    }

    private DisplayImageOptions getDisplayImageOptions(int errImage) {
        DisplayImageOptions options;
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(errImage) //设置图片在下载期间显示的图片
                .showImageForEmptyUri(errImage)//设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(errImage)  //设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中
                // .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
                // 设置 图片的缩放方式
                // EXACTLY :图像将完全按比例缩小的目标大小
                // EXACTLY_STRETCHED:图片会缩放到目标大小完全
                // IN_SAMPLE_INT:图像将被二次采样的整数倍
                // IN_SAMPLE_POWER_OF_2:图片将降低2倍，直到下一减少步骤，使图像更小的目标大小
                // NONE:图片不会调整
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型
                // .decodingOptions(android.graphics.BitmapFactory.Options decodingOptions)//设置图片的解码配置
                // .delayBeforeLoading(int delayInMillis)//int delayInMillis为你设置的下载前的延迟时间
                //设置图片加入缓存前，对bitmap进行设置
                // .preProcessor(BitmapProcessor preProcessor)
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                // 图片的显示方式
                // RoundedBitmapDisplayer（introundPixels）设置圆角图片
                // FakeBitmapDisplayer（）这个类什么都没做
                // FadeInBitmapDisplayer（int durationMillis）设置图片渐显的时间
                // SimpleBitmapDisplayer()正常显示一张图片　
                // .displayer(new RoundedBitmapDisplayer(20))//是否设置为圆角，弧度为多少
                // .displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间
                .build();//构建完成
        return options;
    }

    public void displayNet(ImageView imageView, String imageurl) {
        // String imageUri = "http://luzhuo.me/image1.png"; // from Web
        ImageLoader.getInstance().displayImage(imageurl, imageView);
    }

    public void displayNet(ImageView imageView, String imageurl, int errimage) {
        ImageLoader.getInstance().displayImage(imageurl, imageView, getDisplayImageOptions(errimage));
    }

}
