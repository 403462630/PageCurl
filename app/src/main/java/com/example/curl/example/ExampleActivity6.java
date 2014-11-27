package com.example.curl.example;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.example.curl.example.handler.PageProviderHandler6;
import com.example.curl.example.provider.PageProvider6;

import java.util.ArrayList;

import bf.fc.page.curl.view.CurlRenderer;
import bf.fc.page.curl.view.CurlView;


public class ExampleActivity6 extends ActionBarActivity {
    private CurlView curlView;
    private PageProvider6 adapter6;
    private PageProviderHandler6 handler6;
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

        ArrayList<String> contents = new ArrayList<String>();
        contents.add("第一页的内容。。。。。。。");
        contents.add("第二页的内容。。。。。。。");
        contents.add("第三页的内容。。。。。。。");
        contents.add("第四页的内容。。。。。。。");
        contents.add("第五页的内容。。。。。。。");
        contents.add("第六页的内容。。。。。。。");
        contents.add("第七页的内容。。。。。。。");
        contents.add("第八页的内容。。。。。。。");

        ArrayList<String> backContents = new ArrayList<String>();
        backContents.add("第一页背面的内容。。。。。。。");
        backContents.add("第二页背面的内容。。。。。。。");
        backContents.add("第三页背面的内容。。。。。。。");
        backContents.add("第四页背面的内容。。。。。。。");

        if (savedInstanceState != null && savedInstanceState.containsKey("index")) {
            index = savedInstanceState.getInt("index");
        }
        curlView = (CurlView) findViewById(R.id.curl_view);

        adapter6 = new PageProvider6();
        handler6 = new PageProviderHandler6(curlView);
        adapter6.setHandler(handler6);
        adapter6.setStrings(contents);
        adapter6.setBackStrings(backContents);

        curlView.setPageProvider(adapter6);
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
