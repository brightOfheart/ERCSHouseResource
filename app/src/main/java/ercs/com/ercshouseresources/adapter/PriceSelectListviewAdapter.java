package ercs.com.ercshouseresources.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.king.base.adapter.ViewHolderAdapter;
import com.king.base.adapter.holder.ViewHolder;

import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.bean.PriceBean;

/**
 * Created by Administrator on 2017/7/17.
 */

public class PriceSelectListviewAdapter extends ViewHolderAdapter<PriceBean> {


    public PriceSelectListviewAdapter(Context context, List<PriceBean> listData) {
        super(context, listData);
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, PriceBean priceBean, int position) {
        return layoutInflater.inflate(R.layout.item_priceselect_listview,null);
    }

    @Override
    public void bindViewDatas(ViewHolder holder, PriceBean priceBean, int position) {

        holder.setText(R.id.tv_content,priceBean.getTitle());
    }
}
