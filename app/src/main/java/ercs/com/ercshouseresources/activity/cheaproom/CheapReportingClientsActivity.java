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
import ercs.com.ercshouseresources.activity.service.AddClientActivity;
import ercs.com.ercshouseresources.adapter.CheapReportingClientsAdapter;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.bean.CommissionExplainBean;
import ercs.com.ercshouseresources.bean.CustomersListBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.CloseActivityClass;
import ercs.com.ercshouseresources.util.NetWorkUtil;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;

/**
 * Created by Administrator on 2017/8/8.
 * 低价房报备客户
 */

public class CheapReportingClientsActivity extends BaseActivity {

    @BindView(R.id.edit_content)
    EditText edit_content;//搜索框
    @BindView(R.id.recyleview)
    LRecyclerView mRecyclerView;

    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private List<CustomersListBean.DataBean> clientListBeans;
    private CheapReportingClientsAdapter reportingClientsAdapter;//客户列表
    private LoadingDialog loadingDialog;
    private String key = "";// 关键字 “”
    private int pagenum = 1;//页数

    /**
     * 页面跳转
     */
    public static void start(Activity mactivity, String jsonString) {
        Log.i("-->", "报备客户：" + jsonString);
        Intent intent = new Intent(mactivity, CheapReportingClientsActivity.class);
        intent.putExtra("jsonString", jsonString);
        mactivity.startActivity(intent);
    }

    /**
     * 获取BuildingID
     *
     * @return
     */
    private String getBuildingID() {

        String jsonString = getIntent().getStringExtra("jsonString");
        CommissionExplainBean commissionExplainBean = MyGson.getInstance().fromJson(jsonString, CommissionExplainBean.class);

        return commissionExplainBean.getData().getBaseInfo().getId() + "";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporting_clients);
        ButterKnife.bind(this);
        initview();
        if (!CloseActivityClass.activityList.contains(this)) {
            CloseActivityClass.activityList.add(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        pagenum = 1;
        clientListBeans.clear();
        reportingClientsAdapter.notifyDataSetChanged();
        getData(pagenum, true);
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
            NetHelperNew.getCustomersList(BaseApplication.loginBean.getData().getId(), key, pageIndex + "", new HttpUtils.HttpCallback() {
                @Override
                public void onSuccess(String data) {
                    if (isLoading)
                        loadingDialog.dismiss();
                    Log.i("-->", "客户列表：" + data);
                    final CustomersListBean customersListBean = MyGson.getInstance().fromJson(data, CustomersListBean.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (customersListBean.getType() != 1)
                                ToastUtil.showToast(CheapReportingClientsActivity.this, customersListBean.getContent());

                            mRecyclerView.refreshComplete(10);
                            //更新数据
                            clientListBeans.addAll(customersListBean.getData());

                            reportingClientsAdapter.notifyDataSetChanged();
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

    @OnClick({R.id.iv_addperson, R.id.title_left})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_addperson:
                //添加客户
                startActivity(new Intent(CheapReportingClientsActivity.this, AddClientActivity.class));
                break;
            case R.id.title_left:
                finish();
                break;
        }
    }

    private void initview() {
        loadingDialog = new LoadingDialog(this, 0);
        clientListBeans = new ArrayList<>();
        reportingClientsAdapter = new CheapReportingClientsAdapter(this, clientListBeans, getBuildingID());

        lRecyclerViewAdapter = new LRecyclerViewAdapter(reportingClientsAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(lRecyclerViewAdapter);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader); //设置下拉刷新Progress的样式
        // mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);  //设置下拉刷新箭头
        //设置头部加载颜色
        mRecyclerView.setHeaderViewColor(R.color.system_color, R.color.system_color, android.R.color.white);
//设置底部加载颜色
        mRecyclerView.setFooterViewColor(R.color.system_color, R.color.system_color, android.R.color.white);


        mRecyclerView.setOnRefreshListener(new OnRefreshListener() {//下拉刷新
            @Override
            public void onRefresh() {
                pagenum = 1;
                clientListBeans.clear();
                reportingClientsAdapter.notifyDataSetChanged();
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
                    InputMethodManager inputMethodManager = (InputMethodManager) CheapReportingClientsActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (inputMethodManager.isActive()) {
                        inputMethodManager.hideSoftInputFromWindow(CheapReportingClientsActivity.this.getCurrentFocus().getWindowToken(), 0);
                    }
                    key = edit_content.getText().toString();

                    clientListBeans.clear();
                    reportingClientsAdapter.notifyDataSetChanged();
                    pagenum = 1;
                    getData(pagenum, true);
                    return true;
                }
                return false;
            }
        });


    }
}
