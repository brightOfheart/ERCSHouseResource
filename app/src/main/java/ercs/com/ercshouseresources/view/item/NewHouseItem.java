package ercs.com.ercshouseresources.view.item;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.adapter.SchedulingAdapterChildItemAdapter;
import ercs.com.ercshouseresources.bean.SchedulingBean;
import ercs.com.ercshouseresources.view.NoScrollListView;

/**
 * Created by Administrator on 2017/7/24.
 */

public class NewHouseItem extends RelativeLayout {
    private LinearLayout linearLayout;
//    @BindView(R.id.tv_time)
//    TextView tv_time;
//    @BindView(R.id.litviews)
//    NoScrollListView litview;
    public NewHouseItem(Context context) {
        super(context);
        LayoutInflater mInflater = LayoutInflater.from(context);
        linearLayout = (LinearLayout) mInflater.inflate(
                R.layout.adapter_newhouse, null);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 0);
        this.addView(linearLayout, params);
        ButterKnife.bind(this);

    }

}