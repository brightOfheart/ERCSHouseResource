package ercs.com.ercshouseresources.activity.renovation;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.activity.service.DynamicDetailActivity;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.bean.RenovaDetailBean;
import ercs.com.ercshouseresources.bean.RenovationListBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.newbean.LoginBean;
import ercs.com.ercshouseresources.util.NetWorkUtil;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.util.imageUtil.GlideUtil;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;
import ercs.com.ercshouseresources.view.item.RenovatinDetailItem;

/**
 * Created by Administrator on 2017/8/10.
 * 装修详情
 */

public class RenovationDetailActivity extends BaseActivity {
    @BindView(R.id.iv_photo)
    ImageView iv_photo;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.tv_comdes)
    TextView tv_comdes;
    @BindView(R.id.tv_closingbonus)
    TextView tv_closingbonus;
    @BindView(R.id.tv_count)
    TextView tv_count;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tv_intro)
    TextView tv_intro;
    @BindView(R.id.tv_ywName)
    TextView tv_ywName;
    @BindView(R.id.tv_ywPhone)
    TextView tv_ywPhone;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.btn_reportingclients)
    Button btn_reportingclients;
    private LoadingDialog dialog;
    private List<RenovatinDetailItem> viewList=new ArrayList<>();//view数组
    /**
     * 页面跳转
     */
    public static void start(Activity mactivity, String id) {
        Intent intent = new Intent(mactivity, RenovationDetailActivity.class);
        intent.putExtra("id", id);
        mactivity.startActivity(intent);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renovationdetail);
        ButterKnife.bind(this);
        initTitle();
        if (NetWorkUtil.check(getApplicationContext()))
            loadData();
    }


    /**
     * 加载网络数据
     */
    private void loadData() {
        dialog.show();
        NetHelperNew.getRenovationListDetail(getId(), new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                dialog.dismiss();
                final RenovaDetailBean renovaDetailBean = MyGson.getInstance().fromJson(data, RenovaDetailBean.class);
                if(renovaDetailBean.getType().equals("1"))
                {
                    createview(renovaDetailBean);
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
        t.setTitle("装修");
        dialog = new LoadingDialog(RenovationDetailActivity.this, 0);
    }

    private void createview(RenovaDetailBean renovaDetailBean)
    {   if(renovaDetailBean.getData().getStoreList().size()>0)
        tv_title.setText(renovaDetailBean.getData().getStoreList().get(0).getName());
        tv_address.setText(renovaDetailBean.getData().getStoreList().get(0).getAddress());
        tv_comdes.setText(renovaDetailBean.getData().getBaseInfo().getBrokerage());
        tv_closingbonus.setText(renovaDetailBean.getData().getBaseInfo().getAwardDescription());
        tv_count.setText("148");
        tv_intro.setText(renovaDetailBean.getData().getBaseInfo().getDecorationCompanyInfo());
        tv_name.setText(renovaDetailBean.getData().getStaffName());
        tv_phone.setText(renovaDetailBean.getData().getStaffPhone());
        List<LoginBean.DataBean.StaffListBean> list = BaseApplication.loginBean.getData().getStaffList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getModuleID().equals("1")) {
                tv_ywName.setText(list.get(i).getName());
                tv_ywPhone.setText(list.get(i).getPhone());
                break;
            }
        }
        GlideUtil.loadImage(RenovationDetailActivity.this,NetHelperNew.URL+renovaDetailBean.getData().getImagePath(),iv_photo,R.mipmap.ic_launcher,R.mipmap.ic_launcher);
    }

    private String getId()
    {
        return getIntent().getStringExtra("id");
    }

    private void loadpager()
    {

    }
}
