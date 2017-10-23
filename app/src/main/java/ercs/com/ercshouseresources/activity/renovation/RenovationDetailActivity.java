package ercs.com.ercshouseresources.activity.renovation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.activity.service.MapActivity;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.bean.RenovaDetailBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.newbean.LoginBean;
import ercs.com.ercshouseresources.util.CloseActivityClass;
import ercs.com.ercshouseresources.util.NetWorkUtil;
import ercs.com.ercshouseresources.util.OtherUitl;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.util.imageUtil.GlideUtil;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;
import ercs.com.ercshouseresources.view.item.RenovatinDetailItem;

import static ercs.com.ercshouseresources.base.BaseApplication.context;

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
    private List<RenovatinDetailItem> viewList = new ArrayList<>();//view数组
    private RenovaDetailBean renovaDetailBean;
    private String JsonData = "";
    private String DecorationCompanyId = "";

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
        NetHelperNew.getRenovationListDetail(getId(), new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                JsonData = data;
                dialog.dismiss();
                renovaDetailBean = MyGson.getInstance().fromJson(data, RenovaDetailBean.class);
                if (renovaDetailBean.getType().equals("1")) {
                    createview(renovaDetailBean);
                    loadpager(renovaDetailBean.getData().getDecorationCasePartList());
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
     * 点击事件的处理
     *
     * @param view
     */
    @OnClick({R.id.frame_commissionexplain, R.id.fr_recrule, R.id.fr_ad, R.id.ly_companyIntro, R.id.frame_address, R.id.btn_reportingclients, R.id.fr_setcycle, R.id.fram_callp1, R.id.fram_callp2, R.id.frame_design})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.frame_commissionexplain://佣金说明
                CommissionDesActivity.start(RenovationDetailActivity.this, renovaDetailBean.getData().getBaseInfo().getCommissionAccount(), renovaDetailBean.getData().getImagePath());
                break;
            case R.id.fr_recrule://推荐规则
                Ren_RecRuleActivity.start(RenovationDetailActivity.this, renovaDetailBean.getData().getBaseInfo().getFilingRules(),
                        renovaDetailBean.getData().getBaseInfo().getBandSawRules(), renovaDetailBean.getData().getBaseInfo().getTransactionRules());
                break;
            case R.id.fr_ad://成交奖励
                Ren_ClosingBonusActivity.start(RenovationDetailActivity.this, renovaDetailBean.getData().getBaseInfo().getAwardDescription());
                break;
            case R.id.fr_setcycle://结算周期
                Ren_SettlementCycleActivity.start(RenovationDetailActivity.this, renovaDetailBean.getData().getBaseInfo().getCommissionAccount());
                break;
            case R.id.ly_companyIntro://公司简介
                Ren_companyIntroActivity.start(RenovationDetailActivity.this, renovaDetailBean.getData().getBaseInfo().getDecorationCompanyInfo(), renovaDetailBean.getData().getImagePath());
                break;
            case R.id.frame_address://地址
                if (renovaDetailBean.getData().getStoreList().size() > 0) {
                    MapActivity.start(this, Double.valueOf(renovaDetailBean.getData().getStoreList().get(0).getY()), Double.valueOf(renovaDetailBean.getData().getStoreList().get(0).getX()), renovaDetailBean.getData().getStoreList().get(0).getAddress());
                } else {
                    ToastUtil.showToast(this, "没有地址信息");
                }

                break;
            case R.id.btn_reportingclients://报备客户
                if (!"".equals(JsonData))
                    Ren_ReportingClientsActivity.start(RenovationDetailActivity.this, JsonData);
                break;
            case R.id.fram_callp1://业务电话
                if (!tv_phone.getText().toString().equals(""))
                    OtherUitl.callPage(context, tv_phone.getText().toString());
                else
                    ToastUtil.showToast(this, "没有发现该业务负责人的电话!");
                break;
            case R.id.fram_callp2://咨询业务电话
                if (!tv_ywPhone.getText().toString().equals(""))
                    OtherUitl.callPage(context, tv_ywPhone.getText().toString());
                else
                    ToastUtil.showToast(this, "没有发现该咨询业务员的电话!");
                break;
            case R.id.frame_design://设计方案
                if (DecorationCompanyId.length() > 0)
                    Ren_DesignSchemeActivity.start(RenovationDetailActivity.this, DecorationCompanyId);
                else
                    ToastUtil.showToast(this, "没有发现装修公司的Id");
                break;
        }
    }

    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle("装修");
        dialog = new LoadingDialog(RenovationDetailActivity.this, 0);
//        iv_photo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(RenovationDetailActivity.this, Ren_OrderActivity.class));
//            }
//        });
    }

    private void createview(RenovaDetailBean renovaDetailBean) {
        if (renovaDetailBean.getData().getStoreList().size() > 0) {
            DecorationCompanyId = renovaDetailBean.getData().getStoreList().get(0).getDecorationCompanyID();
            tv_title.setText(renovaDetailBean.getData().getStoreList().get(0).getName());
            tv_address.setText(renovaDetailBean.getData().getStoreList().get(0).getAddress());
        }
        tv_comdes.setText(renovaDetailBean.getData().getBaseInfo().getBrokerage());
        tv_closingbonus.setText(renovaDetailBean.getData().getBaseInfo().getAwardDescription());
        tv_count.setText("(" + renovaDetailBean.getData().getCaseCount() + ")");
        tv_intro.setText(renovaDetailBean.getData().getBaseInfo().getDecorationCompanyInfo());
        tv_name.setText(renovaDetailBean.getData().getStaffName());
        tv_phone.setText(renovaDetailBean.getData().getStaffPhone());
        List<LoginBean.DataBean.StaffListBean> list = BaseApplication.loginBean.getData().getStaffList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getModuleID().equals("4")) {
                tv_ywName.setText(list.get(i).getName());
                tv_ywPhone.setText(list.get(i).getPhone());
                break;
            }
        }
        GlideUtil.loadImage(RenovationDetailActivity.this, NetHelperNew.URL + renovaDetailBean.getData().getImagePath(), iv_photo, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
    }

    private String getId() {
        return getIntent().getStringExtra("id");
    }

    /**
     * 初始化viewPager
     *
     * @param list
     */
    private void loadpager(final List<RenovaDetailBean.DataBean.DecorationCasePartListBean> list) {
        for (int i = 0; i < list.size(); i++) {
            RenovatinDetailItem renovatinDetailItem = new RenovatinDetailItem(this, list.get(i).getCaseImagePath(), list.get(i).getName(), list.get(i).getContent());
            viewList.add(renovatinDetailItem);
            final int j = i;
            renovatinDetailItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ren_resignDetailActivity.start(RenovationDetailActivity.this, list.get(j).getId());
                }
            });
        }
        viewpager.setAdapter(new MyViewPagerAdapter(viewList));
    }

    public class MyViewPagerAdapter extends PagerAdapter {
        private List<RenovatinDetailItem> mListViews;

        public MyViewPagerAdapter(List<RenovatinDetailItem> mListViews) {
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
