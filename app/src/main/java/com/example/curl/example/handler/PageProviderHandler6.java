package com.example.curl.example.handler;

import bf.fc.page.curl.provider.handler.BasePageProviderHandler;
import bf.fc.page.curl.view.CurlView;

/**
 * Created by rjhy on 14-11-26.
 */
public class PageProviderHandler6 extends BasePageProviderHandler {
    public PageProviderHandler6(CurlView curlView) {
        super(curlView);
    }

    @Override
    public Object onLoading(int index, boolean isBack) {
        return "数据正在加载中，请稍等。。。。。。";
    }

    @Override
    public Object onError(int index, boolean isBack) {
        return "数据加载出错";
    }

    @Override
    public Object fetchData(int index, boolean isBack, Object data) throws Exception {
        Thread.sleep(2000);
        // not return null
        return data != null ? data : "";
    }
}
