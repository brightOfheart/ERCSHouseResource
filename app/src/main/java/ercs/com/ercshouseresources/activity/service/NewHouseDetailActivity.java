package ercs.com.ercshouseresources.activity.service;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.bean.NewHouseDetailBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.newbean.LoginBean;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.view.CustomBanner;
import ercs.com.ercshouseresources.view.ObservableScrollView;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;
import ercs.com.ercshouseresources.view.item.NewHouseItem;

/**
 * Created by Administrator on 2017/7/24.
 * 新房详情页
 */

public class NewHouseDetailActivity extends BaseActivity implements ObservableScrollView.ScrollViewListener {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_ywName)
    TextView tv_ywName;//业务员的姓名
    @BindView(R.id.tv_ywPhone)
    TextView tv_ywPhone;//业务员的电话
    @BindView(R.id.tv_subTitle)
    TextView tv_subTitle;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.tv_detail)
    TextView tv_detail;
    @BindView(R.id.tv_comdes)
    TextView tv_comdes;
    @BindView(R.id.tv_closingbonus)
    TextView tv_closingbonus;
    @BindView(R.id.tv_houserecom)
    TextView tv_houserecom;
    @BindView(R.id.tv_subaddress)
    TextView tv_subaddress;
    @BindView(R.id.scrollview)
    ObservableScrollView scrollview;
    @BindView(R.id.banner)
    XBanner xBanner;
    @BindView(R.id.ly_newhouse)
    LinearLayout ly_newhouse;
    private String JsonData = "";
    private LoadingDialog dialog;
    private NewHouseDetailBean newHouseDetailBean;

    public static void start(Activity mactivity, String BuildingID, String UserID, String name) {
        Intent intent = new Intent(mactivity, NewHouseDetailActivity.class);
        intent.putExtra("BuildingID", BuildingID);
        intent.putExtra("UserID", UserID);
        intent.putExtra("name", name);
        mactivity.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newhousedetailactivity);
        ButterKnife.bind(this);
        setbanner();
        initview();
        getData();
    }

    /**
     * 显示网络数据
     */
    private void showData(NewHouseDetailBean newHouseDetailBean) {
        tv_subTitle.setText(newHouseDetailBean.getData().getBaseInfo().getName());
        tv_address.setText(newHouseDetailBean.getData().getAreaName());
        tv_detail.setText(newHouseDetailBean.getData().getBaseInfo().getSellingPrice());
        tv_comdes.setText(newHouseDetailBean.getData().getBaseInfo().getSellingBrokerage());
        if (newHouseDetailBean.getData().getBaseInfo().getAwardDescription() != null)
            tv_closingbonus.setText(newHouseDetailBean.getData().getBaseInfo().getAwardDescription());
        else
            tv_closingbonus.setText("暂无数据");
        tv_houserecom.setText("户型推荐(" + newHouseDetailBean.getData().getHouseTypeList().size() + "个)");
        tv_subaddress.setText(newHouseDetailBean.getData().getBaseInfo().getAddress());
        for (int i = 0; i < newHouseDetailBean.getData().getHouseTypeList().size(); i++) {
            ly_newhouse.addView(new NewHouseItem(this, newHouseDetailBean.getData().getHouseTypeList().get(i), JsonData, i + 1 + ""));
        }
    }

    /**
     * 点击事件的处理
     *
     * @param view
     */
    @OnClick({R.id.iv_left, R.id.iv_right, R.id.frame_commissionexplain, R.id.btn_reportingclients, R.id.ll_propertydetail, R.id.fr_recrule, R.id.fr_ad, R.id.fr_setcycle, R.id.ly_sale,R.id.ly_dt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left://返回点击事件

                break;
            case R.id.iv_right://标题栏顶部右侧点击事件

                break;
            case R.id.frame_commissionexplain://佣金说明点击事件
                if (!"".equals(JsonData))
                    CommissionExplainActivity.start(NewHouseDetailActivity.this, JsonData);
                break;
            case R.id.btn_reportingclients://报备客户点击事件
                if (!"".equals(JsonData))
                    ReportingClientsActivity.start(NewHouseDetailActivity.this, JsonData);
                break;
            case R.id.ll_propertydetail://楼盘详情点击事件
                if (!"".equals(JsonData))
                    PropertyDetailActivity.start(NewHouseDetailActivity.this, JsonData);
                break;
            case R.id.fr_recrule://推荐规则
                RecRuleActivity.start(NewHouseDetailActivity.this, newHouseDetailBean.getData().getBaseInfo().getFilingRules(),
                        newHouseDetailBean.getData().getBaseInfo().getBandSawRules(), newHouseDetailBean.getData().getBaseInfo().getTransactionRules());
                break;
            case R.id.fr_ad://成交奖励
                ClosingBonusActivity.start(NewHouseDetailActivity.this, newHouseDetailBean.getData().getBaseInfo().getAwardDescription());

                break;
            case R.id.fr_setcycle://结算周期
                SettlementCycleActivity.start(NewHouseDetailActivity.this, newHouseDetailBean.getData().getBaseInfo().getCommissionAccount());
            case R.id.ly_sale://楼盘卖点
                String str1 = newHouseDetailBean.getData().getBaseInfo().getPriceAdvantage();
                String str2 = newHouseDetailBean.getData().getBaseInfo().getHouseTypeArea();
                String str3 = newHouseDetailBean.getData().getBaseInfo().getLivingFacilities();
                String str4= newHouseDetailBean.getData().getBaseInfo().getSchoolDistrict();
                String str5= newHouseDetailBean.getData().getBaseInfo().getTransportation();
                String str6= newHouseDetailBean.getData().getBaseInfo().getRegionalDevelopment();
                String str7= newHouseDetailBean.getData().getBaseInfo().getCharacteristic();
                String str8= newHouseDetailBean.getData().getBaseInfo().getBrandAdvantage();
                String str9= newHouseDetailBean.getData().getBaseInfo().getHaveProductComparison();
                ContentListActivity.start(NewHouseDetailActivity.this,str1,str2,str3,str4,str5,str6,str7,str8,str9);
                break;
            case R.id.ly_dt://返回点击事件

                DynamicActivity.start(NewHouseDetailActivity.this,newHouseDetailBean.getData().getBaseInfo().getBuildingID());
                break;
        }
    }

    /**
     * 设置首页广告页
     */
    private void setbanner() {
        final List<String> imgesUrl = new ArrayList<>();
        imgesUrl.add("http://imageprocess.yitos.net/images/public/20160906/1291473163104906.jpg");
        imgesUrl.add("http://imageprocess.yitos.net/images/public/20160910/99381473502384338.jpg");
        imgesUrl.add("http://imageprocess.yitos.net/images/public/20160910/77991473496077677.jpg");
        imgesUrl.add("http://imageprocess.yitos.net/images/public/20160906/1291473163104906.jpg");
        new CustomBanner(this, xBanner, imgesUrl);

    }

    /**
     * 初始化
     */
    private void initview() {
        dialog = new LoadingDialog(NewHouseDetailActivity.this, 0);
        scrollview.setScrollViewListener(NewHouseDetailActivity.this);
        tv_title.setText(getName());
        List<LoginBean.DataBean.StaffListBean> list = BaseApplication.loginBean.getData().getStaffList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getModuleID().equals("1")) {
                tv_ywName.setText(list.get(i).getName());
                tv_ywPhone.setText(list.get(i).getPhone());
                break;
            }
        }
    }

    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        if (y <= 20) {
            tv_title.setBackgroundColor(Color.argb((int) 255, 66, 69, 82));//AGB由相关工具获得，或者美工提供
        } else if (y > 20 && y <= 300) {
            float scale = (float) y / 300;
            float alpha = (255 * scale);
            // 只是layout背景透明(仿知乎滑动效果)
            tv_title.setBackgroundColor(Color.argb((int) alpha, 24, 178, 148));
        } else {
            tv_title.setBackgroundColor(Color.argb((int) 255, 24, 178, 148));
        }

    }

    /**
     * 获取网络数据
     */
    private void getData() {
        dialog.show();
        NetHelperNew.getHouseDetail(getBuildingID(), getUserID(), new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                dialog.dismiss();
                newHouseDetailBean = MyGson.getInstance().fromJson(data, NewHouseDetailBean.class);
                if (newHouseDetailBean.getType().equals("1")) {
                    JsonData = data;
                    showData(newHouseDetailBean);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showToast(getApplicationContext(), newHouseDetailBean.getContent());
                    }
                });
            }

            @Override
            public void onError(String msg) {
                dialog.dismiss();
                super.onError(msg);
            }
        });
    }

    private String getBuildingID() {
        return getIntent().getStringExtra("BuildingID");
    }

    private String getUserID() {
        return getIntent().getStringExtra("UserID");
    }

    private String getName() {
        return getIntent().getStringExtra("name");
    }
}
