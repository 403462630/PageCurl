package com.example.curl.example;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import java.util.ArrayList;

import bf.fc.page.curl.provider.ImagePageProvider;
import bf.fc.page.curl.view.CurlRenderer;
import bf.fc.page.curl.view.CurlView;


public class ExampleActivity2 extends ActionBarActivity {
    private CurlView curlView;
    private ImagePageProvider imagePageProvider;
    private int index = 0;
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("index", curlView.getCurrentIndex());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_activity);

        if (savedInstanceState != null && savedInstanceState.containsKey("index")) {
            index = savedInstanceState.getInt("index");
        }
        curlView = (CurlView) findViewById(R.id.curl_view);

        imagePageProvider = new ImagePageProvider();
        curlView.setPageProvider(imagePageProvider);

        ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.icon));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.obama));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.road_rage));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.taipei_101));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.world));

        ArrayList<Bitmap> backBitmaps = new ArrayList<Bitmap>();
        backBitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.obama));
        backBitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.road_rage));
        backBitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.taipei_101));

        imagePageProvider.setBitmaps(bitmaps);
        imagePageProvider.setBackBitmaps(backBitmaps);

        curlView.setSizeChangedObserver(new CurlView.SizeChangedObserver() {
            @Override
            public void onSizeChanged(int width, int height) {
                if (width > height) {
                    curlView.setViewMode(CurlRenderer.SHOW_TWO_PAGES);
                    //percentage margin
                    curlView.setMargins(0.1f, 0f, 0.1f, 0f);
                } else {
                    curlView.setViewMode(CurlRenderer.SHOW_ONE_PAGE);
                }
            }
        });
        curlView.setCurrentIndex(index);
    }

}
