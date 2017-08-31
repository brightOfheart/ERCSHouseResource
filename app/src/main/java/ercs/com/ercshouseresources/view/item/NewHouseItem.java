package ercs.com.ercshouseresources.view.item;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import ercs.com.ercshouseresources.activity.service.CommissionExplainActivity;
import ercs.com.ercshouseresources.activity.service.HouseBigPicActivity;
import ercs.com.ercshouseresources.activity.service.NewHouseDetailActivity;
import ercs.com.ercshouseresources.activity.service.ReportingClientsActivity;
import ercs.com.ercshouseresources.adapter.SchedulingAdapterChildItemAdapter;
import ercs.com.ercshouseresources.bean.NewHouseDetailBean;
import ercs.com.ercshouseresources.bean.SchedulingBean;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.imageUtil.GlideUtil;
import ercs.com.ercshouseresources.view.NoScrollListView;

/**
 * Created by Administrator on 2017/7/24.
 */

public class NewHouseItem extends RelativeLayout {
    private LinearLayout linearLayout;
    @BindView(R.id.tv_area)
    TextView tv_area;
    @BindView(R.id.tv_price)
    TextView tv_price;
    @BindView(R.id.image)
    ImageView image;
    private String jsons;
    private Activity activity;
    private String counts;

    public NewHouseItem(Activity context, NewHouseDetailBean.DataBean.HouseTypeListBean bean, String json, String count) {
        super(context);
        LayoutInflater mInflater = LayoutInflater.from(context);
        linearLayout = (LinearLayout) mInflater.inflate(
                R.layout.adapter_newhouse, null);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        params.setMargins(40, 0, 0, 0);
        this.addView(linearLayout, params);
        ButterKnife.bind(this);
        tv_area.setText(bean.getName());
        tv_price.setText(bean.getPrice());
        jsons = json;
        activity = context;
        counts = count;
        GlideUtil.loadImage(context, NetHelperNew.URL + bean.getImagePath(), image, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
    }

    /**
     * 点击事件的处理
     *
     * @param view
     */
    @OnClick({R.id.ly})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ly:
                HouseBigPicActivity.start(activity, jsons, counts);
                break;


        }
    }
}
