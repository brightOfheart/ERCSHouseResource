package ercs.com.ercshouseresources.activity.renovation;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.view.item.Ren_ResginDetailItem;

/**
 * Created by Administrator on 2017/8/11.
 * 设计方案详情
 */

public class Ren_resignDetailActivity extends BaseActivity {
    @BindView(R.id.tv_content)
    TextView tv_content;
    @BindView(R.id.tv_price)
    TextView tv_price;
    @BindView(R.id.tv_housetype)
    TextView tv_housetype;
    @BindView(R.id.tv_area)
    TextView tv_area;
    @BindView(R.id.tv_date)
    TextView tv_date;
    @BindView(R.id.tv_teacher)
    TextView tv_teacher;
    @BindView(R.id.tv_desginstyle)
    TextView tv_desginstyle;
    @BindView(R.id.tv_worktype)
    TextView tv_worktype;
    @BindView(R.id.ly_all)
    LinearLayout ly_all;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resigndetail);
        ButterKnife.bind(this);
        initTitle();
    }

    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle("设计方案详情");
    }

    private void createview() {
        Ren_ResginDetailItem ren_resginDetailItem = new Ren_ResginDetailItem(this);
        ly_all.addView(ren_resginDetailItem);
    }
}
