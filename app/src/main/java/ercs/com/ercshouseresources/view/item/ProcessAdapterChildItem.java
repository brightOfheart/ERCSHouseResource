package ercs.com.ercshouseresources.view.item;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.process.ProcessContentAcvitity;
import ercs.com.ercshouseresources.bean.ProcessBean;
import ercs.com.ercshouseresources.network.NetHelper;
import ercs.com.ercshouseresources.util.imageUtil.GlideUtil;

/**
 * Created by Administrator on 2017/6/23.
 * 流程审批适配器里面的Item
 */

public class ProcessAdapterChildItem extends RelativeLayout {
    private LinearLayout linearLayout;
    @BindView(R.id.iv_photo)
    ImageView iv_photo;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_time)
    TextView tv_time;
    private ProcessBean.DataBean datas;
    private Activity activity;
    public ProcessAdapterChildItem(Activity activity,ProcessBean.DataBean datas) {
        super(activity);
        this.datas=datas;
        this.activity=activity;
        LayoutInflater mInflater = LayoutInflater.from(activity);
        linearLayout = (LinearLayout) mInflater.inflate(
                R.layout.item_processadapterchild, null);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 0);
        this.addView(linearLayout, params);
        ButterKnife.bind(this);
        setData(datas);
    }

    private void setData(ProcessBean.DataBean datas) {
        tv_name.setText(datas.getUserName());
        tv_time.setText(datas.getCreatedTime());
        GlideUtil.loadCircleImage(activity, NetHelper.URL + datas.getPhotoPath(), iv_photo);
    }

    @OnClick({R.id.iv_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_next://
                ProcessContentAcvitity.start(activity,datas.getUserName(),datas.getCreatedTime(),datas.getPhotoPath(),datas.getId(),datas.getLeaveType());
                break;

        }
    }
}
