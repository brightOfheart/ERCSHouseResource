package ercs.com.ercshouseresources.activity.set;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.activity.CityAcvitity;
import ercs.com.ercshouseresources.activity.MainActivity;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.bean.UpdateBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.CloseActivityClass;
import ercs.com.ercshouseresources.util.OtherUitl;
import ercs.com.ercshouseresources.util.SPUtil;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;
import ercs.com.ercshouseresources.view.dialog.UpdateDialog;

/**
 * Created by Administrator on 2017/6/22.
 * 设置
 */

public class SetActivity extends BaseActivity {
    private SPUtil spUtil;
    @BindView(R.id.tv_version)
    TextView tv_version;
    private LoadingDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        ButterKnife.bind(this);
        initTitle();
        if (!CloseActivityClass.activityList.contains(this)) {
            CloseActivityClass.activityList.add(this);
        }
    }

    /**
     * 初始化标题
     */
    private void initTitle() {
        dialog = new LoadingDialog(SetActivity.this, 0);
        TitleControl titleControl = new TitleControl(this);
        titleControl.setTitle(getString(R.string.str_set));
        if (spUtil == null)
            spUtil = new SPUtil(this, "fileName");

        tv_version.setText(OtherUitl.getVersion(SetActivity.this));
    }

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.fl_updatepwd, R.id.btn_exit, R.id.ly_memberOutAssess})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fl_updatepwd:
                //修改密码
                startActivity(new Intent(SetActivity.this, UpdatePwdActivity.class));
                break;
            case R.id.btn_exit:
                //退出
                spUtil.putInt(BaseApplication.ISLOGIN, 0);
                CloseActivityClass.exitClient();
                break;
            case R.id.ly_memberOutAssess://版本更新
                getUpdate();
                break;
        }
    }

    private void getUpdate() {
        dialog.show();
        NetHelperNew.updateVersion(new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                dialog.dismiss();
                final UpdateBean updateBean = MyGson.getInstance().fromJson(data, UpdateBean.class);
                if (updateBean.getType().equals("1")) {
                    if (updateBean.getData().getVersionCode().length() > 0)
                        if (Integer.valueOf(updateBean.getData().getVersionCode()) > Integer.valueOf(OtherUitl.getVersionCode(SetActivity.this))) {
                            UpdateDialog updateDialog = new UpdateDialog(SetActivity.this, R.style.dialog, updateBean.getData().getUpdateInfo(), updateBean.getData().getUpdateUrl());
                            updateDialog.show();
                        } else {
                            ToastUtil.showToast(SetActivity.this, "当前已经是最新版本");
                        }

                }

            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
                dialog.dismiss();
                ToastUtil.showToast(SetActivity.this, msg);
            }
        });
    }
}
