package ercs.com.ercshouseresources.activity.cheaproom;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import java.text.ParseException;
import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.service.PropertyDetailActivity;
import ercs.com.ercshouseresources.bean.CheapRoomDetailBean;
import ercs.com.ercshouseresources.bean.CommissionExplainBean;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.util.TimeUtil;
import ercs.com.ercshouseresources.util.TitleControl;

/**
 * Created by Administrator on 2017/8/8.
 * 小区详情
 */

public class ResidentialDetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_developers)
    TextView tv_developers;//开发商
    @BindView(R.id.tv_referenceprice)
    TextView tv_referenceprice;//参考均价
    @BindView(R.id.tv_openingtime)
    TextView tv_openingtime;//开盘时间
    @BindView(R.id.tv_expressingtime)
    TextView tv_expressingtime;//交房时间
    @BindView(R.id.tv_buildingclass)
    TextView tv_buildingclass;//建筑类型
    @BindView(R.id.tv_decorationcondition)
    TextView tv_decorationcondition;//装修状况
    @BindView(R.id.tv_coveredarea)
    TextView tv_coveredarea;//建筑面积
    @BindView(R.id.tv_totalhouseholds)
    TextView tv_totalhouseholds;//总户数
    @BindView(R.id.tv_plotratio)
    TextView tv_plotratio;//容积率
    @BindView(R.id.tv_greeningratio)
    TextView tv_greeningratio;//绿化率
    @BindView(R.id.tv_carnumber )
    TextView  tv_carnumber ;//车位数
    @BindView(R.id.tv_propertycompany )
    TextView  tv_propertycompany ;//物业公司
    @BindView(R.id.tv_propertyfee )
    TextView  tv_propertyfee ;//物业费

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_detail);
        ButterKnife.bind(this);

        setData();
    }

    /**
     * 填充数据
     */
    private void setData() {

        CheapRoomDetailBean baseInfo = MyGson.getInstance().fromJson(getjsonString(), CheapRoomDetailBean.class);


        //标题
        TitleControl titleControl = new TitleControl(this);
        titleControl.setTitle(baseInfo.getData().getModel().getBuildingName());

        tv_developers.setText(baseInfo.getData().getModel().getDeveloper());
        tv_referenceprice.setText("000");

        try {
            tv_openingtime.setText(TimeUtil.dealDateFormatChinese(baseInfo.getData().getModel().getOpeningTime()));
            tv_expressingtime.setText(TimeUtil.dealDateFormatChinese(baseInfo.getData().getModel().getLaunchTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        tv_buildingclass.setText(baseInfo.getData().getModel().getBuildingClass());
        tv_decorationcondition.setText(baseInfo.getData().getModel().getDecorationCondition());
        tv_coveredarea.setText(baseInfo.getData().getModel().getCoveredArea());
        tv_totalhouseholds.setText(baseInfo.getData().getModel().getTotalHouseholds());
        tv_plotratio.setText(baseInfo.getData().getModel().getPlotRatio());
        tv_greeningratio.setText(baseInfo.getData().getModel().getGreeningRatio());
        tv_carnumber.setText(baseInfo.getData().getModel().getCarNumber());
        tv_propertycompany.setText(baseInfo.getData().getModel().getPropertyManagementCompany());
        tv_propertyfee.setText(baseInfo.getData().getModel().getPropertyManagementFee());
    }

    /**
     * 页面跳转
     */
    public static void start(Activity mactivity, String jsonString) {
        Intent intent = new Intent(mactivity, ResidentialDetailActivity.class);
        intent.putExtra("jsonString", jsonString);
        mactivity.startActivity(intent);
    }

    /**
     * 获取json数据
     *
     * @return
     */
    private String getjsonString() {

        return getIntent().getStringExtra("jsonString");
    }
}
