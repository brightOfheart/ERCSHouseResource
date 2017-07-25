package ercs.com.ercshouseresources.activity.service;
import android.os.Bundle;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.view.item.ContentListItem;

/**
 * Created by Administrator on 2017/7/24.
 * 庄园列表
 */

public class ContentListActivity extends BaseActivity {
    @BindView(R.id.ly_all)
    LinearLayout ly_all;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contentlist);
        ButterKnife.bind(this);
        initTitle();
        initview();
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
        for (int i = 0; i < 5; i++) {
            ly_all.addView(new ContentListItem(this));
        }
    }
}
