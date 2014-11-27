package bf.fc.page.curl.provider.handler;

/**
 * Created by rjhy on 14-11-25.
 */
public interface PageProviderHandler {

    public Object onLoading(int index, boolean isBack);

    public Object onError(int index, boolean isBack);

    public Object fetchData(int index, boolean isBack, Object data) throws Exception;

    public void loadData(int index, boolean isBack, Object data);

    public Object getItem(int index, boolean isBack, Object data);

}
