package ercs.com.ercshouseresources.activity.field;
import android.os.Bundle;
import com.baidu.mapapi.map.MapView;
import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.util.TitleControl;
/**
 * Created by Administrator on 2017/7/5.
 * 外勤打卡
 */

public class FieldClockInActivity extends BaseActivity {
    @BindView(R.id.bmapView)
    MapView mMapView;//百度地图

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fieldclcokin);
        ButterKnife.bind(this);
        initTitle();
    }

    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle(getString(R.string.str_fieldclockin));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
}
