package ercs.com.ercshouseresources.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.king.base.adapter.ViewHolderRecyclerAdapter;
import com.king.base.adapter.holder.ViewHolder;

import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.bean.CheapReportOrderDetailBean;
import ercs.com.ercshouseresources.bean.ReportOrderDetailBean;

/**
 * Created by Administrator on 2017/8/30.
 */

public class CheapFollowMsgAdapter extends ViewHolderRecyclerAdapter<CheapReportOrderDetailBean.DataBean.MessageBoardsShowModel> {
    private Context context;
    private Activity activity;

    public CheapFollowMsgAdapter(Activity activity, Context context, List<CheapReportOrderDetailBean.DataBean.MessageBoardsShowModel> listData) {
        super(context, listData);
        this.context = context;
        this.activity = activity;
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, int viewType) {
        return inflate(R.layout.adapter_followmsg);
    }

    @Override
    public void bindViewDatas(final ViewHolder holder, final CheapReportOrderDetailBean.DataBean.MessageBoardsShowModel datas, final int position) {
        holder.setText(R.id.tv_name, datas.getStaffName());
        holder.setText(R.id.tv_time, datas.getCreatedTime());
        holder.setText(R.id.tv_content, datas.getContent());
    }
}
