package ercs.com.ercshouseresources.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.king.base.adapter.ViewHolderAdapter;
import com.king.base.adapter.holder.ViewHolder;

import java.io.File;
import java.io.IOException;
import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.field.FieldClockInActivity;
import ercs.com.ercshouseresources.util.OtherUitl;

import static ercs.com.ercshouseresources.util.OtherUitl.getBitmapDegree;
import static ercs.com.ercshouseresources.util.OtherUitl.rotateBitmapByDegree;

/**
 * 报备订单详情图片gridview
 * Created by Administrator on 2017/7/28.
 */

public class OrderReportPhotoGridAdapter extends ViewHolderAdapter<String>{
    private OnCancelPhotoListener onCancelPhotoListener;

    private boolean isCance=false;//是否显示删除图标

    public boolean isCance() {
        return isCance;
    }

    public void setCance(boolean cance) {
        isCance = cance;
    }

    public OrderReportPhotoGridAdapter(Context context, List<String> listData, OnCancelPhotoListener onCancelPhotoListener) {
        super(context, listData);
        this.onCancelPhotoListener = onCancelPhotoListener;
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, String s, int position) {
        return layoutInflater.inflate(R.layout.item_photo_gridview,null);
    }

    @Override
    public void bindViewDatas(ViewHolder holder, String s, final int position) {
        ImageView iv_photo = holder.getView(R.id.iv_photo);
        Bitmap photoBmp = null;
        try {
            photoBmp = OtherUitl.getBitmapFormUri((Activity) context, Uri.fromFile(new File(s)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int degree = getBitmapDegree(s);
        Bitmap newbitmap = rotateBitmapByDegree(photoBmp, degree);
        iv_photo.setImageBitmap(newbitmap);

       ImageView iv_cancel= holder.getView(R.id.iv_cancel);//删除
        if (isCance)
        {
            iv_cancel.setVisibility(View.VISIBLE);
        }else
        {
            iv_cancel.setVisibility(View.GONE);
        }
        iv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCancelPhotoListener.cancelPhoto(position);
            }
        });
    }


    public interface OnCancelPhotoListener
    {
        void cancelPhoto(int i);//删除图片
    }
}
