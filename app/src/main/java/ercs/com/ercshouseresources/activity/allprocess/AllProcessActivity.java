package ercs.com.ercshouseresources.activity.allprocess;
import android.os.Bundle;
import android.widget.ListView;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.adapter.AllProcessAdapter;
import ercs.com.ercshouseresources.bean.AllProcessBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelper;
import ercs.com.ercshouseresources.util.NetWorkUtil;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;
/**
 * Created by Administrator on 2017/6/27.
 * 所有流程
 */

public class AllProcessActivity extends BaseActivity {
    @BindView(R.id.listview)
    ListView listview;
    private LoadingDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allprocess);
        ButterKnife.bind(this);
        initTitle();
        if (NetWorkUtil.check(getApplicationContext()))
            loadData();
    }

    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle(getString(R.string.str_allprocess));
        dialog = new LoadingDialog(AllProcessActivity.this, 0);
    }

    /**
     * 获取网络数据
     */
    private void loadData() {
        dialog.show();
        NetHelper.leavealllis("", new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                final AllProcessBean allProcessBean = MyGson.getInstance().fromJson(data, AllProcessBean.class);
                if (allProcessBean.getType().equals("1"))
                    initview(allProcessBean.getData());
                dialog.dismiss();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showToast(getApplicationContext(), allProcessBean.getContent());
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

    /**
     * 设置显示的数据
     *
     * @param listData
     */
    private void initview(List<AllProcessBean.DataBean> listData) {
        listview.setAdapter(new AllProcessAdapter(getApplicationContext(), listData));
    }
}
