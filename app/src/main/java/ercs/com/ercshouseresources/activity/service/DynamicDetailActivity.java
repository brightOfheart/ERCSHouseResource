package ercs.com.ercshouseresources.activity.service;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.adapter.DynamicDetailAdapter;
import ercs.com.ercshouseresources.util.CloseActivityClass;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.view.NoScrollListView;


/**
 * Created by Administrator on 2017/8/5.
 * 动态详情
 */

public class DynamicDetailActivity extends BaseActivity{
    @BindView(R.id.listview)
    NoScrollListView listview;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_subtitle)
    TextView tv_subtitle;

    /**
     * 页面跳转
     */
    public static void start(Activity mactivity, String title, String subtitle, ArrayList<String> path) {
        Intent intent = new Intent(mactivity, DynamicDetailActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("subtitle", subtitle);
        intent.putStringArrayListExtra("path",path);
        mactivity.startActivity(intent);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamicdetail);
        ButterKnife.bind(this);
        initTitle();
        createData();
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
        t.setTitle("动态详情");
    }

    private void createData()
    {
        tv_title.setText(getTitles());
        tv_subtitle.setText(getSubTitles());
        listview.setAdapter(new DynamicDetailAdapter(this,getPathList()));
    }
    private String getTitles()
    {
        return getIntent().getStringExtra("title");
    }
    private String getSubTitles()
    {
        return getIntent().getStringExtra("subtitle");
    }
    private ArrayList<String> getPathList()
    {
        return getIntent().getStringArrayListExtra("path");
    }
}
