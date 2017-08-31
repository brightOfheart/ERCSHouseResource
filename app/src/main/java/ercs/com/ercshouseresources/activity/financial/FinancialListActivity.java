package ercs.com.ercshouseresources.activity.financial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.adapter.FinancialListAdapter;
import ercs.com.ercshouseresources.bean.FinancialListBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.CloseActivityClass;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;

/**
 * Created by Administrator on 2017/8/17.
 */

public class FinancialListActivity extends BaseActivity {
    private LoadingDialog dialog;
    @BindView(R.id.recyleview)
    LRecyclerView recyleview;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;

    /**
     * 页面跳转
     */
    public static void start(Activity mactivity, String id, String title) {
        Intent intent = new Intent(mactivity, FinancialListActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("title", title);
        mactivity.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financiallist);
        ButterKnife.bind(this);
        initTitle();
        if(!CloseActivityClass.activityList.contains(this))
        {
            CloseActivityClass.activityList.add(this);
        }
        loadData();
    }

    private void initview(FinancialListBean financialListBean) {
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(new FinancialListAdapter(FinancialListActivity.this, this, financialListBean.getData()));
        recyleview.setLayoutManager(new LinearLayoutManager(this));
        recyleview.setAdapter(mLRecyclerViewAdapter);
        recyleview.setPullRefreshEnabled(false);
    }

    private void loadData() {
        dialog.show();
        NetHelperNew.getFinancial(getId(), new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                dialog.dismiss();
                final FinancialListBean financialListBean = MyGson.getInstance().fromJson(data, FinancialListBean.class);
                if (financialListBean.getType().equals("1")) {
                    initview(financialListBean);
                }
            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
                dialog.dismiss();
                ToastUtil.showToast(FinancialListActivity.this, msg);
            }
        });
    }

    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle(getTitles());
        dialog = new LoadingDialog(FinancialListActivity.this, 0);
    }

    private String getId() {

        return getIntent().getStringExtra("id");
    }

    private String getTitles() {

        return getIntent().getStringExtra("title");
    }
}
