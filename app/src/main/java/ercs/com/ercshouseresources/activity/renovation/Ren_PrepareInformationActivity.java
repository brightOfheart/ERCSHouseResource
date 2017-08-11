package ercs.com.ercshouseresources.activity.renovation;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.util.TitleControl;

/**
 * Created by Administrator on 2017/8/11.
 * 装修报备信息
 */

public class Ren_PrepareInformationActivity extends BaseActivity {
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_areao)
    TextView tv_areao;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.tv_remarks)
    TextView tv_remarks;
    @BindView(R.id.btn_sure)
    Button btn_sure;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepareinformation);
        ButterKnife.bind(this);
        initTitle();
    }

    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle("报备信息");
    }
}
