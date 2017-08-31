package ercs.com.ercshouseresources.activity.attendance;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.adapter.MemberOutAssessAdapter;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.bean.ClerkBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelper;
import ercs.com.ercshouseresources.util.NetWorkUtil;
import ercs.com.ercshouseresources.util.SPUtil;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;

/**
 * Created by Administrator on 2017/7/6.
 * 员工考核记录
 */

public class MemberAssessActivity extends BaseActivity {
    @BindView(R.id.gridview)
    GridView gridview;
    private LoadingDialog dialog;
    private SPUtil spUtil;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memberoutassess);
        ButterKnife.bind(this);
        initTitle();
        createDialog();
        if (NetWorkUtil.check(getApplicationContext()))
            loadData();
    }
    /**
     * 创建加载对话框
     */
    private void createDialog() {
        dialog = new LoadingDialog(MemberAssessActivity.this, 0);
    }
    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle(getString(R.string.str_memberAssess));
        if (spUtil == null)
            spUtil = new SPUtil(this, "fileName");
    }

    /**
     * 获取id
     * @return
     */
    private String getId()
    {
        return spUtil.getString(BaseApplication.ID,"");
    }
    /**
     * 加载网络数据
     */
    private void loadData() {
        dialog.show();
        NetHelper.clerk(getId(), new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                final ClerkBean clerkBean = MyGson.getInstance().fromJson(data, ClerkBean.class);
                if (clerkBean.getType().equals("1")) {
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

    /**
     * 初始化
     */
    private void initView(final List<ClerkBean.Datas> datas) {
        gridview.setAdapter(new MemberOutAssessAdapter(this,datas));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ClerkBean.Datas data = datas.get(i);
                if (data!=null)
                AtendanceActivity.start(MemberAssessActivity.this,data.getPhotoPath(),data.getName(),data.getId());
            }
        });
    }
}
