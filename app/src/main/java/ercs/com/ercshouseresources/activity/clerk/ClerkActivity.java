package ercs.com.ercshouseresources.activity.clerk;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.adapter.ClerkAdapter;
import ercs.com.ercshouseresources.bean.ClerkBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelper;
import ercs.com.ercshouseresources.util.NetWorkUtil;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;

/**
 * Created by Administrator on 2017/6/22.
 * 职员列表
 */

public class ClerkActivity extends BaseActivity {
    @BindView(R.id.recyleview)
    LRecyclerView recyleview;
    private String SUCCESS = "1";
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private LoadingDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clerk);
        ButterKnife.bind(this);
        initTitle();
        createDialog();
        if (NetWorkUtil.check(getApplicationContext()))
            loadData();
    }

    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle(getString(R.string.str_clerk));
    }

    /**
     * 创建加载对话框
     */
    private void createDialog() {
        dialog = new LoadingDialog(ClerkActivity.this, 0);
    }

    /**
     * 初始化
     */
    private void initView(List<ClerkBean.Datas> datas) {
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(new ClerkAdapter(ClerkActivity.this, this, datas));
        recyleview.setLayoutManager(new LinearLayoutManager(this));
        recyleview.setAdapter(mLRecyclerViewAdapter);
        recyleview.setPullRefreshEnabled(false);
    }

    /**
     * 加载网络数据
     */
    private void loadData() {
        dialog.show();
        NetHelper.clerk("0", new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                final ClerkBean clerkBean = MyGson.getInstance().fromJson(data, ClerkBean.class);
                if (clerkBean.getType().equals(SUCCESS)) {
                    initView(clerkBean.getData());
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                        ToastUtil.showToast(getApplicationContext(), clerkBean.getContent());
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

}
