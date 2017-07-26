package ercs.com.ercshouseresources.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.king.base.adapter.ViewHolderRecyclerAdapter;
import com.king.base.adapter.holder.ViewHolder;

import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.service.NewHouseDetailActivity;
import ercs.com.ercshouseresources.bean.NewHouseListBean;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.imageUtil.GlideUtil;

/**
 * 新房列表适配器
 * Created by Administrator on 2017/7/25.
 */

public class NewBuildingAdapter extends ViewHolderRecyclerAdapter<NewHouseListBean.DataBean> {
    public NewBuildingAdapter(Context context, List<NewHouseListBean.DataBean> listData) {
        super(context, listData);
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, int viewType) {
        return layoutInflater.inflate(R.layout.item_newbuilding,null);
    }

    @Override
    public void bindViewDatas(ViewHolder holder, NewHouseListBean.DataBean dataBean, int position) {

        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, NewHouseDetailActivity.class));
            }
        });

        holder.setText(R.id.tv_housename,dataBean.getName());
        holder.setText(R.id.tv_content,dataBean.getSellingBrokerage());
        holder.setText(R.id.tv_saletotal,dataBean.getSellingPrice());
        holder.setText(R.id.tv_commissionaccount,dataBean.getCommissionAccount());//周期

        GlideUtil.loadImage(context, NetHelperNew.URL+dataBean.getImagePath(), (ImageView) holder.getView(R.id.iv_goods),R.mipmap.ic_launcher,R.mipmap.ic_launcher);

    }
}
