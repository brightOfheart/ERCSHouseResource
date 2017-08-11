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
import ercs.com.ercshouseresources.network.NetHelper;
import ercs.com.ercshouseresources.util.OtherUitl;
import ercs.com.ercshouseresources.util.imageUtil.GlideUtil;

/**
 * Created by Administrator on 2017/8/11.
 * 装修订单
 */

public class Ren_OrderAdapter extends ViewHolderRecyclerAdapter<ClerkBean.Datas> {
    private Context context;
    private Activity activity;

    public Ren_OrderAdapter(Activity activity, Context context, List<ClerkBean.Datas> listData) {
        super(context, listData);
        this.context = context;
        this.activity = activity;
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, int viewType) {
        return inflate(R.layout.adapter_renorder);
    }

    @Override
    public void bindViewDatas(final ViewHolder holder, final ClerkBean.Datas datas, final int position) {
//        holder.setText(R.id.tv_name, datas.getName());
//        holder.setText(R.id.tv_phone, datas.getName());
//        holder.setText(R.id.tv_area, datas.getName());
//        holder.setText(R.id.tv_address, datas.getName());
//        holder.setText(R.id.tv_remarks, datas.getName());
//        holder.setText(R.id.tv_company, datas.getName());
//        holder.setText(R.id.tv_economicman, datas.getName());
//        holder.setText(R.id.tv_state, datas.getName());
    }
}
