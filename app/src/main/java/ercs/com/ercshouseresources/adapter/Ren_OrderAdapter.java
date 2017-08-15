package ercs.com.ercshouseresources.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.king.base.adapter.ViewHolderRecyclerAdapter;
import com.king.base.adapter.holder.ViewHolder;

import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.bean.ClerkBean;
import ercs.com.ercshouseresources.bean.Ren_OrderBean;
import ercs.com.ercshouseresources.network.NetHelper;
import ercs.com.ercshouseresources.util.OtherUitl;
import ercs.com.ercshouseresources.util.imageUtil.GlideUtil;

/**
 * Created by Administrator on 2017/8/11.
 * 装修订单
 */

public class Ren_OrderAdapter extends ViewHolderRecyclerAdapter<Ren_OrderBean.DataBean> {
    private Context context;
    private Activity activity;

    public Ren_OrderAdapter(Activity activity, Context context, List<Ren_OrderBean.DataBean> listData) {
        super(context, listData);
        this.context = context;
        this.activity = activity;
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, int viewType) {
        return inflate(R.layout.adapter_renorder);
    }

    @Override
    public void bindViewDatas(final ViewHolder holder, final Ren_OrderBean.DataBean datas, final int position) {
        holder.setText(R.id.tv_name, datas.getCustomerName());
        holder.setText(R.id.tv_phone, datas.getCustomerPhone());
        holder.setText(R.id.tv_area, datas.getArea());
        holder.setText(R.id.tv_address, datas.getCustomerAddress());
        holder.setText(R.id.tv_remarks, datas.getRemark());
        holder.setText(R.id.tv_company, datas.getDecorationCompanyName());
        holder.setText(R.id.tv_economicman, "经济人:" + datas.getUserName());
        holder.setText(R.id.tv_state, datas.getOperTime() + "  " + datas.getState());
    }
}
