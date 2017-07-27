package ercs.com.ercshouseresources.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.king.base.adapter.ViewHolderAdapter;
import com.king.base.adapter.holder.ViewHolder;

import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.bean.CommissionExplainBean;

/**
 * 佣金说明 应得佣金列表
 * Created by Administrator on 2017/7/26.
 */

public class CommissionDeservedAdapter extends ViewHolderAdapter<CommissionExplainBean.DataBean.BuildingsBrokerageGroupListBean>{


    private String node;
    public CommissionDeservedAdapter(Context context, List<CommissionExplainBean.DataBean.BuildingsBrokerageGroupListBean> listData,String node) {
        super(context, listData);
        this.node=node;
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, CommissionExplainBean.DataBean.BuildingsBrokerageGroupListBean s, int position) {
        return layoutInflater.inflate(R.layout.item_commission_deserved,null);
    }

    @Override
    public void bindViewDatas(ViewHolder holder, CommissionExplainBean.DataBean.BuildingsBrokerageGroupListBean buildingsBrokerageGroupListBean, int position) {

        holder.setText(R.id.tv_title,buildingsBrokerageGroupListBean.getName());
        holder.setText(R.id.tv_brokerage,buildingsBrokerageGroupListBean.getBrokerage());
        holder.setText(R.id.tv_node,node);
    }
}
