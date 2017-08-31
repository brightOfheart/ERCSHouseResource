package ercs.com.ercshouseresources.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.bean.FinancialOrderDetailBean;
import ercs.com.ercshouseresources.bean.ReportOrderDetailBean;
import ercs.com.ercshouseresources.util.ToastUtil;

/**
 * Created by Administrator on 2017/8/23.
 */

public class FinancialOrderReportDetailAdapter extends BaseAdapter {
    private List<FinancialOrderDetailBean.DataBean.TimelineShowList> list;
    private Activity context;
    private OrderReportDetailAdapter.OnCamreaListener onCamreaListener;


    public FinancialOrderReportDetailAdapter(List<FinancialOrderDetailBean.DataBean.TimelineShowList> list, Activity context, OrderReportDetailAdapter.OnCamreaListener onCamreaListener) {
        this.list = list;
        this.context = context;
        this.onCamreaListener = onCamreaListener;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final FinancialOrderDetailBean.DataBean.TimelineShowList newHouseRunningsInfoShowListBean = list.get(i);
        if (newHouseRunningsInfoShowListBean.getStyleID() == 1) {
            //模版1
            view = LayoutInflater.from(context).inflate(R.layout.item_order_report_detail1, null);

        } else if (newHouseRunningsInfoShowListBean.getStyleID() == 2) {
            //模版2
            view = LayoutInflater.from(context).inflate(R.layout.item_order_report_detail2, null);
            TextView tv_no_reportreason = view.findViewById(R.id.tv_no_reportreason);
            //报备审核说明
            if (newHouseRunningsInfoShowListBean.getFilingAuditingRemark() != null)
                tv_no_reportreason.setText(newHouseRunningsInfoShowListBean.getFilingAuditingRemark());
        } else if (newHouseRunningsInfoShowListBean.getStyleID() == 3) {
            //模版3
            view = LayoutInflater.from(context).inflate(R.layout.item_order_report_detail3, null);

            //照相图片
            GridView gridview = view.findViewById(R.id.gridview);
            FinancialOrderReportPhotoGridAdapter orderReportPhotoGridAdapter = new FinancialOrderReportPhotoGridAdapter(context, newHouseRunningsInfoShowListBean.getImageList(), new OrderReportPhotoGridAdapter.OnCancelPhotoListener() {
                @Override
                public void cancelPhoto(int id, int childpos) {
                    onCamreaListener.delPic(i, id, childpos);
                }
            });
            gridview.setAdapter(orderReportPhotoGridAdapter);


            //照相图标
            ImageView iv_camera = view.findViewById(R.id.iv_camera);
            if (newHouseRunningsInfoShowListBean.getIsCameraButton() == 1) {
                iv_camera.setVisibility(View.VISIBLE);
                orderReportPhotoGridAdapter.setCance(true);
            } else {
                iv_camera.setVisibility(View.GONE);
                orderReportPhotoGridAdapter.setCance(false);
            }
            //照相提示语
            TextView tv_imageremark = view.findViewById(R.id.tv_imageremark);
            if (newHouseRunningsInfoShowListBean.getImageRemark() != null)
                tv_imageremark.setText(newHouseRunningsInfoShowListBean.getImageRemark());
            iv_camera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!(newHouseRunningsInfoShowListBean.getImageList().size() > 6)) {
                        onCamreaListener.getImageType(i, newHouseRunningsInfoShowListBean.getImageType(),newHouseRunningsInfoShowListBean.getGroupID());
                    } else {
                        ToastUtil.showToast(context, "最多传六张图片");
                    }

                }
            });


        } else if (newHouseRunningsInfoShowListBean.getStyleID() == 4) {
            //模版4
            view = LayoutInflater.from(context).inflate(R.layout.item_order_report_detail4, null);
        }


        ImageView iv_state = view.findViewById(R.id.iv_state);
        switch (newHouseRunningsInfoShowListBean.getModuleIcon()) {
            case 1:
                //进行中
                iv_state.setImageResource(R.mipmap.dengdai);
                break;
            case 2:
                //已完成
                iv_state.setImageResource(R.mipmap.tongguo);
                break;
            case 3:
                //未到
                iv_state.setImageResource(R.mipmap.weidao);
                break;
            case 4:
                //拒绝
                iv_state.setImageResource(R.mipmap.jujue);
                break;
        }


        //阶段名称 报备 带看
        TextView tv_name = view.findViewById(R.id.tv_name);
        tv_name.setText(newHouseRunningsInfoShowListBean.getModuleName());
        tv_name.setTextColor(Color.parseColor(newHouseRunningsInfoShowListBean.getModuleNameColor()));
        if(newHouseRunningsInfoShowListBean.getStyleID() != 4)
        {
            //标题
            TextView tv_title = view.findViewById(R.id.tv_title);
            if (newHouseRunningsInfoShowListBean.getTitle() != null)
                tv_title.setText(newHouseRunningsInfoShowListBean.getTitle());
            //副标题
            TextView tv_vicetitle = view.findViewById(R.id.tv_vicetitle);
            if (newHouseRunningsInfoShowListBean.getViceTitle() != null)
                tv_vicetitle.setText(newHouseRunningsInfoShowListBean.getViceTitle());
            if (newHouseRunningsInfoShowListBean.getViceTitleColor() != null && !"".equals(newHouseRunningsInfoShowListBean.getViceTitleColor()))
                tv_vicetitle.setTextColor(Color.parseColor(newHouseRunningsInfoShowListBean.getViceTitleColor()));
            //时间
            TextView tv_time = view.findViewById(R.id.tv_time);
            if (newHouseRunningsInfoShowListBean.getOperTime() != null)
                tv_time.setText(newHouseRunningsInfoShowListBean.getOperTime());
        }


        return view;
    }


    /**
     * 图片操作回调
     */
    public interface OnCamreaListener {
        /**
         * 拍照
         *
         * @param pos       索引
         * @param ImageType 图片类型
         */
        public void getImageType(int pos, int ImageType);

        /**
         * 删除图片
         *
         * @param pos     在整个listview索引
         * @param id      图片id
         * @param gridPos 图片在grid的索引
         */
        public void delPic(int pos, int id, int gridPos);
    }

}
