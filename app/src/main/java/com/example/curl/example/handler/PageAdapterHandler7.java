package com.example.curl.example.handler;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;

import java.net.HttpURLConnection;
import java.net.URL;

import bf.fc.page.curl.adapter.handler.BasePageProviderHandler;
import bf.fc.page.curl.view.CurlView;

/**
 * Created by rjhy on 14-11-26.
 */
public class PageAdapterHandler7 extends BasePageProviderHandler {
    private Context context;
    private Bitmap loadBitmap;
    private Bitmap errorBitmap;
    public PageAdapterHandler7(Context context, CurlView curlView) {
        super(curlView);
        this.context = context;
        loadBitmap = canvasBitmap("图片正在加载中");
        errorBitmap = canvasBitmap("图片加载出错");
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

    @Override
    public Object fetchData(int index, boolean isBack, Object data) throws Exception {
        HttpURLConnection conn = null;
        try {
            URL url = new URL((String)data);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(1000*10);
            Bitmap bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            return bitmap;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
}
