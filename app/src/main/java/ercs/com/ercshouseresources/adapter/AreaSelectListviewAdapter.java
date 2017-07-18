package ercs.com.ercshouseresources.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.king.base.adapter.ViewHolderAdapter;
import com.king.base.adapter.holder.ViewHolder;

import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.bean.AreaBean;

/**
 * 区域选择适配器
 * Created by Administrator on 2017/7/17.
 */

public class AreaSelectListviewAdapter extends ViewHolderAdapter<AreaBean.DataBean> {


    public AreaSelectListviewAdapter(Context context, List<AreaBean.DataBean> listData) {
        super(context, listData);
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, AreaBean.DataBean dataBean, int position) {
        return layoutInflater.inflate(R.layout.item_priceselect_listview,null);
    }

    @Override
    public void bindViewDatas(ViewHolder holder, AreaBean.DataBean dataBean, int position) {
               holder.setText(R.id.tv_content,dataBean.getName());
    }
}
