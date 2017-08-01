package ercs.com.ercshouseresources.activity.service;

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
 * Created by Administrator on 2017/7/24.
 * 结算周期
 */

public class SettlementCycleActivity extends BaseActivity {
    @BindView(R.id.tv_content)
    TextView tv_content;
    /**
     * 页面跳转
     */
    public static void start(Activity mactivity, String str) {
        Intent intent = new Intent(mactivity, SettlementCycleActivity.class);
        intent.putExtra("str", str);
        mactivity.startActivity(intent);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settlement);
        ButterKnife.bind(this);
        initTitle();
        createData();
    }
    private void createData()
    {
        tv_content.setText(getStr());
    }
    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle(getString(R.string.str_settlementcycle));

    }
    private String getStr() {

        return getIntent().getStringExtra("str");
    }
}
