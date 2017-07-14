package ercs.com.ercshouseresources.activity.set;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.util.TitleControl;

/**
 * Created by Administrator on 2017/6/22.
 * 设置
 */

public class SetActivity extends BaseActivity {

    @BindView(R.id.fl_updatepwd)
    FrameLayout fl_updatepwd;
@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        ButterKnife.bind(this);

        initTitle();
    }

    /**
     * 初始化标题
     */
    private void initTitle() {

        TitleControl titleControl = new TitleControl(this);
        titleControl.setTitle(getString(R.string.str_set));
    }

    /**
     * 点击事件
     * @param view
     */
    @OnClick({R.id.fl_updatepwd})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.fl_updatepwd:
                //修改密码
                startActivity(new Intent(SetActivity.this,UpdatePwdActivity.class));
                break;
        }
    }
}
