PageCurl
========

#Usage
<hr/>
#### first step

    <bf.fc.page.curl.view.CurlView
            android:id="@+id/curl_view"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />

#### second step

    TextPageProvider textPageProvider = new TextPageProvider();
    textPageProvider.setStrings(contents);
    textPageProvider.setBackStrings(backContents);
    curlView.setPageProvider(textPageProvider);

######  Or

    ImagePageProvider imagePageProvider = new ImagePageProvider();
    imagePageProvider.setBitmaps(bitmaps);
    imagePageProvider.setBackBitmaps(backBitmaps);
    curlView.setPageProvider(imagePageProvider);

#### this library is also Asynchronous loading data; use following:

    public class PageAdapterHandler extends BasePageProviderHandler {
        public PageAdapterHandler(CurlView curlView) {
            super(curlView);
        }

        @Override
        public Object onLoading(int index, boolean isBack) {
            return "loading。。。。。。";
        }

        @Override
        public Object onError(int index, boolean isBack) {
            return "error";
        }

        /**
         * the method execute in background thread
         */
        @Override
        public Object fetchData(int index, boolean isBack, Object data) throws Exception {
            // do something
        }
    }
detail please look code



