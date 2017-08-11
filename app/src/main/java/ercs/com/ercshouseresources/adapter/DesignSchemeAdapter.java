package ercs.com.ercshouseresources.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.king.base.adapter.ViewHolderRecyclerAdapter;
import com.king.base.adapter.holder.ViewHolder;
import java.util.List;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.bean.CustomersListBean;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.imageUtil.GlideUtil;


/**
 * Created by Administrator on 2017/8/11.
 */

public class DesignSchemeAdapter extends ViewHolderRecyclerAdapter<CustomersListBean.DataBean> {
    private String BuildingID;
    public DesignSchemeAdapter(Context context, List<CustomersListBean.DataBean> listData, String BuildingID) {
        super(context, listData);
        this.BuildingID=BuildingID;
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, int viewType) {
        return layoutInflater.inflate(R.layout.adapter_designscheme,null);
    }

    @Override
    public void bindViewDatas(ViewHolder holder, final CustomersListBean.DataBean dataBean, int position) {

        holder.setText(R.id.tv_title,dataBean.getName());
        holder.setText(R.id.tv_other,dataBean.getName());
        //GlideUtil.loadImage(context, NetHelperNew.URL + dataBean, (ImageView) holder.getView(R.id.iv_pic),R.mipmap.ic_launcher,R.mipmap.ic_launcher);


    }
}
