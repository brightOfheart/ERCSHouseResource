package ercs.com.ercshouseresources.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.king.base.adapter.ViewHolderRecyclerAdapter;
import com.king.base.adapter.holder.ViewHolder;

import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.bean.ProcessBean;
import ercs.com.ercshouseresources.fragment.HouseFragment;

/**
 * Created by Administrator on 2017/7/12.
 * 房源列表的Adapter
 */

public class HouseAdapter extends ViewHolderRecyclerAdapter<String> {

    public HouseAdapter( Context context, List<String> listData) {
        super(context, listData);
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, int viewType) {
        return inflate(R.layout.adapter_house);
    }

    @Override
    public void bindViewDatas(final ViewHolder holder, String datas, final int position) {

    }
}
