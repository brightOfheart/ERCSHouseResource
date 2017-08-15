package ercs.com.ercshouseresources.activity.renovation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.activity.service.DynamicActivity;
import ercs.com.ercshouseresources.bean.BaseBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.newbean.LoginBean;
import ercs.com.ercshouseresources.util.CloseActivityClass;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;

/**
 * Created by Administrator on 2017/8/11.
 * 装修报备信息
 */

public class Ren_PrepareInformationActivity extends BaseActivity {
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_areao)
    EditText tv_areao;
    @BindView(R.id.tv_address)
    EditText tv_address;
    @BindView(R.id.tv_remarks)
    EditText tv_remarks;
    @BindView(R.id.btn_sure)
    Button btn_sure;
    private LoadingDialog dialog;

    /**
     * 页面跳转
     */
    public static void start(Activity mactivity, String name, String tel, String phoneid, String CustomerID, String BuildingID) {
        Intent intent = new Intent(mactivity, Ren_PrepareInformationActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("tel", tel);
        intent.putExtra("phoneid", phoneid);
        intent.putExtra("CustomerID", CustomerID);
        intent.putExtra("BuildingID", BuildingID);
        mactivity.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepareinformation);
        ButterKnife.bind(this);
        initTitle();
        createView();
        if(!CloseActivityClass.activityList.contains(this))
        {
            CloseActivityClass.activityList.add(this);
        }
    }

    private void createView() {
        tv_name.setText(getName());
        tv_phone.setText(getTel());
        btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (tv_address.getText().toString().length() > 0) {
                    if (tv_areao.getText().toString().length() > 0) {
                        dialog = new LoadingDialog(Ren_PrepareInformationActivity.this, 0);
                        NetHelperNew.getDecorationPreparation(getBuildingID(), getCustomerID(), getphoneid(), tv_address.getText().toString(), tv_areao.getText().toString(), tv_remarks.getText().toString(), new HttpUtils.HttpCallback() {
                            @Override
                            public void onSuccess(String data) {
                                dialog.dismiss();
                                final BaseBean baseBean = MyGson.getInstance().fromJson(data, BaseBean.class);
                                ToastUtil.showToast(Ren_PrepareInformationActivity.this, baseBean.getContent());
                            }

                            @Override
                            public void onError(String msg) {
                                super.onError(msg);
                                dialog.dismiss();
                                ToastUtil.showToast(Ren_PrepareInformationActivity.this, msg);
                            }
                        });

                    } else {
                        ToastUtil.showToast(Ren_PrepareInformationActivity.this, "请填写面积");
                    }
                } else {
                    ToastUtil.showToast(Ren_PrepareInformationActivity.this, "请填写地址");
                }

            }
        });
    }

    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle("报备信息");
    }

    private String getName() {

        return getIntent().getStringExtra("name");
    }

    private String getTel() {

        return getIntent().getStringExtra("tel");
    }

    private String getBuildingID() {

        return getIntent().getStringExtra("BuildingID");
    }

    private String getCustomerID() {

        return getIntent().getStringExtra("CustomerID");
    }

    private String getphoneid() {

        return getIntent().getStringExtra("phoneid");
    }
}
