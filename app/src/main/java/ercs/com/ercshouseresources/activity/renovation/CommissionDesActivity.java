package ercs.com.ercshouseresources.activity.renovation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.activity.service.NewHouseDetailActivity;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.newbean.LoginBean;
import ercs.com.ercshouseresources.util.CloseActivityClass;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.util.imageUtil.GlideUtil;


/**
 * Created by Administrator on 2017/8/11.
 * 佣金说明
 */

public class CommissionDesActivity extends BaseActivity {
    @BindView(R.id.tv_content)
    TextView tv_content;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_phone)
    TextView tv_phone;

    public static void start(Activity mactivity, String CommissionAccount, String path) {
        Intent intent = new Intent(mactivity, CommissionDesActivity.class);
        intent.putExtra("CommissionAccount", CommissionAccount);
        intent.putExtra("path", path);
        mactivity.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comdes);
        ButterKnife.bind(this);
        initTitle();
        createView();
        if(!CloseActivityClass.activityList.contains(this))
        {
            CloseActivityClass.activityList.add(this);
        }
    }

    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle("佣金说明");

    }

    private void createView() {
        tv_content.setText(getCA());
        GlideUtil.loadImage(CommissionDesActivity.this, NetHelperNew.URL + getPath(), image, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        List<LoginBean.DataBean.StaffListBean> list = BaseApplication.loginBean.getData().getStaffList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getModuleID().equals("1")) {
                tv_name.setText(list.get(i).getName());
                tv_phone.setText(list.get(i).getPhone());
                break;
            }
        }
    }

    private String getCA() {
        return getIntent().getStringExtra("CommissionAccount");
    }

    private String getPath() {
        return getIntent().getStringExtra("path");
    }
}
