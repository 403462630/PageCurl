package bf.fc.page.curl.adapter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import java.util.ArrayList;

import bf.fc.page.curl.view.CurlRenderer;

/**
 * Created by fc on 14-11-21.
 */
public class ImagePageProvider extends BasePageProvider {
    private boolean isRotation = true;
    private ArrayList<Bitmap> bitmaps;
    private ArrayList<Bitmap> backBitmaps;

    public ImagePageProvider(int margin, int padding, int border, int borderColor, int background, ArrayList<Bitmap> bitmaps, ArrayList<Bitmap> backBitmaps) {
        super(margin, padding, border, borderColor, background);
        this.bitmaps = bitmaps;
        this.backBitmaps = backBitmaps;
    }

    public void add(Bitmap data) {
        bitmaps.add(data);
    }

    public void addBack(Bitmap backData) {
        backBitmaps.add(backData);
    }

    public void setRotation(boolean isRotation) {
        this.isRotation = isRotation;
    }

    public void setBitmaps(ArrayList<Bitmap> bitmaps) {
        this.bitmaps = bitmaps;
    }

    public void setBackBitmaps(ArrayList<Bitmap> backBitmaps) {
        this.backBitmaps = backBitmaps;
    }

    public ImagePageProvider(ArrayList<Bitmap> data) {
        this.bitmaps = data;
    }

    public ImagePageProvider() {

    }

    public boolean isShouldRotation(boolean isBack) {
        return isRotation && isBack && (viewMode == CurlRenderer.SHOW_TWO_PAGES);
    }

    @Override
    public boolean isEnaleDrawed(int index, boolean isBack) {
//        if (isBack) {
//            return backBitmaps != null && backBitmaps.size() > index && backBitmaps.get(index) != null;
//        } else {
//            return bitmaps != null && bitmaps.size() > index && bitmaps.get(index) != null;
//        }
        return super.isEnaleDrawed(index, isBack);
    }

    public Bitmap getItem(int index, boolean isBack) {
        if (isBack) {
            return backBitmaps != null && backBitmaps.size() > index ? backBitmaps.get(index) : null;
        } else {
            return bitmaps != null && bitmaps.size() > index ? bitmaps.get(index) : null;
        }

    }

    public void drawBitmap(Canvas c, Rect r, int index, boolean isBack) {
        Log.i("CURLVIEW", "drawBitmap");
        Bitmap d = getItem(index, isBack);
        if (d == null) {
            return ;
        }

        int imageWidth = r.width() - (border * 2);
        int imageHeight = imageWidth * d.getHeight()/ d.getWidth();
        if (imageHeight > r.height() - (border * 2)) {
            imageHeight = r.height() - (border * 2);
            imageWidth = imageHeight * d.getWidth()/ d.getHeight();
        }

        r.left += (r.width() - imageWidth) / 2;
        r.right = r.left + imageWidth;
        r.top += (r.height() - imageHeight) / 2;
        r.bottom = r.top + imageHeight;

        if (isShouldRotation(isBack)) {
            updateCanvasLRSymmetry(c);
        }

        c.drawBitmap(d, null, r, new Paint());
    }

    @Override
    public int getPageCount() {
        return bitmaps == null ? 0 : bitmaps.size();
    }
}
