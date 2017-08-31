package ercs.com.ercshouseresources.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import com.king.base.adapter.ViewHolderRecyclerAdapter;
import com.king.base.adapter.holder.ViewHolder;

import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.cheaproom.Cheap_OrderReportDetailActivity;
import ercs.com.ercshouseresources.activity.service.OrderReportDetailActivity;
import ercs.com.ercshouseresources.bean.OrderReportListBean;

/**
 * Created by Administrator on 2017/8/22.
 */

public class CheapOrderReportListAdapter extends ViewHolderRecyclerAdapter<OrderReportListBean.DataBean> {
    public CheapOrderReportListAdapter(Context context, List<OrderReportListBean.DataBean> listData) {
        super(context, listData);
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, int viewType) {
        return layoutInflater.inflate(R.layout.item_order_report_list,null);
    }

    @Override
    public void bindViewDatas(ViewHolder holder, final OrderReportListBean.DataBean dataBean, int position) {
        holder.setText(R.id.tv_name,dataBean.getCustomerName());
        holder.setText(R.id.tv_phone,dataBean.getCustomerPhone());
        holder.setText(R.id.tv_buildingname,dataBean.getBuildingName());
        holder.setText(R.id.tv_agent,"经纪人："+dataBean.getUserName());//经纪人

        holder.setText(R.id.tv_state,dataBean.getState());
        holder.setText(R.id.tv_time, dataBean.getOperTime());


        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Cheap_OrderReportDetailActivity.class);
                intent.putExtra("Id",dataBean.getId()+"");
                intent.putExtra("title",dataBean.getBuildingName());
                context.startActivity(intent);
            }
        });
    }
}
