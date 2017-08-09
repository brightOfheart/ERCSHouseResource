package ercs.com.ercshouseresources.activity.cheaproom;

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
import ercs.com.ercshouseresources.activity.service.ClosingBonusActivity;
import ercs.com.ercshouseresources.activity.service.CommissionExplainActivity;
import ercs.com.ercshouseresources.activity.service.ContentListActivity;
import ercs.com.ercshouseresources.activity.service.DynamicActivity;
import ercs.com.ercshouseresources.activity.service.MapActivity;
import ercs.com.ercshouseresources.activity.service.NewHouseDetailActivity;
import ercs.com.ercshouseresources.activity.service.ProConActivity;
import ercs.com.ercshouseresources.activity.service.PropertyDetailActivity;
import ercs.com.ercshouseresources.activity.service.RecRuleActivity;
import ercs.com.ercshouseresources.activity.service.ReportingClientsActivity;
import ercs.com.ercshouseresources.activity.service.SettlementCycleActivity;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.bean.CheapRoomDetailBean;
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
 * Created by Administrator on 2017/8/8.
 */

public class CheapRoomDetailActivity  extends BaseActivity implements ObservableScrollView.ScrollViewListener {
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
    @BindView(R.id.tv_price)
    TextView tv_price;
    @BindView(R.id.tv_subprice)
    TextView tv_subprice;
    @BindView(R.id.tv_area)
    TextView tv_area;
    @BindView(R.id.tv_shareprice)
    TextView tv_shareprice;
    @BindView(R.id.tv_loan)
    TextView tv_loan;
    @BindView(R.id.tv_comdes)
    TextView tv_comdes;
    @BindView(R.id.tv_closingbonus)
    TextView tv_closingbonus;
    /**
     * 房源描述
     */
    @BindView(R.id.tv_1)
    TextView tv_1;
    @BindView(R.id.tv_2)
    TextView tv_2;
    @BindView(R.id.tv_3)
    TextView tv_3;
    @BindView(R.id.tv_4)
    TextView tv_4;
    @BindView(R.id.tv_5)
    TextView tv_5;
    @BindView(R.id.tv_6)
    TextView tv_6;
    @BindView(R.id.tv_7)
    TextView tv_7;
    @BindView(R.id.tv_8)
    TextView tv_8;
    @BindView(R.id.tv_lookmore)
    TextView tv_lookmore;
    @BindView(R.id.tv_loanrule)
    TextView tv_loanrule;
    @BindView(R.id.tv_subaddress)
    TextView tv_subaddress;

    @BindView(R.id.scrollview)
    ObservableScrollView scrollview;
    @BindView(R.id.banner)
    XBanner xBanner;
    private String JsonData = "";
    private LoadingDialog dialog;
    private CheapRoomDetailBean newHouseDetailBean;

    public static void start(Activity mactivity, String ID,String name) {
        Intent intent = new Intent(mactivity, CheapRoomDetailActivity.class);
        intent.putExtra("ID", ID);
        intent.putExtra("NAME", name);
        mactivity.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cheaproomdetailactivity);
        ButterKnife.bind(this);
        setbanner();
        initview();
        getData();
    }

    /**
     * 显示网络数据
     */
    private void showData(CheapRoomDetailBean newHouseDetailBean) {
        tv_subTitle.setText(newHouseDetailBean.getData().getModel().getBuildingName());
        tv_address.setText(newHouseDetailBean.getData().getModel().getBuildingAddress());
        tv_price.setText(newHouseDetailBean.getData().getModel().getLowPrice());
        tv_subprice.setText(newHouseDetailBean.getData().getModel().getCostPrice());
        tv_area.setText(newHouseDetailBean.getData().getModel().getArea());
        tv_shareprice.setText(newHouseDetailBean.getData().getModel().getArea());
        tv_loan.setText(newHouseDetailBean.getData().getModel().getIsLoan());
        tv_comdes.setText(newHouseDetailBean.getData().getModel().getBrokerage());
        tv_closingbonus.setText(newHouseDetailBean.getData().getModel().getAwardDescription());
        tv_1.setText(newHouseDetailBean.getData().getModel().getResidenceType());
        tv_2.setText(newHouseDetailBean.getData().getModel().getHouseNumber());
        tv_3.setText(newHouseDetailBean.getData().getModel().getDecorationCondition());
        tv_4.setText(newHouseDetailBean.getData().getBuildingsType());
        tv_5.setText(newHouseDetailBean.getData().getModel().getStorey());
        tv_6.setText(newHouseDetailBean.getData().getModel().getCreateYear());
        tv_7.setText(newHouseDetailBean.getData().getModel().getPropertyRight());
        tv_8.setText(newHouseDetailBean.getData().getModel().getOrientations());
        if (newHouseDetailBean.getData().getModel().getAwardDescription() != null)
            tv_closingbonus.setText(newHouseDetailBean.getData().getModel().getAwardDescription());
        else
            tv_closingbonus.setText("暂无数据");
        tv_loanrule.setText(newHouseDetailBean.getData().getModel().getRules());
        tv_subaddress.setText(newHouseDetailBean.getData().getModel().getBuildingAddress());

    }

    /**
     * 点击事件的处理
     *
     * @param view
     */
    @OnClick({R.id.iv_left, R.id.iv_right, R.id.frame_commissionexplain, R.id.btn_reportingclients, R.id.ll_propertydetail, R.id.fr_recrule, R.id.fr_ad, R.id.fr_setcycle, R.id.ly_sale,R.id.ly_dt,R.id.fr_address,R.id.fr_zyfw,R.id.fr_locanrule})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left://返回点击事件

                break;
            case R.id.iv_right://标题栏顶部右侧点击事件

                break;
            case R.id.frame_commissionexplain://佣金说明点击事件
                CommissionDescription.start(CheapRoomDetailActivity.this,newHouseDetailBean.getData().getModel().getBrokerage());
                break;
            case R.id.btn_reportingclients://报备客户点击事件
                if (!"".equals(JsonData))
                    CheapReportingClientsActivity.start(CheapRoomDetailActivity.this, JsonData);
                break;
            case R.id.ll_propertydetail://小区详情点击事件
                if (!"".equals(JsonData))
                    ResidentialDetailActivity.start(CheapRoomDetailActivity.this, JsonData);
                break;
            case R.id.fr_recrule://低价房规则
                CheapRoomRuleActivity.start(CheapRoomDetailActivity.this,newHouseDetailBean.getData().getModel().getRules());
                break;
            case R.id.fr_ad://成交奖励
                ClosingBonusActivity.start(CheapRoomDetailActivity.this, newHouseDetailBean.getData().getModel().getAwardDescription());

                break;
            case R.id.fr_setcycle://结算周期
                SettlementCycleActivity.start(CheapRoomDetailActivity.this, newHouseDetailBean.getData().getModel().getCommissionAccount());
                break;
            case R.id.fr_locanrule://贷款规则
                LoanRuleActivity.start(CheapRoomDetailActivity.this,newHouseDetailBean.getData().getModel().getLoanRules());
                break;
            case R.id.ly_dt://返回点击事件
                CheapDynamicActivity.start(CheapRoomDetailActivity.this,newHouseDetailBean.getData().getModel().getId());
                break;
            case R.id.fr_address://地址点击事件
                MapActivity.start(this,Double.valueOf(newHouseDetailBean.getData().getModel().getY()),Double.valueOf(newHouseDetailBean.getData().getModel().getX()));

                break;
            case R.id.fr_zyfw://地址点击事件
                //ProConActivity.start(this,newHouseDetailBean.getData().getPropertyConsultantList());

                break;
            case R.id.ly_sale://小区卖点
                String str1 = newHouseDetailBean.getData().getModel().getPriceAdvantage();
                String str2 = newHouseDetailBean.getData().getModel().getHouseTypeArea();
                String str3 = newHouseDetailBean.getData().getModel().getLivingFacilities();
                String str4= newHouseDetailBean.getData().getModel().getSchoolDistrict();
                String str5= newHouseDetailBean.getData().getModel().getTransportation();
                String str6= newHouseDetailBean.getData().getModel().getRegionalDevelopment();
                String str7= newHouseDetailBean.getData().getModel().getCharacteristic();
                String str8= newHouseDetailBean.getData().getModel().getBrandAdvantage();
                String str9= newHouseDetailBean.getData().getModel().getHaveProductComparison();
                ResidePayActivity.start(CheapRoomDetailActivity.this,str1,str2,str3,str4,str5,str6,str7,str8,str9);

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
        dialog = new LoadingDialog(CheapRoomDetailActivity.this, 0);
        scrollview.setScrollViewListener(CheapRoomDetailActivity.this);
        tv_title.setText(getNAME());
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
        NetHelperNew.getCheapRoomHouseDetail(getID(),new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                dialog.dismiss();
                newHouseDetailBean = MyGson.getInstance().fromJson(data, CheapRoomDetailBean.class);
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

    private String getID() {
        return getIntent().getStringExtra("ID");
    }
    private String getNAME() {
        return getIntent().getStringExtra("NAME");
    }

}
