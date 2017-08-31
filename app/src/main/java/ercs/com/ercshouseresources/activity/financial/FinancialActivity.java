package ercs.com.ercshouseresources.activity.financial;

import android.os.Bundle;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.bean.FinancialBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.CloseActivityClass;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;
import ercs.com.ercshouseresources.view.item.FinancialItem;

/**
 * Created by Administrator on 2017/8/17.
 */

public class FinancialActivity extends BaseActivity {
    private LoadingDialog dialog;
    @BindView(R.id.ly_all)
    LinearLayout ly_all;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial);
        ButterKnife.bind(this);
        initTitle();
        if(!CloseActivityClass.activityList.contains(this))
        {
            CloseActivityClass.activityList.add(this);
        }
        loadData();

    }

    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle("金融");
        dialog = new LoadingDialog(FinancialActivity.this, 0);
    }

    private void loadData() {
        dialog.show();
        NetHelperNew.getFinancial("0", new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                dialog.dismiss();
                final FinancialBean financialBean = MyGson.getInstance().fromJson(data, FinancialBean.class);
                if (financialBean.getType().equals("1")) {
                    createview(financialBean);
                }
            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
                dialog.dismiss();
                ToastUtil.showToast(FinancialActivity.this, msg);
            }
        });
    }

    private void createview(FinancialBean financialBean) {
        for (int i = 0; i < financialBean.getData().size(); i++) {
            FinancialItem financialItem = new FinancialItem(FinancialActivity.this, (i+1)+"F", financialBean.getData().get(i).getText(), financialBean.getData().get(i).getId());
            ly_all.addView(financialItem);
        }

    }
}
