package ercs.com.ercshouseresources.activity.renovation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.bean.CustomersListBean;
import ercs.com.ercshouseresources.bean.RenSelectListBean;
import ercs.com.ercshouseresources.bean.Ren_resignDetailBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.CloseActivityClass;
import ercs.com.ercshouseresources.util.NetWorkUtil;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;
import ercs.com.ercshouseresources.view.item.Ren_ResginDetailItem;

/**
 * Created by Administrator on 2017/8/11.
 * 设计方案详情
 */

public class Ren_resignDetailActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_content;
    @BindView(R.id.tv_price)
    TextView tv_price;
    @BindView(R.id.tv_housetype)
    TextView tv_housetype;
    @BindView(R.id.tv_area)
    TextView tv_area;
    @BindView(R.id.tv_date)
    TextView tv_date;
    @BindView(R.id.tv_teacher)
    TextView tv_teacher;
    @BindView(R.id.tv_desginstyle)
    TextView tv_desginstyle;
    @BindView(R.id.tv_worktype)
    TextView tv_worktype;
    @BindView(R.id.ly_all)
    LinearLayout ly_all;
    private LoadingDialog dialog;

    /**
     * 页面跳转
     */
    public static void start(Activity mactivity, String Id) {
        Intent intent = new Intent(mactivity, Ren_resignDetailActivity.class);
        intent.putExtra("Id", Id);
        mactivity.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resigndetail);
        ButterKnife.bind(this);
        initTitle();
        if (!CloseActivityClass.activityList.contains(this)) {
            CloseActivityClass.activityList.add(this);
        }
        if (NetWorkUtil.check(getApplicationContext()))
            loadData();
    }

    /**
     * 加载网络数据
     */
    private void loadData() {
        dialog.show();
        NetHelperNew.getRenListDetail(getStr(), new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                dialog.dismiss();
                final Ren_resignDetailBean ren_resignDetailBean = MyGson.getInstance().fromJson(data, Ren_resignDetailBean.class);
                if (ren_resignDetailBean.getType().equals("1")) {

                } else {
                    ToastUtil.showToast(Ren_resignDetailActivity.this, ren_resignDetailBean.getContent());
                }

            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
                dialog.dismiss();
                ToastUtil.showToast(getApplicationContext(), msg);
            }
        });

    }

    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle("设计方案详情");
        dialog = new LoadingDialog(Ren_resignDetailActivity.this, 0);
    }

    private void createview() {
        Ren_ResginDetailItem ren_resginDetailItem = new Ren_ResginDetailItem(this);
        ly_all.addView(ren_resginDetailItem);
    }

    private String getStr() {

        return getIntent().getStringExtra("Id");
    }
}
