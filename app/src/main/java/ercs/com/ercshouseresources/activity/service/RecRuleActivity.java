package ercs.com.ercshouseresources.activity.service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.newbean.LoginBean;
import ercs.com.ercshouseresources.util.SPUtil;
import ercs.com.ercshouseresources.util.TitleControl;

/**
 * Created by Administrator on 2017/7/31.
 * 推荐规则
 */

public class RecRuleActivity extends BaseActivity {
    /**
     * 页面跳转
     */
    public static void start(Activity mactivity, String str1, String str2, String str3) {
        Intent intent = new Intent(mactivity, RecRuleActivity.class);
        intent.putExtra("str1", str1);
        intent.putExtra("str2", str2);
        intent.putExtra("str3", str3);
        mactivity.startActivity(intent);
    }

    @BindView(R.id.tv_1)
    TextView tv_1;
    @BindView(R.id.tv_2)
    TextView tv_2;
    @BindView(R.id.tv_3)
    TextView tv_3;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_phone)
    TextView tv_phone;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recrule);
        ButterKnife.bind(this);
        createdata();
        initTitle();
    }
    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle("推荐规则");

    }
    /**
     * 初始化数据
     */
    private void createdata() {
        tv_1.setText(getStr1());
        tv_2.setText(getStr2());
        tv_3.setText(getStr3());
        List<LoginBean.DataBean.StaffListBean> list = BaseApplication.loginBean.getData().getStaffList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getModuleID().equals("1")) {
                tv_name.setText(list.get(i).getName());
                tv_phone.setText(list.get(i).getPhone());
                break;
            }
        }
    }


    private String getStr1() {

        return getIntent().getStringExtra("str1");
    }

    private String getStr2() {

        return getIntent().getStringExtra("str2");
    }

    private String getStr3() {

        return getIntent().getStringExtra("str3");
    }

}
