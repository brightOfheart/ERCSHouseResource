package ercs.com.ercshouseresources.activity.cheaproom;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.activity.service.DynamicActivity;
import ercs.com.ercshouseresources.adapter.DynamicAdapter;
import ercs.com.ercshouseresources.bean.DynamicBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;

/**
 * Created by Administrator on 2017/8/8.
 * 低价房动态
 */

public class CheapDynamicActivity extends BaseActivity {
    @BindView(R.id.recyleview)
    LRecyclerView recyleview;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private int PageIndex = 1;
    private int PageSize = 10;
    private LoadingDialog dialog;
    private DynamicBean dynamicBean;

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

    }

    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle(getString(R.string.str_dynamic));
        dialog = new LoadingDialog(CheapDynamicActivity.this, 0);
    }


    /**
     * 初始化
     */
    private void initview() {
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(new DynamicAdapter(CheapDynamicActivity.this, this, dynamicBean.getData()));
        recyleview.setLayoutManager(new LinearLayoutManager(this));
        recyleview.setAdapter(mLRecyclerViewAdapter);
        recyleview.setPullRefreshEnabled(true);
        recyleview.setPullRefreshEnabled(true);
        recyleview.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                PageIndex = 1;
                NetHelperNew.getCheapDynamic(PageIndex + "", PageSize + "", "1", new HttpUtils.HttpCallback() {
                    @Override
                    public void onSuccess(String data) {
                        dialog.dismiss();
                        dynamicBean = MyGson.getInstance().fromJson(data, DynamicBean.class);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                recyleview.refreshComplete(PageSize);// REQUEST_COUNT为每页加载数量
                                mLRecyclerViewAdapter.notifyDataSetChanged();
                                ToastUtil.showToast(getApplicationContext(), dynamicBean.getContent());

                            }
                        });
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
        recyleview.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                PageIndex++;
                NetHelperNew.getCheapDynamic(PageIndex + "", PageSize + "", "1", new HttpUtils.HttpCallback() {
                    @Override
                    public void onSuccess(String data) {
                        dialog.dismiss();
                        dynamicBean = MyGson.getInstance().fromJson(data, DynamicBean.class);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                recyleview.refreshComplete(PageSize);// REQUEST_COUNT为每页加载数量
                                mLRecyclerViewAdapter.notifyDataSetChanged();
                                ToastUtil.showToast(getApplicationContext(), dynamicBean.getContent());

                            }
                        });
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
        NetHelperNew.getCheapDynamic(PageIndex + "", PageSize + "", "1", new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                dialog.dismiss();
                dynamicBean = MyGson.getInstance().fromJson(data, DynamicBean.class);
                if (dynamicBean.getType().equals("1")) {
                    initview();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mLRecyclerViewAdapter.notifyDataSetChanged();
                        ToastUtil.showToast(getApplicationContext(), dynamicBean.getContent());

                    }
                });
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
