package ercs.com.ercshouseresources.activity.service;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
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
import ercs.com.ercshouseresources.util.CloseActivityClass;
import ercs.com.ercshouseresources.util.DisplayUtil;
import ercs.com.ercshouseresources.util.OtherUitl;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.view.ObservableScrollView;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;
import ercs.com.ercshouseresources.view.item.NewHouseItem;
import ercs.com.ercshouseresources.view.item.PicItem;


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
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.frame)
    FrameLayout frame;
    //    @BindView(R.id.banner)
//    XBanner xBanner;
    @BindView(R.id.ly_newhouse)
    LinearLayout ly_newhouse;
    @BindView(R.id.tv_num)
    TextView tv_num;
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
        initview();
        getData();
        if (!CloseActivityClass.activityList.contains(this)) {
            CloseActivityClass.activityList.add(this);
        }
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
            ly_newhouse.addView(new NewHouseItem(this, newHouseDetailBean.getData().getHouseTypeList().get(i), JsonData, i+ ""));
        }
    }

    /**
     * 点击事件的处理
     *
     * @param view
     */
    @OnClick({R.id.iv_left, R.id.frame_commissionexplain, R.id.btn_reportingclients, R.id.ll_propertydetail, R.id.fr_recrule, R.id.fr_ad, R.id.fr_setcycle, R.id.ly_sale, R.id.ly_dt, R.id.fr_address, R.id.fr_zyfw, R.id.ly_callphone})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left://返回点击事件
                finish();
                break;
            case R.id.ly_callphone://咨询业务员电话
                OtherUitl.callPage(NewHouseDetailActivity.this, BaseApplication.loginBean.getData().getStaffList().get(0).getPhone());
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
                break;
            case R.id.ly_sale://楼盘卖点
                String str1 = newHouseDetailBean.getData().getBaseInfo().getPriceAdvantage();
                String str2 = newHouseDetailBean.getData().getBaseInfo().getHouseTypeArea();
                String str3 = newHouseDetailBean.getData().getBaseInfo().getLivingFacilities();
                String str4 = newHouseDetailBean.getData().getBaseInfo().getSchoolDistrict();
                String str5 = newHouseDetailBean.getData().getBaseInfo().getTransportation();
                String str6 = newHouseDetailBean.getData().getBaseInfo().getRegionalDevelopment();
                String str7 = newHouseDetailBean.getData().getBaseInfo().getCharacteristic();
                String str8 = newHouseDetailBean.getData().getBaseInfo().getBrandAdvantage();
                String str9 = newHouseDetailBean.getData().getBaseInfo().getHaveProductComparison();
                ContentListActivity.start(NewHouseDetailActivity.this, str1, str2, str3, str4, str5, str6, str7, str8, str9);
                break;
            case R.id.ly_dt://返回点击事件
                DynamicActivity.start(NewHouseDetailActivity.this, newHouseDetailBean.getData().getBaseInfo().getBuildingID());
                break;
            case R.id.fr_address://地址点击事件
                MapActivity.start(this, Double.valueOf(newHouseDetailBean.getData().getBaseInfo().getY()), Double.valueOf(newHouseDetailBean.getData().getBaseInfo().getX()), newHouseDetailBean.getData().getBaseInfo().getAddress());

                break;
            case R.id.fr_zyfw://置业顾问
                ProConActivity.start(this, newHouseDetailBean.getData().getPropertyConsultantList());

                break;
        }
    }

    /**
     * 设置首页广告页
     */
    private void setbanner() {
        List<PicItem> list = new ArrayList<>();
        for (int i = 0; i < newHouseDetailBean.getData().getImageList().size(); i++) {
            PicItem picItem = new PicItem(NewHouseDetailActivity.this, newHouseDetailBean.getData().getImageList().get(i));
            list.add(picItem);
        }
        tv_num.setText(1 + "/" + list.size());
        viewpager.setAdapter(new MyViewPagerAdapter(list));
        viewpager.setCurrentItem(0);
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tv_num.setText((position + 1) + "/" + newHouseDetailBean.getData().getImageList().size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 初始化
     */
    private void initview() {
        viewpager.setLayoutParams(new FrameLayout.LayoutParams(DisplayUtil.getWidthPixels(NewHouseDetailActivity.this), DisplayUtil.getWidthPixels(NewHouseDetailActivity.this) * 2 / 3));
        dialog = new LoadingDialog(NewHouseDetailActivity.this, 0);
        scrollview.setScrollViewListener(NewHouseDetailActivity.this);
        tv_title.setText(getName());
        // tv_num.setBackgroundColor(Color.argb((int) 70, 0, 0, 0));//AGB由相关工具获得，或者美工提供
        List<LoginBean.DataBean.StaffListBean> list = BaseApplication.loginBean.getData().getStaffList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getModuleID().equals("1")) {
                tv_ywName.setText(list.get(i).getName());
                tv_ywPhone.setText(list.get(i).getPhone());
                break;
            }
        }
        tv_title.setBackgroundColor(Color.argb((int) 70, 0, 0, 0));//AGB由相关工具获得，或者美工提供
    }

    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        if (y <= 20) {
            tv_title.setBackgroundColor(Color.argb((int) 70, 0, 0, 0));//AGB由相关工具获得，或者美工提供
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
                    setbanner();
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

    public class MyViewPagerAdapter extends PagerAdapter {
        private List<PicItem> mListViews;

        public MyViewPagerAdapter(List<PicItem> mListViews) {
            this.mListViews = mListViews;//构造方法，参数是我们的页卡，这样比较方便。
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mListViews.get(position));//删除页卡
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {  //这个方法用来实例化页卡
            container.addView(mListViews.get(position), 0);//添加页卡

            return mListViews.get(position);
        }

        @Override
        public int getCount() {
            return mListViews.size();//返回页卡的数量
        }


        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;//官方提示这样写
        }

    }
}
