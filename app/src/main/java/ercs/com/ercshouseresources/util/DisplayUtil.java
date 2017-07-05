package ercs.com.ercshouseresources.util;

/**
 * Created by Administrator on 2017/6/29.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;

/**
 * Created by Administrator on 2017/5/3.
 * 计算屏幕像素的工具类
 */

public class DisplayUtil {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    /**
     * 根据手机的真实象素将应用转换成对应比例的象素
     */
    public static int realdip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 宽度，象素
     */
    public static int getWidthPixels(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 高度，象素
     */
    public static int getHeightPixels(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * dp系数
     */
    public static float getDensty(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static int getStatusBarHeight(Activity mActivity) {
        Rect rect = new Rect();
        mActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.top;
    }


}
