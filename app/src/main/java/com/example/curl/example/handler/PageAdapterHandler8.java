package com.example.curl.example.handler;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.LruCache;

import java.net.HttpURLConnection;
import java.net.URL;

import bf.fc.page.curl.adapter.handler.BasePageProviderHandler;
import bf.fc.page.curl.adapter.handler.DataEntity;
import bf.fc.page.curl.view.CurlView;

/**
 * Created by rjhy on 14-11-26.
 */
public class PageAdapterHandler8 extends BasePageProviderHandler {
    private Context context;
    private Bitmap loadBitmap;
    private Bitmap errorBitmap;
    private LruCache<Object, DataEntity> cache;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    public PageAdapterHandler8(Context context, CurlView curlView) {
        super(curlView);
        this.context = context;
        loadBitmap = canvasBitmap("图片正在加载中");
        errorBitmap = canvasBitmap("图片加载出错");

        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 8;
        // 设置图片缓存大小为程序最大可用内存的1/8
        cache = new LruCache<Object, DataEntity>(cacheSize) {
            @Override
            protected int sizeOf(Object key, DataEntity entity) {
                return ((Bitmap)entity.getValue()).getByteCount();
            }
        };
    }

    private Bitmap canvasBitmap(String str) {
        Bitmap bitmap = Bitmap.createBitmap(256,40, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bitmap);
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(30f);
        textPaint.setColor(Color.GREEN);
        c.translate(0, 0);
        StaticLayout staticLayout = new StaticLayout(str, textPaint, 256, Layout.Alignment.ALIGN_CENTER, 1.3f, 0, false);
        staticLayout.draw(c);
        return bitmap;
    }

    @Override
    public Object onLoading(int index, boolean isBack) {
        return loadBitmap;
    }

    @Override
    public Object onError(int index, boolean isBack) {
        return errorBitmap;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    @Override
    public Object fetchData(int index, boolean isBack, Object data) throws Exception {
        HttpURLConnection conn = null;
        try {
            URL url = new URL((String)data);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(1000*10);
            Bitmap bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            DataEntity dataEntity = new DataEntity(index, isBack, bitmap, false);
            cache.put(data, dataEntity);
            return bitmap;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    @Override
    public Object getItem(int index, boolean isBack, Object data) {
        DataEntity entity = cache.get(data);
        if (entity != null) {
//            saveData(index, isBack, entity.getValue(), true);
            return entity.getValue();
        }
        return super.getItem(index, isBack, data);
    }
}
