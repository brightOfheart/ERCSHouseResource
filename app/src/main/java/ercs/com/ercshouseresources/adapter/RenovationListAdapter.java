package ercs.com.ercshouseresources.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.king.base.adapter.ViewHolderRecyclerAdapter;
import com.king.base.adapter.holder.ViewHolder;

import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.renovation.RenovationDetailActivity;
import ercs.com.ercshouseresources.bean.ClerkBean;
import ercs.com.ercshouseresources.bean.RenovationListBean;
import ercs.com.ercshouseresources.network.NetHelper;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.OtherUitl;
import ercs.com.ercshouseresources.util.imageUtil.GlideUtil;

/**
 * Created by Administrator on 2017/8/9.
 */

public class RenovationListAdapter extends ViewHolderRecyclerAdapter<RenovationListBean.DataBean> {
    private Context context;
    private Activity activity;

    public RenovationListAdapter(Activity activity, Context context, List<RenovationListBean.DataBean> listData) {
        super(context, listData);
        this.context = context;
        this.activity = activity;
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, int viewType) {
        return inflate(R.layout.adapter_renovationlist);
    }

    @Override
    public void bindViewDatas(final ViewHolder holder, final RenovationListBean.DataBean datas, final int position) {
        Log.d("XXX",datas.getName());
        holder.setText(R.id.tv_title, datas.getName());
        holder.setText(R.id.tv_typeContent, datas.getBrokerage());
        holder.setText(R.id.tv_setc, datas.getCommissionAccount());
        holder.setText(R.id.tv_state,datas.getAwardDescription());
        GlideUtil.loadImage(context,NetHelperNew.URL + datas.getImagePath(), (ImageView) holder.getView(R.id.iv_photo),R.mipmap.ic_launcher,R.mipmap.ic_launcher);
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RenovationDetailActivity.start(activity,datas.getId());
            }
        });
    }
}
