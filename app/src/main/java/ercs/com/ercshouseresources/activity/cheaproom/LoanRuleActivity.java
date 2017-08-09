package ercs.com.ercshouseresources.activity.cheaproom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.util.TitleControl;

/**
 * Created by Administrator on 2017/8/8.
 * 贷款规则
 */

public class LoanRuleActivity extends BaseActivity {
    @BindView(R.id.tv_content)
    TextView tv_content;

    /**
     * 页面跳转
     */
    public static void start(Activity mactivity, String content)
    {

        Intent intent = new Intent(mactivity, CheapRoomRuleActivity.class);
        intent.putExtra("content",content);
        mactivity.startActivity(intent);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cdes);
        ButterKnife.bind(this);
        initTitle();
    }

    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle("贷款规则");
        tv_content.setText(getContent());
    }

    /**
     * 获取json数据
     * @return
     */
    private String getContent()
    {

        return getIntent().getStringExtra("content");
    }
}
