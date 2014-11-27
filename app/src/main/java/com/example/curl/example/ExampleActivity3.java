package com.example.curl.example;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.example.curl.example.provider.PageProvider3;

import java.util.ArrayList;

import bf.fc.page.curl.view.CurlRenderer;
import bf.fc.page.curl.view.CurlView;


public class ExampleActivity3 extends ActionBarActivity {
    private CurlView curlView;
    private PageProvider3 pageAdapter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_activity);
        curlView = (CurlView) findViewById(R.id.curl_view);

        pageAdapter3 = new PageProvider3(this);
        curlView.setPageProvider(pageAdapter3);

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

        pageAdapter3.setStrings(contents);
        pageAdapter3.setBackStrings(backContents);

        curlView.setSizeChangedObserver(new CurlView.SizeChangedObserver() {
            @Override
            public void onSizeChanged(int width, int height) {
                if (width > height) {
                    curlView.setViewMode(CurlRenderer.SHOW_TWO_PAGES);
                    curlView.setMargins(0.1f, 0f, 0.1f, 0f);
                } else {
                    curlView.setViewMode(CurlRenderer.SHOW_ONE_PAGE);
                }
            }
        });
    }



}
