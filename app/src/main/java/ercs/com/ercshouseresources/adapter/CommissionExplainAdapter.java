package ercs.com.ercshouseresources.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.king.base.adapter.ViewHolderAdapter;
import com.king.base.adapter.holder.ViewHolder;

import java.text.ParseException;
import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.bean.CommissionExplainBean;
import ercs.com.ercshouseresources.util.TimeUtil;

/**
 * 佣金说明更改记录列表
 * Created by Administrator on 2017/7/26.
 */

public class CommissionExplainAdapter extends ViewHolderAdapter<CommissionExplainBean.DataBean.ModifyBuildingBrokerageLogListBean>{
    public CommissionExplainAdapter(Context context, List<CommissionExplainBean.DataBean.ModifyBuildingBrokerageLogListBean> listData) {
        super(context, listData);
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, CommissionExplainBean.DataBean.ModifyBuildingBrokerageLogListBean s, int position) {
        return layoutInflater.inflate(R.layout.item_commissionexplain,null);
    }

    @Override
    public void bindViewDatas(ViewHolder holder, CommissionExplainBean.DataBean.ModifyBuildingBrokerageLogListBean dataBean, int position) {

        holder.setText(R.id.tv_content,dataBean.getSellingBrokerage());

        try {
            holder.setText(R.id.tv_time, TimeUtil.dealDateFormatNomm(dataBean.getCreatedTime()));

        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}
