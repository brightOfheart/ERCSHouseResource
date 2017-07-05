package ercs.com.ercshouseresources.view.item;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.adapter.FieldItemAdapter;
import ercs.com.ercshouseresources.bean.FieldCustomBean;
import ercs.com.ercshouseresources.bean.FieldCustomContentBean;
import ercs.com.ercshouseresources.bean.SchedulingBean;
import ercs.com.ercshouseresources.view.NoScrollListView;

/**
 * Created by Administrator on 2017/6/28.
 * 外勤统计的Item
 */

public class FieldItem extends RelativeLayout {
    private LinearLayout linearLayout;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_count)
    TextView tv_count;
    @BindView(R.id.listview)
    NoScrollListView listview;
    @BindView(R.id.iv_icon)
    ImageView iv_icon;
    private boolean isclick = true;

    public FieldItem(Activity context, List<FieldCustomBean> fieldCustomBean, List<FieldCustomContentBean> list) {
        super(context);
        LayoutInflater mInflater = LayoutInflater.from(context);
        linearLayout = (LinearLayout) mInflater.inflate(
                R.layout.item_fielditem, null);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 0);
        this.addView(linearLayout, params);
        ButterKnife.bind(this);
        tv_time.setText(fieldCustomBean.get(0).getTime());
        tv_count.setText(list.size() + "次");
        listview.setAdapter(new FieldItemAdapter(context, list));
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
                if (isclick) {
                    iv_icon.setImageResource(R.mipmap.down_icon);
                    isclick = false;
                    listview.setVisibility(View.GONE);
                } else {
                    iv_icon.setImageResource(R.mipmap.up_icon);
                    isclick = true;
                    listview.setVisibility(View.VISIBLE);
                }
                break;

        }
    }
}
