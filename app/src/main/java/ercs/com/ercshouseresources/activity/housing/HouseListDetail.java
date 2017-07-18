package ercs.com.ercshouseresources.activity.housing;

import android.os.Bundle;
import android.widget.TextView;
import com.stx.xhb.xbanner.XBanner;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.view.CustomBanner;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;

/**
 * Created by Administrator on 2017/7/18.
 * 房源详情页面
 */

public class HouseListDetail extends BaseActivity {
    private LoadingDialog dialog;
    @BindView(R.id.banner)
    XBanner xBanner;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_sale)//售
            TextView tv_sale;
    @BindView(R.id.tv_lease)//租
            TextView tv_lease;
    @BindView(R.id.tv_state)//状态
            TextView tv_state;
    @BindView(R.id.tv_type)//房型
            TextView tv_type;
    @BindView(R.id.tv_area)//面积
            TextView tv_area;
    @BindView(R.id.tv_houseNo)//房源编号
            TextView tv_houseNo;
    @BindView(R.id.tv_transaction)//交易
            TextView tv_transaction;
    @BindView(R.id.tv_cityp)//城区
            TextView tv_cityp;
    @BindView(R.id.tv_aread)//片区
            TextView tv_aread;
    @BindView(R.id.tv_floor)//楼层
            TextView tv_floor;
    @BindView(R.id.tv_housenumber)//房号
            TextView tv_housenumber;
    @BindView(R.id.tv_oration)//朝向
            TextView tv_oration;
    @BindView(R.id.tv_estateDictionary)//楼盘字典
            TextView tv_estateDictionary;
    @BindView(R.id.tv_blockSeat)//栋座位置
            TextView tv_blockSeat;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_houselistdatail);
        ButterKnife.bind(this);
        initTitle();
        setbanner();
    }

    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle(getString(R.string.str_allprocess));
        dialog = new LoadingDialog(HouseListDetail.this, 0);

    }

    /**
     * 设置首页广告页
     */
    private void setbanner() {
        final List<String> imgesUrl = new ArrayList<>();
        imgesUrl.add("http://imageprocess.yitos.net/images/public/20160906/1291473163104906.jpg");
        imgesUrl.add("http://imageprocess.yitos.net/images/public/20160910/99381473502384338.jpg");
        imgesUrl.add("http://imageprocess.yitos.net/images/public/20160910/77991473496077677.jpg");
        imgesUrl.add("http://imageprocess.yitos.net/images/public/20160906/1291473163104906.jpg");
        new CustomBanner(this, xBanner, imgesUrl);

    }
}
