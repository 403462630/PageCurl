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

#### you are also define PageProvider

    public class TestPageProvider extends BasePageProvider {

        @Override
        public Object getItem(int index, boolean isBack) {
            return null;
        }

        @Override
        public void drawBitmap(Canvas c, Rect r, int index, boolean isBack) {

        }

        @Override
        public int getPageCount() {
            return 0;
        }
    }

#### you are also  Asynchronous loading data

    public class PageProviderHandler extends BasePageProviderHandler {
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

##### detail please look code



