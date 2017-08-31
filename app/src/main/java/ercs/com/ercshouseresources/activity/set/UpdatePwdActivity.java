package ercs.com.ercshouseresources.activity.set;

import android.media.MediaCodec;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.activity.LoginActivity;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.bean.BaseBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.newbean.LoginBean;
import ercs.com.ercshouseresources.util.SPUtil;
import ercs.com.ercshouseresources.util.TimeCountUtil;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;

/**
 * Created by Administrator on 2017/6/22.
 * 修改密码
 */

public class UpdatePwdActivity extends BaseActivity {
    private LoadingDialog loadingDialog;

    @BindView(R.id.tv_verificationcode)
    TextView tv_verificationcode;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.edit_pwd)
    EditText edit_pwd;
    @BindView(R.id.edit_code)
    EditText edit_code;
    private LoadingDialog dialog;
    private TimeCountUtil timeCountUtil;//时间计时

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatepwd);

        ButterKnife.bind(this);
        initTitle();
        createData();
    }

    /**
     * 初始化数据
     */
    private void createData() {
        SPUtil spUtil = new SPUtil(this, BaseApplication.FILENAME);
        String s = spUtil.getString(BaseApplication.PHONE, "");
        tv_phone.setText(s);
        dialog = new LoadingDialog(UpdatePwdActivity.this, 0);
    }


    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle(getString(R.string.str_updatepwd));
        loadingDialog = new LoadingDialog(this, 0);

    }

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.tv_verificationcode, R.id.btn_sure})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_verificationcode:
                //获取验证码
                timeCountUtil = new TimeCountUtil(UpdatePwdActivity.this, 60000, 1000, tv_verificationcode);
                timeCountUtil.start();
                getcode();
                break;
            case R.id.btn_sure:
                //确定
                if (edit_pwd.getText().toString().length() > 0) {
                    if (edit_code.getText().toString().length() > 0) {
                        sure(edit_pwd.getText().toString(), edit_code.getText().toString());
                    } else {
                        ToastUtil.showToast(UpdatePwdActivity.this, "请填写密码");
                    }

                } else {
                    ToastUtil.showToast(UpdatePwdActivity.this, "请填写验证码");
                }
                break;
        }
    }

    private void getcode() {
        dialog.show();
        NetHelperNew.getCode(new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                dialog.dismiss();
                final BaseBean baseBean = MyGson.getInstance().fromJson(data, BaseBean.class);
                ToastUtil.showToast(UpdatePwdActivity.this, baseBean.getContent());
            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
                dialog.dismiss();
                ToastUtil.showToast(UpdatePwdActivity.this, msg);
            }
        });
    }

    private void sure(String code, String PassWord) {
        dialog.show();
        NetHelperNew.updatePwd(code, PassWord, new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                dialog.dismiss();
                final BaseBean baseBean = MyGson.getInstance().fromJson(data, BaseBean.class);
                if (baseBean.getType().equals("1")) {
                    finish();
                }
                ToastUtil.showToast(UpdatePwdActivity.this, baseBean.getContent());
            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
                dialog.dismiss();
                ToastUtil.showToast(UpdatePwdActivity.this, msg);
            }
        });
    }
}
