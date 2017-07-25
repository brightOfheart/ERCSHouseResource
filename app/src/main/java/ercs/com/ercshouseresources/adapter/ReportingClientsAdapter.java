package ercs.com.ercshouseresources.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.king.base.adapter.ViewHolderRecyclerAdapter;
import com.king.base.adapter.holder.ViewHolder;

import java.util.List;

import ercs.com.ercshouseresources.R;

/**
 * 报备客户列表
 * Created by Administrator on 2017/7/24.
 */

public class ReportingClientsAdapter extends ViewHolderRecyclerAdapter<String> {
    public ReportingClientsAdapter(Context context, List<String> listData) {
        super(context, listData);
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, int viewType) {
        return layoutInflater.inflate(R.layout.item_reporting_clients,null);
    }

    @Override
    public void bindViewDatas(ViewHolder holder, String s, int position) {

    }
}
