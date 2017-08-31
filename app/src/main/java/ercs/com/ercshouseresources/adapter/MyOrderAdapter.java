package ercs.com.ercshouseresources.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.king.base.adapter.ViewHolderRecyclerAdapter;
import com.king.base.adapter.holder.ViewHolder;

import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.cheaproom.CheapRoomListActivity;
import ercs.com.ercshouseresources.activity.cheaproom.Cheap_OrderReportListActivity;
import ercs.com.ercshouseresources.activity.financial.FinancialOrderActivity;
import ercs.com.ercshouseresources.activity.renovation.Ren_OrderActivity;
import ercs.com.ercshouseresources.activity.service.NewHouseActivity;
import ercs.com.ercshouseresources.activity.service.OrderReportListActivity;
import ercs.com.ercshouseresources.bean.ClerkBean;
import ercs.com.ercshouseresources.bean.ServiceBean;
import ercs.com.ercshouseresources.network.NetHelper;
import ercs.com.ercshouseresources.util.OtherUitl;
import ercs.com.ercshouseresources.util.imageUtil.GlideUtil;

/**
 * Created by Administrator on 2017/8/22.
 */

public class MyOrderAdapter extends ViewHolderRecyclerAdapter<ServiceBean> {
    private Context context;
    private Activity activity;

    public MyOrderAdapter(Activity activity, Context context, List<ServiceBean> listData) {
        super(context, listData);
        this.context = context;
        this.activity = activity;
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, int viewType) {
        return inflate(R.layout.adapter_myorder);
    }

    @Override
    public void bindViewDatas(final ViewHolder holder, final ServiceBean datas, final int position) {
        holder.setText(R.id.tv_title, datas.getTitle());
        holder.setImageResource(R.id.iv_pic, datas.getPic());
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (position) {
                    case 0://新房订单
                        activity.startActivity(new Intent(activity, OrderReportListActivity.class));
                        break;
                    case 1://低价房订单
                        activity.startActivity(new Intent(activity, Cheap_OrderReportListActivity.class));
                        break;
                    case 2://二手房订单
                        break;
                    case 3://租房订单
                        break;
                    case 4://金融订单
                        activity.startActivity(new Intent(activity, FinancialOrderActivity.class));
                        break;
                    case 5://装修订单
                        activity.startActivity(new Intent(activity, Ren_OrderActivity.class));
                        break;
                    case 6://家政订单
                        break;
                    case 7://活动订单
                        break;
                    case 8://旅游订单
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
