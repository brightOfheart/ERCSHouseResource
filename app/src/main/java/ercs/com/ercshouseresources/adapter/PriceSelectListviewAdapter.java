package ercs.com.ercshouseresources.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.king.base.adapter.ViewHolderAdapter;
import com.king.base.adapter.holder.ViewHolder;

import java.util.List;

import ercs.com.ercshouseresources.R;

/**
 * Created by Administrator on 2017/7/17.
 */

public class PriceSelectListviewAdapter extends ViewHolderAdapter<String> {
    public PriceSelectListviewAdapter(Context context, List<String> listData) {
        super(context, listData);
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, String s, int position) {
        return layoutInflater.inflate(R.layout.item_priceselect_listview,null);
    }

    @Override
    public void bindViewDatas(ViewHolder holder, String s, int position) {

    }
}
