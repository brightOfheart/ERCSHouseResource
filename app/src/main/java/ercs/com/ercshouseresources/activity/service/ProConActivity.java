package ercs.com.ercshouseresources.activity.service;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import java.io.Serializable;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.adapter.ProConAdapter;
import ercs.com.ercshouseresources.bean.NewHouseDetailBean;
import ercs.com.ercshouseresources.util.CloseActivityClass;
import ercs.com.ercshouseresources.util.TitleControl;


/**
 * Created by Administrator on 2017/7/24.
 * 置业顾问
 */

public class ProConActivity extends BaseActivity {
    @BindView(R.id.recyleview)
    ListView recyleview;

    /**
     * 页面跳转
     */
    public static void start(Activity mactivity, List<NewHouseDetailBean.DataBean.PropertyConsultantList> list) {
        Intent intent = new Intent(mactivity, ProConActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("serinfo", (Serializable) list);
        intent.putExtras(bundle);
        mactivity.startActivity(intent);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procon);
        ButterKnife.bind(this);
        initTitle();
        initview();
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
        t.setTitle(getString(R.string.str_procon));

    }

    /**
     * 初始化
     */
    private void initview() {


        recyleview.setAdapter(new ProConAdapter(ProConActivity.this, this, getList()));
    }

    private List<NewHouseDetailBean.DataBean.PropertyConsultantList> getList()
    {

        return (List<NewHouseDetailBean.DataBean.PropertyConsultantList>) getIntent().getSerializableExtra("serinfo");
    }
}
