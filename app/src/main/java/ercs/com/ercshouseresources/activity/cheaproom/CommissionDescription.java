package ercs.com.ercshouseresources.activity.cheaproom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.util.CloseActivityClass;
import ercs.com.ercshouseresources.util.TitleControl;

/**
 * Created by Administrator on 2017/8/8.
 * 佣金说明
 */

public class CommissionDescription extends BaseActivity {
    @BindView(R.id.tv_1)
    TextView tv_1;
    @BindView(R.id.tv_2)
    TextView tv_2;
    /**
     * 页面跳转
     */
    public static void start(Activity mactivity, String content,String asscount)
    {

        Intent intent = new Intent(mactivity, CommissionDescription.class);
        intent.putExtra("content",content);
        intent.putExtra("asscount",asscount);
        mactivity.startActivity(intent);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cdes);
        ButterKnife.bind(this);
        initTitle();
        if(!CloseActivityClass.activityList.contains(this))
        {
            CloseActivityClass.activityList.add(this);
        }
    }

    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle("佣金说明");
        tv_1.setText(getContent());
        tv_2.setText(getAssCount());
    }

    /**
     * 获取json数据
     * @return
     */
    private String getContent()
    {

        return getIntent().getStringExtra("content");
    }
    /**
     * 获取json数据
     * @return
     */
    private String getAssCount()
    {

        return getIntent().getStringExtra("asscount");
    }
}
