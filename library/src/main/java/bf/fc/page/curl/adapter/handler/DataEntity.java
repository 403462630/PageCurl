package bf.fc.page.curl.adapter.handler;

public class DataEntity{
    Integer index;
    Object value;

    public boolean isBack() {
        return isBack;
    }

    public void setBack(boolean isBack) {
        this.isBack = isBack;
    }

    boolean isBack;

    public boolean isError() {
        return isError;
    }

    public void setError(boolean isError) {
        this.isError = isError;
    }

    boolean isError;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public DataEntity(Integer key, boolean isBack, Object value, boolean isError) {
        this.index = key;
        this.isBack = isBack;
        this.value = value;
        this.isError = isError;
    }
}