package ercs.com.ercshouseresources.view.atendance;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.attendance.AtendanceDetailActivity;
import ercs.com.ercshouseresources.adapter.AtendanceItemAdapter;
import ercs.com.ercshouseresources.bean.AtendanceBean;


/**
 * Created by Administrator on 2017/6/27.
 */

public class AtendanceItem extends RelativeLayout  {
    private LinearLayout linearLayout;
    @BindView(R.id.tv_kind)
    TextView tv_kind;
    @BindView(R.id.tv_day)
    TextView tv_day;
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.iv_icon)
    ImageView iv_icon;
    @BindView(R.id.ly_item)
    LinearLayout ly_item;
    private Activity activity;
    private boolean isclick=true;
    public AtendanceItem(final Activity activity, AtendanceBean.DataBean dataBean, final List<AtendanceBean.DataBean.InSideStatisticsListModeBean> data) {
        super(activity);
        this.activity=activity;
        LayoutInflater mInflater = LayoutInflater.from(activity);
        linearLayout = (LinearLayout) mInflater.inflate(
                R.layout.item_atendance, null);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 0);
        this.addView(linearLayout, params);
        ButterKnife.bind(this);
        tv_kind.setText(dataBean.getStatisticsTypeName());
        tv_day.setText(dataBean.getTypeExplain());
        listview.setAdapter(new AtendanceItemAdapter(activity,data));
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AtendanceBean.DataBean.InSideStatisticsListModeBean inSideStatisticsListModeBean = data.get(i);
                String paramsDate = inSideStatisticsListModeBean.getStatisticsDateTime();
                AtendanceDetailActivity.start(activity,paramsDate);
            }
        });
    }

    /**
     * 点击事件的处理
     *
     * @param view
     */
    @OnClick({R.id.ly_item})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ly_item:
                if(isclick)
                {
                    iv_icon.setImageResource(R.mipmap.down_icon);
                    isclick=false;
                    listview.setVisibility(View.GONE);
                }
                else
                {
                    iv_icon.setImageResource(R.mipmap.up_icon);
                    isclick=true;
                    listview.setVisibility(View.VISIBLE);
                }
                break;

        }
    }


}
