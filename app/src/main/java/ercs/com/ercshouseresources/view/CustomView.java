package ercs.com.ercshouseresources.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.clockin.ClockinActivity;
import ercs.com.ercshouseresources.util.DisplayUtil;
import ercs.com.ercshouseresources.util.ToastUtil;

/**
 * Created by Administrator on 2017/6/23.
 * 自定义的滑块
 */

public class CustomView extends View {
    private static Paint paint = new Paint();// 画笔
    private Bitmap bitmap, bitmapfalse;
    private int p_x = 400;//400中心点  //左边的中心点45 //右边中心点755
    private int width;//移动图片的宽
    private int height;//移动图片的高
    private int left_point = 0;//外圈左部坐标
    private int right_point = 0;//外圈右部坐标
    private int top_point = 0;//外圈顶部坐标
    private int bottom_point = 0;//外圈底部坐标
    private int space = 0;//外圈与里圈的间距
    private Context context;
    private boolean b = false;//是否可以打卡
    private int centerpoint = 0;


    public CustomView(Context context, int left_point, int right_point, int top_point, int bottom_point, int px) {
        super(context);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.left_point = left_point;
        this.right_point = right_point;
        this.top_point = top_point;
        this.bottom_point = bottom_point;
        bitmap = BitmapFactory.decodeResource(getResources(),
                R.mipmap.huadongniu);
        bitmapfalse = BitmapFactory.decodeResource(getResources(),
                R.mipmap.huadongniufalse);
        width = bitmap.getWidth();
        height = bitmap.getHeight();
        p_x = DisplayUtil.getWidthPixels(context) / 2 - width / 2;
        centerpoint = DisplayUtil.getWidthPixels(context) / 2;
        space = (px - height) / 2;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (((ClockinActivity) context).bln_UpLoad)//如果上传图片成功
            canvas.drawBitmap(bitmap, p_x, top_point + space, paint);
        else
            canvas.drawBitmap(bitmapfalse, p_x, top_point + space, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (((ClockinActivity) context).isWifiLiveMac() || ((ClockinActivity) context).isDistanceLive()) {
                    if (((ClockinActivity) context).bln_UpLoad)//如果上传打开图片了
                        b = true;
                    else {
                        b = false;
                        ToastUtil.showToast(context, "请上传您的打卡照片~");
                    }
                } else {
                    b = false;
                    ToastUtil.showToast(context, "不在考勤打卡范围内,请从新进入~");
                }

                break;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                if (b) {
                    if (x > centerpoint - width && x < centerpoint + width) {
                        p_x = x - width / 2;
                    } else if (x < centerpoint - width) {
                        p_x = left_point + space;
                        if (((ClockinActivity) context).StartContent.equals("0")) {
                            ((ClockinActivity) context).pushClocked();
                        } else if (((ClockinActivity) context).StartContent.equals("")) {
                            ToastUtil.showToast(context, "打卡异常~");
                        } else {
                            ToastUtil.showToast(context, "您已经打过上班卡了~");
                        }
                    } else {
                        if (((ClockinActivity) context).StartContent.equals("0")) {
                            ToastUtil.showToast(context, "您还没有打过上班卡呢~");
                        }
                        else
                        {
                            p_x = right_point - width - space;
                            ((ClockinActivity) context).pushClocked2();
                        }
                    }

                    invalidate();// 刷新
                }


                break;
        }
        return true;// 处理了触摸消息，消息不再传递
    }
}
