package com.example.curl.example;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.example.curl.example.provider.PageProvider5;

import bf.fc.page.curl.view.CurlRenderer;
import bf.fc.page.curl.view.CurlView;


public class ExampleActivity5 extends ActionBarActivity {
    private CurlView curlView;
    private PageProvider5 adapter5;
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

        adapter5 = new PageProvider5();
        curlView.setPageProvider(adapter5);

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
