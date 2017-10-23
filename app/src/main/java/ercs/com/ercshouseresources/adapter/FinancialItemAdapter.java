package ercs.com.ercshouseresources.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.king.base.adapter.ViewHolderAdapter;
import com.king.base.adapter.holder.ViewHolder;

import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.PhotoViewActivity;
import ercs.com.ercshouseresources.activity.financial.FinancialListActivity;
import ercs.com.ercshouseresources.activity.financial.FinanicalDetailActivity;
import ercs.com.ercshouseresources.bean.FieldCustomContentBean;
import ercs.com.ercshouseresources.bean.FinancialChildBean;
import ercs.com.ercshouseresources.network.NetHelper;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.util.imageUtil.GlideUtil;

/**
 * Created by Administrator on 2017/8/17.
 */

public class FinancialItemAdapter extends ViewHolderAdapter<FinancialChildBean.DataBean> {
    private Activity context;


    public FinancialItemAdapter(Activity context, List<FinancialChildBean.DataBean> listData) {
        super(context, listData);
        this.context = context;
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, FinancialChildBean.DataBean datas, int position) {
        return inflate(R.layout.adapter_financialitem);
    }

    @Override
    public void bindViewDatas(ViewHolder holder, final FinancialChildBean.DataBean datas, final int position) {
        holder.setText(R.id.tv_title, datas.getText());
        GlideUtil.loadImagess(context, NetHelperNew.URL + "/" + datas.getIcon(), (ImageView) holder.getView(R.id.iv_pics), R.mipmap.ic_launcher);
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (datas.getMode().equals("0"))//贷款
                    FinancialListActivity.start(context, datas.getId(), datas.getText());
                if (datas.getMode().equals("1"))//二手房
                {
                    if (datas.getAttr().size() > 0) {
                        FinanicalDetailActivity.start(context, datas.getText(), datas.getAttr().get(num(datas.getAttr())).getName(), datas.getAttr().get(num(datas.getAttr())).getAttrValue(), datas.getId());
                    } else {
                        ToastUtil.showToast(context, "没有数据");
                    }
                }


            }
        });
//
    }

    private int num(List<FinancialChildBean.DataBean.attrBean> list) {
        int m = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getAttrKey().equals("k0")) {
                m = i;
                break;
            }
        }
        return m;

    }
}



