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
import ercs.com.ercshouseresources.bean.FinancialOrderDetailBean;

/**
 * Created by Administrator on 2017/8/30.
 */

public class FinancialFollowMsgAdapter extends ViewHolderRecyclerAdapter<FinancialOrderDetailBean.DataBean.MessageBoardsShowModel> {
    private Context context;
    private Activity activity;

    public FinancialFollowMsgAdapter(Activity activity, Context context, List<FinancialOrderDetailBean.DataBean.MessageBoardsShowModel> listData) {
        super(context, listData);
        this.context = context;
        this.activity = activity;
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, int viewType) {
        return inflate(R.layout.adapter_followmsg);
    }

    @Override
    public void bindViewDatas(final ViewHolder holder, final FinancialOrderDetailBean.DataBean.MessageBoardsShowModel datas, final int position) {
        holder.setText(R.id.tv_name, datas.getStaffName());
        holder.setText(R.id.tv_time, datas.getCreatedTime());
        holder.setText(R.id.tv_content, datas.getContent());
    }
}
