package ercs.com.ercshouseresources.activity.financial;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.activity.renovation.RenovationListActivity;
import ercs.com.ercshouseresources.activity.service.NewHouseActivity;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.bean.BannerBean;
import ercs.com.ercshouseresources.bean.FinancialBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.CloseActivityClass;
import ercs.com.ercshouseresources.util.NetWorkUtil;
import ercs.com.ercshouseresources.util.SPUtil;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.view.HorizontalScorllTv;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;
import ercs.com.ercshouseresources.view.dialog.PicDialog;
import ercs.com.ercshouseresources.view.item.FinancialItem;

/**
 * Created by Administrator on 2017/8/17.
 */

public class FinancialActivity extends BaseActivity {
    private LoadingDialog dialog;
    @BindView(R.id.ly_all)
    LinearLayout ly_all;
    @BindView(R.id.ly_top)
    LinearLayout ly_top;
    private String Imagepath = "";
    private String Imagepathid = "";
    private SPUtil spUtil;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial);
        ButterKnife.bind(this);
        initTitle();
        if (!CloseActivityClass.activityList.contains(this)) {
            CloseActivityClass.activityList.add(this);
        }
        if (NetWorkUtil.check(getApplicationContext())) {
            getBanner();
            loadData();
        }

    }

    private void getBanner() {
        NetHelperNew.getBanner("5", new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                final BannerBean bannerBean = MyGson.getInstance().fromJson(data, BannerBean.class);
                if (bannerBean.getType().equals("1")) {
                    List<String> list = new ArrayList<String>();
                    List<String> listid = new ArrayList<String>();
                    for (int i = 0; i < bannerBean.getData().size(); i++) {
                        if (bannerBean.getData().get(i).getAdvertisementType().equals("1")) {
                            list.add(bannerBean.getData().get(i).getText() + "   ");
                            listid.add(bannerBean.getData().get(i).getDynamicID());
                        } else {
                            Imagepath = bannerBean.getData().get(i).getImagePath().get(0);
                            Imagepathid = bannerBean.getData().get(i).getDynamicID();
                        }
                    }

                    if (bannerBean.getData().size() > 0) {
                        loadBanner(list, listid);
                    } else {
                        ly_top.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
            }
        });
    }

    private void loadBanner(List<String> list, List<String> listid) {
        HorizontalScorllTv horizontalScorllTextView = new HorizontalScorllTv(FinancialActivity.this, list, listid);
        ly_top.addView(horizontalScorllTextView);
        if (BaseApplication.FINANCIALOPEN.equals("0")) {
            if (!Imagepath.equals("")) {
                PicDialog picDialog = new PicDialog(FinancialActivity.this, R.style.mydialog, Imagepath, Imagepathid);
                picDialog.show();
                BaseApplication.FINANCIALOPEN = "1";
            }

        }
    }

    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle("金融");
        dialog = new LoadingDialog(FinancialActivity.this, 0);
        spUtil = new SPUtil(FinancialActivity.this, "fileName");
    }

    private void loadData() {
        dialog.show();
        NetHelperNew.getFinancial("0", new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                dialog.dismiss();
                final FinancialBean financialBean = MyGson.getInstance().fromJson(data, FinancialBean.class);
                if (financialBean.getType().equals("1")) {
                    createview(financialBean);
                }
            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
                dialog.dismiss();
                ToastUtil.showToast(FinancialActivity.this, msg);
            }
        });
    }

    private void createview(FinancialBean financialBean) {
        for (int i = 0; i < financialBean.getData().size(); i++) {
            FinancialItem financialItem = new FinancialItem(FinancialActivity.this, (i + 1) + "F", financialBean.getData().get(i).getText(), financialBean.getData().get(i).getId());
            ly_all.addView(financialItem);
        }

    }
}
