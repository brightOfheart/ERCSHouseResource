package ercs.com.ercshouseresources.activity.process;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;

/**
 * Created by Administrator on 2017/7/6.
 * 流程申请
 */

public class ProcessApplyAcvitity extends BaseActivity {
    private LoadingDialog dialog;
    @BindView(R.id.tv_protype)
    TextView tv_protype;//流程类型
    @BindView(R.id.tv_retroactivedata)
    TextView tv_retroactivedata;//补签日期
    @BindView(R.id.tv_processtypes)
    TextView tv_processtypes;//流程类型
    @BindView(R.id.edit_reason)
    EditText edit_reason;//输入事由的文本框
    @BindView(R.id.iv_photo)
    ImageView iv_photo;//选择图片
    @BindView(R.id.gridview)
    GridView gridview;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processapply);
        ButterKnife.bind(this);
        initTitle();
    }

    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle(getString(R.string.str_processpay));
        dialog = new LoadingDialog(ProcessApplyAcvitity.this, 0);
    }

    /**
     * 点击事件的处理
     *
     * @param view
     */
    @OnClick({R.id.img_show, R.id.btn_login, R.id.ly_findpwd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_protype://流程类型

                break;
            case R.id.tv_retroactivedata://补签日期

                break;
            case R.id.tv_processtypes://流程类型

                break;
            case R.id.btn_post://提交

                break;
        }
    }
}
