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
import ercs.com.ercshouseresources.activity.renovation.Ren_resignDetailActivity;
import ercs.com.ercshouseresources.bean.CustomersListBean;
import ercs.com.ercshouseresources.bean.RenListBean;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.imageUtil.GlideUtil;


/**
 * Created by Administrator on 2017/8/11.
 */

public class DesignSchemeAdapter extends ViewHolderRecyclerAdapter<RenListBean.DataBean> {
    private Activity activity;
    public DesignSchemeAdapter(Activity context, List<RenListBean.DataBean> listData) {
        super(context, listData);
        activity=context;
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, int viewType) {
        return layoutInflater.inflate(R.layout.adapter_designscheme,null);
    }

    @Override
    public void bindViewDatas(ViewHolder holder, final RenListBean.DataBean dataBean, int position) {

        holder.setText(R.id.tv_title,dataBean.getName());
        holder.setText(R.id.tv_other,dataBean.getContent());
        GlideUtil.loadImage(context, NetHelperNew.URL + dataBean.getCaseImagePath(), (ImageView) holder.getView(R.id.iv_pic),R.mipmap.ic_launcher,R.mipmap.ic_launcher);
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ren_resignDetailActivity.start(activity,dataBean.getId());
            }
        });

    }
}
