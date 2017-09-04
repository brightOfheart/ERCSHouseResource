package ercs.com.ercshouseresources.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.set.UpdatePwdActivity;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.bean.BaseBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.SPUtil;
import ercs.com.ercshouseresources.util.TimeCountUtil;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;

/**
 * Created by Administrator on 2017/6/21.
 * 密码找回
 */

public class PWDFindActivity extends BaseActivity {
    @BindView(R.id.tv_verificationcode)
    TextView tv_verificationcode;
    @BindView(R.id.edit_phone)
    EditText edit_phone;
    @BindView(R.id.edit_pwd)
    EditText edit_pwd;
    @BindView(R.id.edit_code)
    EditText edit_code;
    private LoadingDialog dialog;
    private TimeCountUtil timeCountUtil;//时间计时

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwdfind);
        ButterKnife.bind(this);
        initTitle();
        createData();
    }

    /**
     * 初始化数据
     */
    private void createData() {
        dialog = new LoadingDialog(PWDFindActivity.this, 0);
    }


    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle("密码找回");
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
                if (edit_phone.getText().toString().length() == 11) {
                    timeCountUtil = new TimeCountUtil(PWDFindActivity.this, 60000, 1000, tv_verificationcode);
                    timeCountUtil.start();
                    getcode(edit_phone.getText().toString());
                } else {
                    ToastUtil.showToast(PWDFindActivity.this, "请填写正确的手机号码");
                }

                break;
            case R.id.btn_sure:
                //确定
                if (edit_phone.getText().toString().length() == 11) {
                    if (edit_pwd.getText().toString().length() > 0) {
                        if (edit_code.getText().toString().length() > 0) {
                            sure(edit_phone.getText().toString(), edit_pwd.getText().toString(), edit_code.getText().toString());
                        } else {
                            ToastUtil.showToast(PWDFindActivity.this, "请填写密码");
                        }

                    } else {
                        ToastUtil.showToast(PWDFindActivity.this, "请填写验证码");
                    }
                } else {
                    ToastUtil.showToast(PWDFindActivity.this, "请填写正确的手机号码");
                }

                break;
        }
    }

    private void getcode(String phone) {
        dialog.show();
        NetHelperNew.findpwdcode(phone, new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                dialog.dismiss();
                final BaseBean baseBean = MyGson.getInstance().fromJson(data, BaseBean.class);
                ToastUtil.showToast(PWDFindActivity.this, baseBean.getContent());
            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
                dialog.dismiss();
                ToastUtil.showToast(PWDFindActivity.this, msg);
            }
        });
    }

    private void sure(String phone, String code, String PassWord) {
        dialog.show();
        NetHelperNew.findpwd(phone, code, PassWord, new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                dialog.dismiss();
                final BaseBean baseBean = MyGson.getInstance().fromJson(data, BaseBean.class);
                if (baseBean.getType().equals("1")) {
                    finish();
                }
                ToastUtil.showToast(PWDFindActivity.this, baseBean.getContent());
            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
                dialog.dismiss();
                ToastUtil.showToast(PWDFindActivity.this, msg);
            }
        });
    }
}
