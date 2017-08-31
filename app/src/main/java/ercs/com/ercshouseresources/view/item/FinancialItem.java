package ercs.com.ercshouseresources.view.item;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.financial.FinancialListActivity;
import ercs.com.ercshouseresources.adapter.FinancialItemAdapter;
import ercs.com.ercshouseresources.bean.FinancialChildBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.view.MyGridView;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;

import static android.R.id.list;

/**
 * Created by Administrator on 2017/8/17.
 */

public class FinancialItem extends RelativeLayout {
    private LinearLayout linearLayout;
    @BindView(R.id.tv_floor)
    TextView tv_floor;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.gridview)
    MyGridView gridview;
    @BindView(R.id.ly_top)
    LinearLayout ly_top;
    private LoadingDialog dialog;
    private Activity context;
    private boolean b = false;
    private boolean isclick = true;

    public FinancialItem(Activity context, String mode, final String title, final String id) {
        super(context);
        this.context = context;
        LayoutInflater mInflater = LayoutInflater.from(context);
        linearLayout = (LinearLayout) mInflater.inflate(
                R.layout.item_financial, null);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 0);
        this.addView(linearLayout, params);
        ButterKnife.bind(this);
        tv_floor.setText(mode);
        tv_title.setText(title);
        ly_top.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!b) {
                    loaddata(id);
                    gridview.setVisibility(View.VISIBLE);
                    b = true;
                } else {
                    if (isclick) {
                        gridview.setVisibility(View.GONE);
                        isclick = false;
                    } else {
                        gridview.setVisibility(View.VISIBLE);
                        isclick = true;
                    }
                }

            }
        });

    }

    private void loaddata(String id) {
        dialog = new LoadingDialog(context, 0);
        dialog.show();
        NetHelperNew.getFinancial(id, new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                dialog.dismiss();
                final FinancialChildBean financialChildBean = MyGson.getInstance().fromJson(data, FinancialChildBean.class);
                if (financialChildBean.getType().equals("1")) {
                    creatview(financialChildBean);
                }
            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
                dialog.dismiss();
                ToastUtil.showToast(context, msg);
            }
        });
    }

    private void creatview(final FinancialChildBean financialChildBean) {
        gridview.setAdapter(new FinancialItemAdapter(context, financialChildBean.getData()));

    }
}
