package ercs.com.ercshouseresources.activity.service;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.adapter.DynamicAdapter;
import ercs.com.ercshouseresources.adapter.ProConAdapter;
import ercs.com.ercshouseresources.util.TitleControl;

/**
 * Created by Administrator on 2017/7/24.
 * 动态
 */

public class DynamicActivity extends BaseActivity {
    @BindView(R.id.recyleview)
    LRecyclerView recyleview;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic);
        ButterKnife.bind(this);
        initTitle();
        initview();
    }
    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle(getString(R.string.str_dynamic));

    }

    /**
     * 初始化
     */
    private void initview() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(new DynamicAdapter(DynamicActivity.this, this, list));
        recyleview.setLayoutManager(new LinearLayoutManager(this));
        recyleview.setAdapter(mLRecyclerViewAdapter);
        recyleview.setPullRefreshEnabled(false);
    }
}
