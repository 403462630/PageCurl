package com.example.curl.example.provider;

import bf.fc.page.curl.adapter.TextPageProvider;
import bf.fc.page.curl.adapter.handler.BasePageProviderHandler;

/**
 * Created by rjhy on 14-11-26.
 */
public class PageProvider6 extends TextPageProvider {

    public void setHandler(BasePageProviderHandler handler) {
        this.handler = handler;
    }

    private BasePageProviderHandler handler;


    @Override
    public int getPageCount() {
        return super.getPageCount();
    }

    @Override
    public String getItem(int index, boolean isBack) {
        if (isBack) {
            return (String)handler.getItem(index, isBack, super.getItem(index, isBack));
        } else {
            return (String)handler.getItem(index, isBack, super.getItem(index, isBack));
        }
    }
}
