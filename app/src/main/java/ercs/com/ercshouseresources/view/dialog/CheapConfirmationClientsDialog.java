package ercs.com.ercshouseresources.view.dialog;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.adapter.ConfirmationClientsAdapter;
import ercs.com.ercshouseresources.bean.BaseBean;
import ercs.com.ercshouseresources.bean.CustomersListBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.NetWorkUtil;
import ercs.com.ercshouseresources.util.ToastUtil;

/**
 * Created by Administrator on 2017/8/9.
 */

public class CheapConfirmationClientsDialog extends BaseDialog {

    private TextView tv_name;
    private TextView tv_quit;//取消键
    private TextView tv_sure;//确定
    private ListView listView;

    private String name;
    private List<CustomersListBean.DataBean.PhoneListBean> tels;

    private Activity context;
    private ConfirmationClientsAdapter confirmationClientsAdapter;

    private String BuildingID;
    private String CustomerID;
    private LoadingDialog loadingDialog;

    public CheapConfirmationClientsDialog(Activity mActivity, String name, List<CustomersListBean.DataBean.PhoneListBean> tels, String BuildingID, String CustomerID) {
        super(mActivity);
        this.name = name;
        this.tels = tels;
        context = mActivity;
        this.BuildingID = BuildingID;
        this.CustomerID = CustomerID;
        loadingDialog = new LoadingDialog(context, 0);
    }

    @Override
    public int getAnimStyle() {
        return 0;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.dialog_comfirmation_clients;
    }

    @Override
    public void initView() {
        tv_name = findViewById(R.id.tv_name);
        tv_quit = findViewById(R.id.tv_quit);
        tv_sure = findViewById(R.id.tv_sure);
        listView = findViewById(R.id.listview);

        tv_name.setText(name);
        confirmationClientsAdapter = new ConfirmationClientsAdapter(context, tels);
        listView.setAdapter(confirmationClientsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                confirmationClientsAdapter.setPos(i);
                confirmationClientsAdapter.notifyDataSetChanged();
            }
        });
        tv_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                //取消
            }
        });
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //确定
                if (NetWorkUtil.check(context))
                    submit();
            }
        });
    }

    /**
     * 确认报备信息
     */
    private void submit() {
        loadingDialog.show();
        NetHelperNew.getInsertCheapRunningsModel(BuildingID, CustomerID, tels.get(confirmationClientsAdapter.getPos()).getCustomerPhoneID() + "", new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                final BaseBean baseBean = MyGson.getInstance().fromJson(data, BaseBean.class);
                Log.d("===sdatas===", data);
                loadingDialog.dismiss();
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (!baseBean.getType().equals("1")) {
                            ToastUtil.showToast(context, baseBean.getContent());
                        } else {
                            context.finish();
                        }
                        dismiss();
                    }
                });

            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
                loadingDialog.dismiss();

            }
        });
    }
}
