package ercs.com.ercshouseresources.activity.renovation;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.util.TitleControl;

/**
 * Created by Administrator on 2017/8/11.
 * 公司简介
 */

public class Ren_companyIntroActivity extends BaseActivity {
    @BindView(R.id.tv_content)
    TextView tv_content;
    @BindView(R.id.iv_photo)
    ImageView iv_photo;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companyintro);
        ButterKnife.bind(this);
        initTitle();
    }
    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle("公司简介");

    }
}
