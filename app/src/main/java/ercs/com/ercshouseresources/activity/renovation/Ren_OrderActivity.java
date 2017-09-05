package ercs.com.ercshouseresources.activity.renovation;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.activity.cheaproom.Cheap_OrderReportListActivity;
import ercs.com.ercshouseresources.adapter.Ren_OrderAdapter;
import ercs.com.ercshouseresources.bean.Ren_OrderBean;
import ercs.com.ercshouseresources.bean.RenovationListBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.CloseActivityClass;
import ercs.com.ercshouseresources.util.NetWorkUtil;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;

/**
 * Created by Administrator on 2017/8/11.
 * 装修订单
 */

public class Ren_OrderActivity extends BaseActivity {
    @BindView(R.id.recyleview)
    LRecyclerView recyleview;
    private String SUCCESS = "1";
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private LoadingDialog dialog;
    private int PageIndex = 1;
    private Ren_OrderBean ren_orderBean;
    private List<Ren_OrderBean.DataBean> list;
    private Ren_OrderAdapter ren_orderAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_renorder);
        ButterKnife.bind(this);
        initTitle();
        if (!CloseActivityClass.activityList.contains(this)) {
            CloseActivityClass.activityList.add(this);
        }
        if (NetWorkUtil.check(getApplicationContext()))
            loadData();
    }

    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle("装修订单");
        dialog = new LoadingDialog(Ren_OrderActivity.this, 0);
    }

    private void createView() {
        ren_orderAdapter = new Ren_OrderAdapter(Ren_OrderActivity.this, this, ren_orderBean.getData());
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(ren_orderAdapter);
        recyleview.setLayoutManager(new LinearLayoutManager(this));
        //设置头部加载颜色
        recyleview.setHeaderViewColor(R.color.system_color, R.color.system_color, android.R.color.transparent);
//设置底部加载颜色
        recyleview.setFooterViewColor(R.color.system_color, R.color.system_color, android.R.color.transparent);

        recyleview.setAdapter(mLRecyclerViewAdapter);
        recyleview.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                PageIndex++;
                NetHelperNew.getDecorationPreparationOrder(PageIndex + "", "", new HttpUtils.HttpCallback() {
                    @Override
                    public void onSuccess(String data) {
                        ren_orderBean = MyGson.getInstance().fromJson(data, Ren_OrderBean.class);
                        if (ren_orderBean.getType().equals("1")) {
                            list.addAll(ren_orderBean.getData());
                            ren_orderAdapter.setListData(list);
                            mLRecyclerViewAdapter.notifyDataSetChanged();
                            if (ren_orderBean.getData().size() == 0) {
                                ToastUtil.showToast(getApplicationContext(), "没有更多数据了");
                            }
                        }
                        recyleview.refreshComplete(10);
                    }

                    @Override
                    public void onError(String msg) {
                        super.onError(msg);
                        recyleview.refreshComplete(10);
                        ToastUtil.showToast(getApplicationContext(), msg);
                    }
                });
            }
        });
        recyleview.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                PageIndex = 1;
                NetHelperNew.getDecorationPreparationOrder(PageIndex + "", "", new HttpUtils.HttpCallback() {
                    @Override
                    public void onSuccess(String data) {
                        ren_orderBean = MyGson.getInstance().fromJson(data, Ren_OrderBean.class);
                        if (ren_orderBean.getType().equals("1")) {
                            list.clear();
                            list = ren_orderBean.getData();
                            ren_orderAdapter.setListData(list);
                            mLRecyclerViewAdapter.notifyDataSetChanged();
                        }
                        recyleview.refreshComplete(10);
                    }

                    @Override
                    public void onError(String msg) {
                        super.onError(msg);
                        recyleview.refreshComplete(10);
                        ToastUtil.showToast(getApplicationContext(), msg);
                    }
                });

            }
        });
    }

    /**
     * 加载网络数据
     */
    private void loadData() {
        dialog.show();
        NetHelperNew.getDecorationPreparationOrder(PageIndex + "", "", new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                ren_orderBean = MyGson.getInstance().fromJson(data, Ren_OrderBean.class);
                list = ren_orderBean.getData();
                if (ren_orderBean.getType().equals("1")) {
                    createView();
                }
                dialog.dismiss();
            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
                dialog.dismiss();
                ToastUtil.showToast(getApplicationContext(), msg);
            }
        });
    }

}
