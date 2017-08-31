package ercs.com.ercshouseresources.activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;

/**
 * Created by Administrator on 2017/6/21.
 * 密码找回
 */

public class PWDFindActivity extends BaseActivity {
    @BindView(R.id.iv_goback)
    ImageView iv_goback;//返回

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwdfind);
        ButterKnife.bind(this);
    }

    /**
     * 点击事件的处理
     *
     * @param view
     */
    @OnClick({R.id.iv_goback})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_show://返回
                finish();
                break;

        }
    }
}
