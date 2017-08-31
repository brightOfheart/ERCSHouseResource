package ercs.com.ercshouseresources.activity.service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

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
import ercs.com.ercshouseresources.adapter.DynamicAdapter;
import ercs.com.ercshouseresources.bean.DynamicBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.CloseActivityClass;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;

/**
 * Created by Administrator on 2017/7/24.
 * 动态
 */

public class DynamicActivity extends BaseActivity {
    @BindView(R.id.recyleview)
    LRecyclerView recyleview;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private int PageIndex = 1;
    private int PageSize = 10;
    private LoadingDialog dialog;
    private DynamicBean dynamicBean;
    private List<DynamicBean.DataBean> list;
    private DynamicAdapter adapter;

    /**
     * 页面跳转
     */
    public static void start(Activity mactivity, String id) {
        Intent intent = new Intent(mactivity, DynamicActivity.class);
        intent.putExtra("id", id);
        mactivity.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic);
        ButterKnife.bind(this);
        initTitle();
        getData();
        if (!CloseActivityClass.activityList.contains(this)) {
            CloseActivityClass.activityList.add(this);
        }
    }

    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle(getString(R.string.str_dynamic));
        dialog = new LoadingDialog(DynamicActivity.this, 0);
        list = new ArrayList<>();
    }


    /**
     * 初始化
     */
    private void initview() {
        adapter = new DynamicAdapter(DynamicActivity.this, this, list);
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        recyleview.setLayoutManager(new LinearLayoutManager(this));
        recyleview.setAdapter(mLRecyclerViewAdapter);
        recyleview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader); //设置下拉刷新Progress的样式
        // mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);  //设置下拉刷新箭头
        //设置头部加载颜色
        recyleview.setHeaderViewColor(R.color.system_color, R.color.system_color, android.R.color.white);
//设置底部加载颜色
        recyleview.setFooterViewColor(R.color.system_color, R.color.system_color, android.R.color.white);
        recyleview.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                PageIndex = 1;
                NetHelperNew.getDynamic(PageIndex + "", PageSize + "", "1", new HttpUtils.HttpCallback() {
                    @Override
                    public void onSuccess(String data) {
                        recyleview.refreshComplete(PageSize);// REQUEST_COUNT为每页加载数量
                        dynamicBean = MyGson.getInstance().fromJson(data, DynamicBean.class);
                        if (dynamicBean.getType().equals("1")) {
                            list.clear();
                            list.addAll(dynamicBean.getData());
                            adapter.setListData(list);
                            mLRecyclerViewAdapter.notifyDataSetChanged();
                        } else {
                            ToastUtil.showToast(getApplicationContext(), dynamicBean.getContent());
                        }

                    }

                    @Override
                    public void onError(String msg) {
                        super.onError(msg);
                        recyleview.refreshComplete(PageSize);// REQUEST_COUNT为每页加载数量
                        ToastUtil.showToast(getApplicationContext(), msg);
                    }
                });
            }
        });
        recyleview.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                PageIndex++;
                NetHelperNew.getDynamic(PageIndex + "", PageSize + "", "1", new HttpUtils.HttpCallback() {
                    @Override
                    public void onSuccess(String data) {
                        recyleview.refreshComplete(PageSize);// REQUEST_COUNT为每页加载数量
                        dynamicBean = MyGson.getInstance().fromJson(data, DynamicBean.class);
                        if (dynamicBean.getType().equals("1")) {
                            if (dynamicBean.getData().size() > 0) {
                                list.addAll(dynamicBean.getData());
                                mLRecyclerViewAdapter.notifyDataSetChanged();

                            } else {
                                ToastUtil.showToast(getApplicationContext(), "没有更多数据了");
                            }
                        } else {
                            ToastUtil.showToast(getApplicationContext(), dynamicBean.getContent());
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
        });
    }

    /**
     * 获取网络接口
     */
    private void getData() {
        dialog.show();
        NetHelperNew.getDynamic(PageIndex + "", PageSize + "", "1", new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                dialog.dismiss();
                dynamicBean = MyGson.getInstance().fromJson(data, DynamicBean.class);
                if (dynamicBean.getType().equals("1")) {
                    list.addAll(dynamicBean.getData());
                    initview();
                    mLRecyclerViewAdapter.notifyDataSetChanged();
                } else {
                    ToastUtil.showToast(getApplicationContext(), dynamicBean.getContent());
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

    private String getId() {

        return getIntent().getStringExtra("id");
    }
}
