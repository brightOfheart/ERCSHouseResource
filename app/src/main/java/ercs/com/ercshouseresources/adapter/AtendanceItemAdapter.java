package ercs.com.ercshouseresources.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.king.base.adapter.ViewHolderAdapter;
import com.king.base.adapter.holder.ViewHolder;

import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.bean.AtendanceBean;


/**
 * Created by Administrator on 2017/6/27.
 */

public class AtendanceItemAdapter extends ViewHolderAdapter<AtendanceBean.DataBean.InSideStatisticsListModeBean> {
    private Context context;

    public AtendanceItemAdapter(Context context, List<AtendanceBean.DataBean.InSideStatisticsListModeBean> listData) {
        super(context, listData);
        this.context = context;
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, AtendanceBean.DataBean.InSideStatisticsListModeBean dataBean, int position) {
        return inflate(R.layout.adapter_child_item);
    }

    @Override
    public void bindViewDatas(ViewHolder holder, AtendanceBean.DataBean.InSideStatisticsListModeBean dataBean, final int position) {
        holder.setText(R.id.tv_day, dataBean.getStatisticsDateTime());
        holder.setText(R.id.tv_hours, dataBean.getStatisticsDateTimeExplain());
        if (dataBean.getStatisticsExplain().length()>0) {
            holder.setText(R.id.tv_explain, dataBean.getStatisticsExplain());
            holder.getView(R.id.tv_explain).setVisibility(View.VISIBLE);
        } else {
            holder.getView(R.id.tv_explain).setVisibility(View.GONE);
        }
    }
}
