package ercs.com.ercshouseresources.activity.cheaproom;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.adapter.CheapRoomAdapter;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.bean.BannerBean;
import ercs.com.ercshouseresources.bean.CheapRoomListBean;
import ercs.com.ercshouseresources.bean.HouseBean;
import ercs.com.ercshouseresources.bean.NewHouseAreaBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.CloseActivityClass;
import ercs.com.ercshouseresources.util.NetWorkUtil;
import ercs.com.ercshouseresources.util.SPUtil;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.view.HorizontalScorllTv;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;
import ercs.com.ercshouseresources.view.dialog.PicDialog;
import ercs.com.ercshouseresources.view.popupwindow.MorePop;
import ercs.com.ercshouseresources.view.popupwindow.NewHouseAreaSelectPop;
import ercs.com.ercshouseresources.view.popupwindow.PricePop;

/**
 * Created by Administrator on 2017/8/7.
 * 低价房列表
 */
public class CheapRoomListActivity extends BaseActivity {
    @BindView(R.id.edit_content)
    EditText edit_content;//搜索框
    @BindView(R.id.recyleview)
    LRecyclerView mRecyclerView;
    @BindView(R.id.view_line)
    View view_line;
    @BindView(R.id.tv_city)
    TextView tv_city;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private List<CheapRoomListBean.DataBean> houseListBeans;
    private CheapRoomAdapter houseAdapter;//房源列表
    private LoadingDialog loadingDialog;
    private String key = "";// 关键字 “”
    private int pagenum = 1;//页数
    private String HouseTypeVal = "";//房源类型
    private String BuildingsTypeVal;
    private String DecorationConditionVal;
    private String AreaMax;
    private String AreaMin;
    private String StoreyMin;
    private String StoreyMax;
    private String CreateYearMin;
    private String CreateYearMax;
    private String PriceMin;
    private String PriceMax;
    private int AreaID = 0;//区域类型
    private HousePop housePop;//房源类型
    private NewHouseAreaSelectPop newHouseAreaSelectPop;//区域类型
    private PricePop pricePop;
    private MorePop morePop;
    @BindView(R.id.ly_top)
    LinearLayout ly_top;
    private String Imagepath = "";
    private String Imagepathid = "";
    private SPUtil spUtil;

    /**
     * 页面跳转
     */
    public static void start(Activity mactivity, String HouseTypeVal) {
        Intent intent = new Intent(mactivity, CheapRoomListActivity.class);
        intent.putExtra("HouseTypeVal", HouseTypeVal);
        mactivity.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheaproomlist);
        ButterKnife.bind(this);
        initview();
        BuildingsTypeVal = getIntent().getStringExtra("HouseTypeVal");
        downLoadArea();
        if (!CloseActivityClass.activityList.contains(this)) {
            CloseActivityClass.activityList.add(this);
        }
        if (NetWorkUtil.check(getApplicationContext())) {
            getBanner();
            getData(pagenum, true);
        }
    }

    private void getBanner() {
        NetHelperNew.getBanner("2", new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                final BannerBean bannerBean = MyGson.getInstance().fromJson(data, BannerBean.class);
                if (bannerBean.getType().equals("1")) {
                    List<String> list = new ArrayList<String>();
                    List<String> listid = new ArrayList<String>();
                    for (int i = 0; i < bannerBean.getData().size(); i++) {
                        if (bannerBean.getData().get(i).getAdvertisementType().equals("1")) {
                            list.add(bannerBean.getData().get(i).getText() + "   ");
                            listid.add(bannerBean.getData().get(i).getDynamicID());
                        } else {
                            Imagepath = bannerBean.getData().get(i).getImagePath().get(0);
                            Imagepathid = bannerBean.getData().get(i).getDynamicID();
                        }
                    }

                    if (bannerBean.getData().size() > 0) {
                        loadBanner(list, listid);
                    } else {
                        ly_top.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
            }
        });
    }

    private void loadBanner(List<String> list, List<String> listid) {
        HorizontalScorllTv horizontalScorllTextView = new HorizontalScorllTv(CheapRoomListActivity.this, list, listid);
        ly_top.addView(horizontalScorllTextView);
        if (BaseApplication.CHEAPOPEN.equals("0")) {
            if (!Imagepath.equals("")) {
                PicDialog picDialog = new PicDialog(CheapRoomListActivity.this, R.style.mydialog, Imagepath, Imagepathid);
                picDialog.show();
                BaseApplication.CHEAPOPEN = "1";
            }

        }
    }

    /**
     * 下载区域数据
     */
    private void downLoadArea() {
        NetHelperNew.AreaList(BaseApplication.loginBean.getData().getCityID(), new HttpUtils.HttpCallback() {

            @Override
            public void onSuccess(String data) {
                Log.i("-->", "低价房区域:" + data);
                final NewHouseAreaBean newHouseAreaBean = MyGson.getInstance().fromJson(data, NewHouseAreaBean.class);
                if (1 == newHouseAreaBean.getType()) {

                    initAreaSelectPop(newHouseAreaBean.getData());
                }

            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
                Log.i("-->", "下载低价房区域信息失败" + msg);

            }
        });
        NetHelperNew.getCheapRoomContent(new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                final HouseBean houseBean = MyGson.getInstance().fromJson(data, HouseBean.class);
                if (houseBean.getType().equals("1")) {
                    initHouseLayoutSelectPop(houseBean.getData().getHouseType());
                    initMorePop(houseBean.getData());
                }
            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
            }
        });
    }


    /**
     * 初始化
     */
    private void initview() {
        if (!CloseActivityClass.activityList.contains(this)) {
            CloseActivityClass.activityList.add(this);
        }
        loadingDialog = new LoadingDialog(this, 0);
        spUtil = new SPUtil(CheapRoomListActivity.this, "fileName");
        String city = spUtil.getString(BaseApplication.CITY, "");
        tv_city.setText(city);
        houseListBeans = new ArrayList<>();
        houseAdapter = new CheapRoomAdapter(this, houseListBeans);

        lRecyclerViewAdapter = new LRecyclerViewAdapter(houseAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(lRecyclerViewAdapter);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader); //设置下拉刷新Progress的样式
        // mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);  //设置下拉刷新箭头
        //设置头部加载颜色
        mRecyclerView.setHeaderViewColor(R.color.system_color, R.color.system_color, android.R.color.white);
//设置底部加载颜色
        mRecyclerView.setFooterViewColor(R.color.system_color, R.color.system_color, android.R.color.white);

        //分割线
//        DividerDecoration divider = new DividerDecoration.Builder(this)
//                .setHeight(R.dimen.d_1)
//                .setPadding(R.dimen.d_0)
//                .setColorResource(R.color.white2)
//                .build();
//        mRecyclerView.addItemDecoration(divider);
        mRecyclerView.setOnRefreshListener(new OnRefreshListener() {//下拉刷新
            @Override
            public void onRefresh() {
                pagenum = 1;
                houseListBeans.clear();
                houseAdapter.notifyDataSetChanged();
                getData(pagenum, false);
            }
        });

        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {//加载更多
            @Override
            public void onLoadMore() {
                getData(pagenum, false);
            }
        });


        //搜索框监听
        edit_content.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {

            /*隐藏软键盘*/
                    InputMethodManager inputMethodManager = (InputMethodManager) CheapRoomListActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (inputMethodManager.isActive()) {
                        inputMethodManager.hideSoftInputFromWindow(CheapRoomListActivity.this.getCurrentFocus().getWindowToken(), 0);
                    }
                    key = edit_content.getText().toString();

                    houseListBeans.clear();
                    houseAdapter.notifyDataSetChanged();
                    pagenum = 1;
                    getData(pagenum, true);
                    return true;
                }
                return false;
            }
        });

        pricePop = new PricePop(CheapRoomListActivity.this, new PricePop.GetPriceListener() {
            @Override
            public void getPrice(String str1, String str2) {
                PriceMin = str1;
                PriceMax = str2;
                pagenum = 1;
                houseListBeans.clear();
                houseAdapter.notifyDataSetChanged();
                getData(pagenum, true);
            }
        });

    }

    /**
     * 房源类型选择pop
     */
    private void initHouseLayoutSelectPop(List<HouseBean.DataBean.HouseTypeBean> list) {
        housePop = new HousePop(this, list, new HousePop.OnSelectHouseLayoutListener() {
            @Override
            public void selectHouseLayout(String str) {
                HouseTypeVal = str;
                pagenum = 1;
                houseListBeans.clear();
                houseAdapter.notifyDataSetChanged();
                getData(pagenum, true);
            }
        });
    }

    private void initMorePop(HouseBean.DataBean Data) {
        morePop = new MorePop(CheapRoomListActivity.this, Data, new MorePop.OngetContentListener() {
            @Override
            public void getContent(String Dcv, String areo1, String aer02, String btv, String flo1, String flo2, String year1, String year2) {
                DecorationConditionVal = Dcv;
                AreaMin = areo1;
                AreaMax = aer02;
                BuildingsTypeVal = btv;
                StoreyMin = flo1;
                StoreyMax = flo2;
                CreateYearMin = year1;
                CreateYearMax = year2;
                pagenum = 1;
                houseListBeans.clear();
                houseAdapter.notifyDataSetChanged();
                getData(pagenum, true);
            }


        });
    }

    /**
     * 房源区域选择pop
     */
    private void initAreaSelectPop(List<NewHouseAreaBean.DataBean> dataBeanList) {
        newHouseAreaSelectPop = new NewHouseAreaSelectPop(this, dataBeanList, new NewHouseAreaSelectPop.OnSelectNewAreaListener() {
            @Override
            public void selectArea(int i) {
                Log.i("-->", "选择区域：" + i);
                AreaID = i;
                pagenum = 1;
                houseListBeans.clear();
                houseAdapter.notifyDataSetChanged();
                getData(pagenum, true);
            }
        });
    }

    @OnClick({R.id.title_left, R.id.ly_price, R.id.ly_area, R.id.ly_housetype, R.id.ly_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_left:
                //返回键
                finish();

                break;

            case R.id.ly_price:
                //价格
                if (pricePop != null)
                    pricePop.showAsDropDown(view_line, 0, 0);
                break;
            case R.id.ly_area:
                //选择区域
                if (newHouseAreaSelectPop != null)
                    newHouseAreaSelectPop.showAsDropDown(view_line, 0, 0);
                break;
            case R.id.ly_housetype:
                //房型
                if (housePop != null)
                    housePop.showAsDropDown(view_line, 0, 0);
                break;
            case R.id.ly_more:
                //更多
                if (morePop != null)
                    morePop.showAsDropDown(view_line, 0, 0);
                break;
        }
    }

    /**
     * 获取网络数据
     *
     * @param pageIndex 页数
     */
    private void getData(int pageIndex, final boolean isLoading) {

        if (NetWorkUtil.check(this)) {
            if (isLoading)
                loadingDialog.show();
            NetHelperNew.CheapRoomList(pageIndex + "", AreaID == 0 ? "" : AreaID + "", HouseTypeVal, BuildingsTypeVal, DecorationConditionVal, AreaMax, AreaMin, StoreyMin, StoreyMax, CreateYearMin, CreateYearMax, PriceMin, PriceMax, key, new HttpUtils.HttpCallback() {
                @Override
                public void onSuccess(String data) {
                    if (isLoading)
                        loadingDialog.dismiss();
                    Log.i("-->", "低价房列表：" + data);
                    final CheapRoomListBean cheapRoomListBean = MyGson.getInstance().fromJson(data, CheapRoomListBean.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (!cheapRoomListBean.getType().equals("1"))
                                ToastUtil.showToast(CheapRoomListActivity.this, cheapRoomListBean.getContent());

                            mRecyclerView.refreshComplete(10);
                            //更新数据
                            houseListBeans.addAll(cheapRoomListBean.getData());
                            houseAdapter.notifyDataSetChanged();
                            pagenum++;
                            if (cheapRoomListBean.getData().size() == 0) {
                                ToastUtil.showToast(CheapRoomListActivity.this, "没有更多数据了~");
                            }
                        }
                    });

                }

                @Override
                public void onError(String msg) {
                    super.onError(msg);
                    if (isLoading)
                        loadingDialog.dismiss();
                    mRecyclerView.refreshComplete(10);
                    ToastUtil.showToast(getApplicationContext(), msg);
                }
            });
        }

    }

}
