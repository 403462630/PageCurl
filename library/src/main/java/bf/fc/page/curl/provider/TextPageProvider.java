package bf.fc.page.curl.provider;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;

import java.util.ArrayList;

import bf.fc.page.curl.view.CurlPage;
import bf.fc.page.curl.view.CurlRenderer;

/**
 * Created by fc on 14-11-21.
 */
public class TextPageProvider extends BasePageProvider {

    private boolean isRotation = true;
    private ArrayList<String> strings;
    private ArrayList<String> backStrings;

    public TextPageProvider(int margin, int padding, int border, int borderColor, int background, ArrayList<String> strings, ArrayList<String> backStrings) {
        super(margin, padding, border, borderColor, background);
        this.strings = strings;
        this.backStrings = backStrings;
    }

    public void add(String data) {
        strings.add(data);
    }

    public void addBack(String backData) {
        backStrings.add(backData);
    }

    public void setRotation(boolean isRotation) {
        this.isRotation = isRotation;
    }

    public String getItem(final int index, boolean isBack) {
        if (isBack) {
            return (backStrings != null) && (backStrings.size() > index) ? backStrings.get(index) : null;
        } else {
            return (strings != null) && (strings.size() > index) ? strings.get(index) : null;
        }
    }

    @Override
    public boolean isEnaleDrawed(int index, boolean isBack) {
//        if (isBack) {
//            return (backStrings != null) && (backStrings.size() > index) && (backStrings.get(index) != null);
//        } else {
//            return (strings != null) && (strings.size() > index) && (strings.get(index) != null);
//        }
        return super.isEnaleDrawed(index, isBack);
    }

    public void setStrings(ArrayList<String> strings) {
        this.strings = strings;
    }

    public void setBackStrings(ArrayList<String> backStrings) {
        this.backStrings = backStrings;
    }

    public TextPageProvider(ArrayList<String> data) {
        this.strings = data;
    }
    public TextPageProvider() {

    }

    public boolean isShouldRotation(boolean isBack) {
        return isRotation && isBack && (viewMode == CurlRenderer.SHOW_TWO_PAGES);
    }

    public void drawBitmap(final Canvas c, Rect r, final int index, boolean isBack) {
        String data = getItem(index, isBack);
        if (data == null) {
            return ;
        }
        TextPaint textPaint = new TextPaint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(28f);
        if (isShouldRotation(isBack)) {
            updateCanvasLRSymmetry(c);
        }
        StaticLayout staticLayout = new StaticLayout(data, 0, data.length(), textPaint, r.width(), Layout.Alignment.ALIGN_NORMAL, 1.3f, 0.0f, false);
        c.translate(r.left, r.top);
        staticLayout.draw(c);
    }

    @Override
    public void updatePage(CurlPage page, int width, int height, int index) {
        super.updatePage(page, width, height, index);
    }

    @Override
    public int getPageCount() {
        return strings == null ? 0 : strings.size();
    }
}
