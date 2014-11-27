package com.example.curl.example.provider;

import bf.fc.page.curl.provider.TextPageProvider;

/**
 * Created by rjhy on 14-11-26.
 */
public class PageProvider5 extends TextPageProvider {

    @Override
    public String getItem(int index, boolean isBack) {
        if (index%2 == 0) {
            return "第" + index + "的内容";
        } else {
            return null;
        }
    }

    @Override
    public int getPageCount() {
        return Integer.MAX_VALUE;
    }
}
