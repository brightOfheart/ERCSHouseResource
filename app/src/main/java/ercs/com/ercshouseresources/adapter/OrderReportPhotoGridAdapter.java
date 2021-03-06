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
import java.io.PipedReader;
import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.PhotoViewActivity;
import ercs.com.ercshouseresources.activity.field.FieldClockInActivity;
import ercs.com.ercshouseresources.bean.NewHousePicBean;
import ercs.com.ercshouseresources.bean.ReportOrderDetailBean;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.OtherUitl;
import ercs.com.ercshouseresources.util.imageUtil.GlideUtil;

import static ercs.com.ercshouseresources.util.OtherUitl.getBitmapDegree;
import static ercs.com.ercshouseresources.util.OtherUitl.rotateBitmapByDegree;

/**
 * 报备订单详情图片gridview
 * Created by Administrator on 2017/7/28.
 */

public class OrderReportPhotoGridAdapter extends ViewHolderAdapter<ReportOrderDetailBean.DataBean.NewHouseRunningsInfoShowListBean.ImageListBean>{
    private OnCancelPhotoListener onCancelPhotoListener;

    private boolean isCance=false;//是否显示删除图标

    private Activity activity;



    public void setCance(boolean cance) {
        isCance = cance;
    }

    public OrderReportPhotoGridAdapter(Activity context, List<ReportOrderDetailBean.DataBean.NewHouseRunningsInfoShowListBean.ImageListBean> listData, OnCancelPhotoListener onCancelPhotoListener) {
        super(context, listData);
        activity=context;
        this.onCancelPhotoListener = onCancelPhotoListener;
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, ReportOrderDetailBean.DataBean.NewHouseRunningsInfoShowListBean.ImageListBean picBean, int position) {
        return layoutInflater.inflate(R.layout.item_photo_gridview,null);
    }

    @Override
    public void bindViewDatas(ViewHolder holder, final ReportOrderDetailBean.DataBean.NewHouseRunningsInfoShowListBean.ImageListBean picBean, final int position) {
        ImageView iv_photo = holder.getView(R.id.iv_photo);

            //网络图片
            GlideUtil.loadImage(context, NetHelperNew.URL+picBean.getImagePath()+picBean.getFileName(), iv_photo,R.mipmap.ic_launcher,R.mipmap.ic_launcher);

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
                onCancelPhotoListener.cancelPhoto(picBean.getId(),position);
            }
        });
        iv_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhotoViewActivity.start(activity,NetHelperNew.URL+picBean.getImagePath()+picBean.getFileName());
            }
        });
    }


    public interface OnCancelPhotoListener
    {
        void cancelPhoto(int id,int pos);//删除图片
    }
}
