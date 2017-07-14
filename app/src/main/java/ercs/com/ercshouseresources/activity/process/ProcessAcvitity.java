package ercs.com.ercshouseresources.activity.process;
import android.os.Bundle;
import android.widget.LinearLayout;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.bean.ProcessBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelper;
import ercs.com.ercshouseresources.util.NetWorkUtil;
import ercs.com.ercshouseresources.util.SPUtil;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.view.item.ProcessAdapterChildItem;

/**
 * Created by Administrator on 2017/6/23.
 * 流程审批
 */

public class ProcessAcvitity extends BaseActivity {
    @BindView(R.id.ly_rest)
    LinearLayout ly_rest;
    @BindView(R.id.ly_out)
    LinearLayout ly_out;
    @BindView(R.id.ly_retroactive)
    LinearLayout ly_retroactive;
    private String SUCCESS = "1";

    private SPUtil spUtil;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process);
        ButterKnife.bind(this);
        initTitle();
        if (NetWorkUtil.check(getApplicationContext()))
            loadData();
    }

    /**
     * 初始化
     */
    private void initView(List<ProcessBean.DataBean> datas) {
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).getLeaveType().equals("1")) {
                ly_rest.addView(new ProcessAdapterChildItem(ProcessAcvitity.this, datas.get(i)));
            } else if (datas.get(i).getLeaveType().equals("3")) {
                ly_out.addView(new ProcessAdapterChildItem(ProcessAcvitity.this, datas.get(i)));
            } else if (datas.get(i).getLeaveType().equals("2")) {
                ly_retroactive.addView(new ProcessAdapterChildItem(ProcessAcvitity.this, datas.get(i)));
            }
        }
    }


    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle(getString(R.string.str_process));
        if (spUtil==null)
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
     * 获取网络数据
     */
    private void loadData() {
        NetHelper.process(getId(), new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                final ProcessBean processBean = MyGson.getInstance().fromJson(data, ProcessBean.class);
                if (processBean.getType().equals(SUCCESS)) {
                    initView(processBean.getData());
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showToast(getApplicationContext(), processBean.getContent());
                    }
                });

            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
                ToastUtil.showToast(getApplicationContext(), msg);
            }
        });

    }

}
