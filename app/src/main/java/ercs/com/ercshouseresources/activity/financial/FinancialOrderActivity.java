package ercs.com.ercshouseresources.activity.financial;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.bean.FinancialOrderBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.CloseActivityClass;
import ercs.com.ercshouseresources.util.NetWorkUtil;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;

/**
 * Created by Administrator on 2017/8/18.
 * 订单信息
 */

public class FinancialOrderActivity extends BaseActivity {
    private LoadingDialog dialog;
    @BindView(R.id.recyleview)
    LRecyclerView recyleview;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private int PageIndex = 1;
    private FinancialOrderAdapter adapter;
    private List<FinancialOrderBean.DataBean> list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financialorder);
        ButterKnife.bind(this);
        initTitle();
        if (NetWorkUtil.check(getApplicationContext()))
            loadData();
        if (!CloseActivityClass.activityList.contains(this)) {
            CloseActivityClass.activityList.add(this);
        }
    }

    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle("报备信息");
        dialog = new LoadingDialog(FinancialOrderActivity.this, 0);
    }

    private void initview(FinancialOrderBean financialOrderBean) {
        adapter = new FinancialOrderAdapter(FinancialOrderActivity.this, this, financialOrderBean.getData());
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        recyleview.setLayoutManager(new LinearLayoutManager(this));
        recyleview.setAdapter(mLRecyclerViewAdapter);
        recyleview.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                PageIndex = 1;
                NetHelperNew.getFinacialRunningList(PageIndex + "", new HttpUtils.HttpCallback() {
                    @Override
                    public void onSuccess(String data) {
                        final FinancialOrderBean financialOrderBean = MyGson.getInstance().fromJson(data, FinancialOrderBean.class);
                        recyleview.refreshComplete(10);
                        if (financialOrderBean.getType().equals("1")) {
                            list.clear();
                            list = financialOrderBean.getData();
                            adapter.setListData(list);
                            mLRecyclerViewAdapter.notifyDataSetChanged();
                        }

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
        NetHelperNew.getFinacialRunningList(PageIndex + "", new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                dialog.dismiss();
                final FinancialOrderBean financialOrderBean = MyGson.getInstance().fromJson(data, FinancialOrderBean.class);
                list = financialOrderBean.getData();
                if (financialOrderBean.getType().equals("1")) {
                    initview(financialOrderBean);
                } else {
                    ToastUtil.showToast(FinancialOrderActivity.this, financialOrderBean.getContent());
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
}
