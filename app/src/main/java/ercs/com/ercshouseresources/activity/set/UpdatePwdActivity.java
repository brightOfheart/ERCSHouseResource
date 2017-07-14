package ercs.com.ercshouseresources.activity.set;

import android.media.MediaCodec;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.util.SPUtil;
import ercs.com.ercshouseresources.util.TimeCountUtil;
import ercs.com.ercshouseresources.util.TitleControl;
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
    }


    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle(getString(R.string.str_updatepwd));
        loadingDialog=new LoadingDialog(this,0);

    }

    /**
     * 点击事件
     * @param view
     */
    @OnClick({R.id.tv_verificationcode})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.tv_verificationcode:
                //获取验证码
                 timeCountUtil = new TimeCountUtil(UpdatePwdActivity.this, 60000, 1000, tv_verificationcode);
                timeCountUtil.start();
                break;
        }
    }
}
