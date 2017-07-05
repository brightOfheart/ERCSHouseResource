package ercs.com.ercshouseresources.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import com.king.base.adapter.ViewHolderAdapter;
import com.king.base.adapter.holder.ViewHolder;

import java.text.ParseException;
import java.util.List;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.bean.SchedulingBean;
import ercs.com.ercshouseresources.util.TimeUtil;
import ercs.com.ercshouseresources.view.NoScrollListView;
/**
 * Created by Administrator on 2017/6/26.
 */

public class SchedulingAdapter extends ViewHolderAdapter<SchedulingBean.DataBean> {
    private Context context;

    public SchedulingAdapter(Context context, List<SchedulingBean.DataBean> listData) {
        super(context, listData);
        this.context = context;
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, SchedulingBean.DataBean dataBean, int position) {
        return inflate(R.layout.item_schedulingadapterchild);
    }

    @Override
    public void bindViewDatas(ViewHolder holder, SchedulingBean.DataBean dataBean, final int position) {
        holder.setText(R.id.tv_time,getDay(dataBean.getDate()));
        ((NoScrollListView) holder.getView(R.id.litviews)).setAdapter(new SchedulingAdapterChildItemAdapter(context, dataBean.getUData()));
    }
    /**
     * 获取当前的年月日
     *
     * @param str
     * @return
     */
    private String getDay(String str) {
        String newdate = "";
        try {
            String date = TimeUtil.dealDateFormat(str);
            int m = date.indexOf(" ");
            newdate = date.substring(0, m);


        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newdate;
    }
}
