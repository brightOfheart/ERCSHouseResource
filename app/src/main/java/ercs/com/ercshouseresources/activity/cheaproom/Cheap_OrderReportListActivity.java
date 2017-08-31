package ercs.com.ercshouseresources.activity.cheaproom;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
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
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.adapter.CheapOrderReportListAdapter;
import ercs.com.ercshouseresources.bean.OrderReportListBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.CloseActivityClass;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;

/**
 * Created by Administrator on 2017/8/22.
 * 低价房报备订单列表
 */

public class Cheap_OrderReportListActivity extends BaseActivity {

    @BindView(R.id.recyleview)
    LRecyclerView recyleview;

    private List<OrderReportListBean.DataBean> dataBeanList;
    private LoadingDialog loadingDialog;
    private int pageNum = 1;
    private CheapOrderReportListAdapter orderReportListAdapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_report_list);
        ButterKnife.bind(this);
        initTitle();
        initRecycleView();
        downLoad(pageNum, true);
        if (!CloseActivityClass.activityList.contains(this)) {
            CloseActivityClass.activityList.add(this);
        }
    }

    /**
     * 加载网络数据
     *
     * @param page
     */
    private void downLoad(int page, final boolean isLoading) {
        if (isLoading)
            loadingDialog.show();
        NetHelperNew.getCheapCustomersList(page + "", new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                if (isLoading)
                    loadingDialog.dismiss();
                final OrderReportListBean orderReportListBean = MyGson.getInstance().fromJson(data, OrderReportListBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recyleview.refreshComplete(10);
                        if (orderReportListBean.getType() == 1) {

                            dataBeanList.addAll(orderReportListBean.getData());
                            orderReportListAdapter.notifyDataSetChanged();
                            pageNum++;
                        }
                        else
                        {
                            ToastUtil.showToast(Cheap_OrderReportListActivity.this, orderReportListBean.getContent());
                        }

                    }
                });
            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
                if (isLoading)
                    loadingDialog.dismiss();
                recyleview.refreshComplete(10);
            }
        });
    }

    private void initRecycleView() {
        dataBeanList = new ArrayList<>();
        orderReportListAdapter = new CheapOrderReportListAdapter(this, dataBeanList);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(orderReportListAdapter);
        recyleview.setLayoutManager(new LinearLayoutManager(this));
        recyleview.setAdapter(lRecyclerViewAdapter);
        recyleview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);

        //设置头部加载颜色
        recyleview.setHeaderViewColor(R.color.system_color, R.color.system_color, android.R.color.transparent);
//设置底部加载颜色
        recyleview.setFooterViewColor(R.color.system_color, R.color.system_color, android.R.color.transparent);

        //分割线
        DividerDecoration divider = new DividerDecoration.Builder(this)
                .setHeight(R.dimen.d_16)
                .setPadding(R.dimen.d_0)
                .setColorResource(R.color.transparent)
                .build();
        recyleview.addItemDecoration(divider);
        recyleview.setOnRefreshListener(new OnRefreshListener() {//下拉刷新
            @Override
            public void onRefresh() {
                pageNum = 1;
                dataBeanList.clear();
                orderReportListAdapter.notifyDataSetChanged();
                downLoad(pageNum, false);
            }
        });

        recyleview.setOnLoadMoreListener(new OnLoadMoreListener() {//加载更多
            @Override
            public void onLoadMore() {
                downLoad(pageNum, false);
            }
        });


    }

    /**
     * 初始化标题
     */
    private void initTitle() {
        TitleControl titleControl = new TitleControl(this);
        titleControl.setTitle("低价房订单");
        loadingDialog = new LoadingDialog(this, 0);
    }
}
