package ercs.com.ercshouseresources.activity.service;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
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
import ercs.com.ercshouseresources.adapter.NewBuildingAdapter;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.bean.HouseListBean;
import ercs.com.ercshouseresources.bean.NewHouseAreaBean;
import ercs.com.ercshouseresources.bean.NewHouseListBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelper;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.NetWorkUtil;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;
import ercs.com.ercshouseresources.view.popupwindow.BuildingTypeSelectPop;
import ercs.com.ercshouseresources.view.popupwindow.HouseLayoutSelectPop;
import ercs.com.ercshouseresources.view.popupwindow.NewHouseAreaSelectPop;

/**
 * 新房
 */
public class NewHouseActivity extends AppCompatActivity {

    @BindView(R.id.edit_content)
    EditText edit_content;//搜索框
    @BindView(R.id.recyleview)
    LRecyclerView mRecyclerView;
    @BindView(R.id.view_line)
    View view_line;
    @BindView(R.id.tv_city)
    TextView tv_city;

    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private List<NewHouseListBean.DataBean> houseListBeans;
    private NewBuildingAdapter houseAdapter;//房源列表
    private LoadingDialog loadingDialog;
    private String key = "";// 关键字 “”
    private int pagenum = 1;//页数


    private int BuildingTypeID = 0;//房源类型
    private int AreaID = 0;//区域类型

    private BuildingTypeSelectPop buildingTypeSelectPop;//房源类型
    private NewHouseAreaSelectPop newHouseAreaSelectPop;//区域类型

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_house);
        ButterKnife.bind(this);
        initview();

        getData(pagenum,true);
        initHouseLayoutSelectPop();
        downLoadArea();
    }


    /**
     *下载区域数据
     */
    private void downLoadArea() {
        NetHelperNew.AreaList(BaseApplication.loginBean.getData().getCityID(),new HttpUtils.HttpCallback() {

            @Override
            public void onSuccess(String data) {
                Log.i("-->","新房区域:"+data);
                final NewHouseAreaBean newHouseAreaBean = MyGson.getInstance().fromJson(data, NewHouseAreaBean.class);
                if (1==newHouseAreaBean.getType())
                {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            initAreaSelectPop(newHouseAreaBean.getData());
                        }
                    });
                }

            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
                Log.i("-->","下载新房区域信息失败"+msg);

            }
        });
    }


    /**
     * 初始化
     */
    private void initview() {
        loadingDialog = new LoadingDialog(this, 0);
        if(BaseApplication.loginBean.getData()!=null)
        tv_city.setText(BaseApplication.loginBean.getData().getCityName());
        houseListBeans = new ArrayList<>();
        houseAdapter = new NewBuildingAdapter(this, houseListBeans);

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
        DividerDecoration divider = new DividerDecoration.Builder(this)
                .setHeight(R.dimen.d_1)
                .setPadding(R.dimen.d_0)
                .setColorResource(R.color.white2)
                .build();
        mRecyclerView.addItemDecoration(divider);
        mRecyclerView.setOnRefreshListener(new OnRefreshListener() {//下拉刷新
            @Override
            public void onRefresh() {
                pagenum = 1;
                houseListBeans.clear();
                houseAdapter.notifyDataSetChanged();
                getData(pagenum,false);
            }
        });

        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {//加载更多
            @Override
            public void onLoadMore() {
                getData(pagenum,false);
            }
        });


        //搜索框监听
        edit_content.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {

            /*隐藏软键盘*/
                    InputMethodManager inputMethodManager = (InputMethodManager) NewHouseActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (inputMethodManager.isActive()) {
                        inputMethodManager.hideSoftInputFromWindow(NewHouseActivity.this.getCurrentFocus().getWindowToken(), 0);
                    }
                    key = edit_content.getText().toString();

                    houseListBeans.clear();
                    houseAdapter.notifyDataSetChanged();
                    pagenum=1;
                    getData(pagenum,true);
                    return true;
                }
                return false;
            }
        });


    }

    /**
     * 房源类型选择pop
     */
    private void initHouseLayoutSelectPop() {
        buildingTypeSelectPop = new BuildingTypeSelectPop(this, new BuildingTypeSelectPop.OnSelectHouseLayoutListener() {
            @Override
            public void selectHouseLayout(int i) {

                Log.i("-->","选择房型："+i);
                BuildingTypeID=i;

                pagenum=1;
                houseListBeans.clear();
                houseAdapter.notifyDataSetChanged();
                getData(pagenum,true);
            }
        });
    }

    /**
     * 房源区域选择pop
     */
    private void initAreaSelectPop(List<NewHouseAreaBean.DataBean> dataBeanList) {
        newHouseAreaSelectPop = new NewHouseAreaSelectPop(this,dataBeanList, new NewHouseAreaSelectPop.OnSelectNewAreaListener() {
            @Override
            public void selectArea(int i) {

                Log.i("-->","选择区域："+i);
                AreaID=i;

                pagenum=1;
                houseListBeans.clear();
                houseAdapter.notifyDataSetChanged();
                getData(pagenum,true);
            }
        });
    }
    @OnClick({R.id.title_left,R.id.ly_housingtype,R.id.ly_area})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_left:
                //返回键
//                finish();
                startActivity(new Intent(NewHouseActivity.this,OrderReportListActivity.class));
                break;

            case R.id.ly_housingtype:
                //房源类型
                if (buildingTypeSelectPop!=null)
                    buildingTypeSelectPop.showAsDropDown(view_line,0,0);
                break;
            case R.id.ly_area:
                //选择区域
                if (newHouseAreaSelectPop!=null)
                    newHouseAreaSelectPop.showAsDropDown(view_line,0,0);
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
            NetHelperNew.NewBuildingsList(pageIndex+"",AreaID==0?"":AreaID+"",BuildingTypeID+"",key, new HttpUtils.HttpCallback() {
                @Override
                public void onSuccess(String data) {
                    if (isLoading)
                    loadingDialog.dismiss();
                    Log.i("-->","新房源列表："+data);
                    final NewHouseListBean newHouseListBean = MyGson.getInstance().fromJson(data, NewHouseListBean.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showToast(NewHouseActivity.this, newHouseListBean.getContent());

                            mRecyclerView.refreshComplete(10);
                            //更新数据
                            houseListBeans.addAll(newHouseListBean.getData());

                            houseAdapter.notifyDataSetChanged();
                            pagenum++;
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
