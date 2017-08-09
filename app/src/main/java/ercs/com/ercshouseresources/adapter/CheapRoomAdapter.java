package ercs.com.ercshouseresources.adapter;

import android.app.Activity;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.king.base.adapter.ViewHolderRecyclerAdapter;
import com.king.base.adapter.holder.ViewHolder;

import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.cheaproom.CheapRoomDetailActivity;
import ercs.com.ercshouseresources.activity.service.NewHouseDetailActivity;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.bean.CheapRoomListBean;
import ercs.com.ercshouseresources.bean.NewHouseListBean;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.imageUtil.GlideUtil;

/**
 * Created by Administrator on 2017/8/7.
 */

public class CheapRoomAdapter extends ViewHolderRecyclerAdapter<CheapRoomListBean.DataBean> {
    Activity contexts;

    public CheapRoomAdapter(Activity context, List<CheapRoomListBean.DataBean> listData) {
        super(context, listData);
        contexts = context;
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, int viewType) {
        return layoutInflater.inflate(R.layout.adapter_cheaproomlist, null);
    }

    @Override
    public void bindViewDatas(ViewHolder holder, final CheapRoomListBean.DataBean dataBean, int position) {

        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheapRoomDetailActivity.start(contexts, dataBean.getId(),dataBean.getBuildingName());

            }
        });
        holder.setText(R.id.tv_title, dataBean.getBuildingName());
        holder.setText(R.id.tv_price, dataBean.getLowPrice());
        holder.setText(R.id.tv_subprice, "(原值:"+dataBean.getCostPrice()+")");
        TextView tv=holder.getView(R.id.tv_subprice);
        tv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.setText(R.id.tv_areao, dataBean.getArea()+"~"+dataBean.getIsLoan());
        holder.setText(R.id.tv_paytime, dataBean.getCommissionAccount());
        holder.setText(R.id.tv_p1, dataBean.getBrokerage());
        holder.setText(R.id.tv_p2, dataBean.getPoundage());
        GlideUtil.loadImage(context, NetHelperNew.URL + dataBean.getImagePath(), (ImageView) holder.getView(R.id.iv_goods), R.mipmap.ic_launcher, R.mipmap.ic_launcher);

    }
}
