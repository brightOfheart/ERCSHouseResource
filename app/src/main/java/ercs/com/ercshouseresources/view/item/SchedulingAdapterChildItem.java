package ercs.com.ercshouseresources.view.item;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.adapter.SchedulingAdapterChildItemAdapter;
import ercs.com.ercshouseresources.bean.SchedulingBean;
import ercs.com.ercshouseresources.util.TimeUtil;
import ercs.com.ercshouseresources.view.NoScrollListView;

/**
 * Created by Administrator on 2017/6/26.
 * 排班管理的子Item
 */

public class SchedulingAdapterChildItem extends RelativeLayout {
    private LinearLayout linearLayout;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.litviews)
    NoScrollListView litview;
    public SchedulingAdapterChildItem(Context context,String time,List<SchedulingBean.DataBean.UDataBean> listData) {
        super(context);
        LayoutInflater mInflater = LayoutInflater.from(context);
        linearLayout = (LinearLayout) mInflater.inflate(
                R.layout.item_schedulingadapterchild, null);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 0);
        this.addView(linearLayout, params);
        ButterKnife.bind(this);
        tv_time.setText(getDay(time));
        litview.setAdapter(new SchedulingAdapterChildItemAdapter(context,listData));
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
