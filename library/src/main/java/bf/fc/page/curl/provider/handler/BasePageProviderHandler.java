package bf.fc.page.curl.provider.handler;

import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
import java.util.HashMap;

import bf.fc.page.curl.view.CurlView;

/**
 * Created by rjhy on 14-11-25.
 */
public abstract class BasePageProviderHandler implements PageProviderHandler {
    protected ProviderData providerData = new ProviderData();
    protected CurlView curlView;

    public BasePageProviderHandler(CurlView curlView) {
        this.curlView = curlView;
    }

    @Override
    public void loadData(final int index, final boolean isBack, final Object oldData) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Object data = null;
                boolean isError = false;
                try {
                    data = fetchData(index, isBack, oldData);
                } catch (Exception e) {
                    e.printStackTrace();
                    data = onError(index, isBack);
                    isError = true;
                }

                int currentIndex = curlView.getCurrentIndex();
                if (index >= (currentIndex - 2) && index <= (currentIndex + 1)) {
                    saveData(index, isBack, data, isError);
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            if (!curlView.isCurling) {
                                curlView.requestRenderPage(index);
                            } else {
                                curlView.addRequestRenderPage(index);
                            }
                        }
                    });
                }
            }
        });
        thread.start();
    }

    public void saveData(int index, boolean isBack, Object data, boolean isError) {
        providerData.put(index, isBack, data, isError);
    }

    @Override
    public Object getItem(int index, boolean isBack, Object data) {
        Object value = providerData.get(index, isBack);
        if (value == null) {
            loadData(index, isBack, data);
            return onLoading(index, isBack);
        }
        return value;
    }


    class ProviderData{
        private HashMap<String, DataEntity> tempData = new HashMap<String, DataEntity>();
        private ArrayList<String> keys = new ArrayList<String>();
        private Object lock = new Object();
        public ProviderData() {
        }

        public void put(Integer index, boolean isBack, Object value, boolean isError) {
            synchronized (lock) {
                DataEntity entity = new DataEntity(index, isBack, value, isError);
                String key = index + "" + isBack;
                if (tempData.containsKey(key)) {
                    return ;
                }
                tempData.put(key, entity);
                keys.add(key);
                if (tempData.size() > 4) {
                    tempData.remove(keys.get(0));
                    keys.remove(0);
                }
            }
        }

        public Object get(int index, boolean isBack) {
            Object value = null;
            synchronized (lock) {
                String key = index + "" + isBack;
                if (tempData.containsKey(key) && tempData.get(key).isBack() == isBack) {
                    DataEntity entity = tempData.get(key);
                    value = entity.getValue();
                    if (entity.isError()) {
                        tempData.remove(key);
                        keys.remove(key);
                    }
                }
            }
            return value;
        }
    }
}
