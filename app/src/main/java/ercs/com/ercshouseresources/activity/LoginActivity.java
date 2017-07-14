package ercs.com.ercshouseresources.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.bean.LoginBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelper;
import ercs.com.ercshouseresources.util.NetWorkUtil;
import ercs.com.ercshouseresources.util.SPUtil;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;

/**
 * Created by Administrator on 2017/6/21.
 * 登录
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.edit_phone)
    EditText edit_phone;//手机账号
    @BindView(R.id.edit_pwd)
    EditText edit_pwd;//密码
    @BindView(R.id.edit_code)
    EditText edit_code;//识别码
    @BindView(R.id.img_show)
    ImageView img_show;//显示与隐藏密码的图标
    private SPUtil spUtil;
    private String LOGINRIGHT = "1";
    private LoadingDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initialize();
    }

    /**
     * 点击事件的处理
     *
     * @param view
     */
    @OnClick({R.id.img_show, R.id.btn_login, R.id.ly_findpwd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_show://显示与隐藏密码的图标
                isShowPwd();
                break;
            case R.id.btn_login://登录
                if (check())
                    login();
                break;
            case R.id.ly_findpwd://密码找回
                startActivity(new Intent(getApplicationContext(), PWDFindActivity.class));
                break;
        }
    }

    /**
     * 初始化
     */
    private void initialize() {
        if (spUtil == null)
            spUtil = new SPUtil(this, "fileName");
        if (spUtil.getInt(BaseApplication.ISSHOWPWD, 0) == 1)//判断是否显示密码
            showContent(1);
        else
            showContent(0);
        if (spUtil.getInt(BaseApplication.ISLOGIN, 0) == 1)
            showId();
        dialog = new LoadingDialog(LoginActivity.this, 0);
    }

    /**
     * 是否显示密码
     */
    private void isShowPwd() {
        if (spUtil.getInt(BaseApplication.ISSHOWPWD, 0) == 1) {
            save(0);
            showContent(0);
        } else {
            save(1);
            showContent(1);
        }
    }

    /**
     * 显示内容的方式
     */
    private void showContent(int kind) {
        if (kind == 1) {//显示密码
            img_show.setImageResource(R.mipmap.icon_pwd_show);
            edit_pwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else {
            img_show.setImageResource(R.mipmap.icon_pwd_closed);
            edit_pwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        edit_pwd.setSelection(edit_pwd.getText().toString().length());//设置光标位置
    }

    /**
     * 文件存储是否显示密码
     */
    private void save(int count) {
        spUtil.putInt(BaseApplication.ISSHOWPWD, count);
    }

    /**
     * 文件存储是否登录成功
     *
     * @param count
     */
    private void saveLogin(int count) {
        spUtil.putInt(BaseApplication.ISLOGIN, count);
    }

    /**
     * 保存账号和密码
     *
     * @param id
     * @param pwd
     */
    private void saveIdPwd(String id,String phone, String pwd, String photopath, String name, String depname, String Authority) {
        spUtil.putString(BaseApplication.ID, id);
        spUtil.putString(BaseApplication.PHONE, phone);
        spUtil.putString(BaseApplication.PWD, pwd);
        spUtil.putString(BaseApplication.PHOTOPATH, photopath);
        spUtil.putString(BaseApplication.NAME, name);
        spUtil.putString(BaseApplication.DEPNAME, depname);
        spUtil.putString(BaseApplication.AUTHORITY, Authority);
    }

    /**
     * 显示账号和密码
     */
    private void showId() {
        edit_phone.setText(spUtil.getString(BaseApplication.PHONE, ""));
        edit_pwd.setText(spUtil.getString(BaseApplication.PWD, ""));
        edit_phone.setSelection(edit_phone.getText().toString().length());//设置光标位置
    }

    /**
     * 登录操作
     */
    private void login() {
        dialog.show();
        NetHelper.login(getId(), getPwd(), new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                dialog.dismiss();
                final LoginBean loginBean = MyGson.getInstance().fromJson(data, LoginBean.class);
                if (loginBean.getType().equals(LOGINRIGHT)) {
                    saveIdPwd(loginBean.getData().getId(),getId(),getPwd(), loginBean.getData().getPhotoPath(), loginBean.getData().getName(), loginBean.getData().getDepName(), loginBean.getData().getAuthority());
                    saveLogin(1);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showToast(getApplicationContext(), loginBean.getContent());
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                });

            }

            @Override
            public void onError(String msg) {
                dialog.dismiss();
                super.onError(msg);
                ToastUtil.showToast(getApplicationContext(), msg);
            }
        });

    }

    /**
     * 获取账号
     *
     * @return
     */
    private String getId() {
        return edit_phone.getText().toString().trim();
    }

    /**
     * 获取密码
     *
     * @return
     */
    private String getPwd() {
        return edit_pwd.getText().toString().trim();
    }

    /**
     * 登录之前的检测
     *
     * @return
     */
    private boolean check() {
        if (getId().length() != 11) {
            ToastUtil.showToast(getApplicationContext(), getString(R.string.error_tel));
            return false;
        }
        if (getPwd().length() == 0) {
            ToastUtil.showToast(getApplicationContext(), getString(R.string.error_pwd));
            return false;
        }
        if (!NetWorkUtil.checkNet(getApplicationContext())) {
            ToastUtil.showToast(getApplicationContext(), getString(R.string.error_net));
            return false;
        }
        return true;
    }
}
