package ercs.com.ercshouseresources.activity.financial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.util.CloseActivityClass;
import ercs.com.ercshouseresources.util.TitleControl;

/**
 * Created by Administrator on 2017/8/18.
 * 金融详情页面
 */

public class FinanicalDetailActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_content)
    TextView tv_content;
    @BindView(R.id.btn_reportingclients)
    Button btn_reportingclients;

    /**
     * 页面跳转
     */
    public static void start(Activity mactivity, String mtitle, String title, String content,String id) {
        Intent intent = new Intent(mactivity, FinanicalDetailActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("content", content);
        intent.putExtra("mtitle", mtitle);
        intent.putExtra("id", id);
        mactivity.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finanicaldetail);
        ButterKnife.bind(this);
        initTitle();
        if (!CloseActivityClass.activityList.contains(this)) {
            CloseActivityClass.activityList.add(this);
        }
        createview();
    }

    private void createview() {
        tv_title.setText(getTitles());
        tv_content.setText(getContent());
        btn_reportingclients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FinancialReportingClientsActivity.start(FinanicalDetailActivity.this,getId());
            }
        });
    }

    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle(getMtitle());

    }
    private String getId() {

        return getIntent().getStringExtra("id");
    }

    private String getTitles() {

        return getIntent().getStringExtra("title");
    }

    private String getContent() {

        return getIntent().getStringExtra("content");
    }

    private String getMtitle() {

        return getIntent().getStringExtra("mtitle");
    }
}
