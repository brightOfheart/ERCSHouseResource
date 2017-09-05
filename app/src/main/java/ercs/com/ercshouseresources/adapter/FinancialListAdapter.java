package ercs.com.ercshouseresources.adapter;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import com.king.base.adapter.ViewHolderRecyclerAdapter;
import com.king.base.adapter.holder.ViewHolder;
import java.util.List;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.financial.FinancialListActivity;
import ercs.com.ercshouseresources.activity.financial.FinanicalDetailActivity;
import ercs.com.ercshouseresources.bean.FinancialListBean;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.imageUtil.GlideUtil;

/**
 * Created by Administrator on 2017/8/17.
 */

public class FinancialListAdapter extends ViewHolderRecyclerAdapter<FinancialListBean.DataBean> {
    private Context context;
    private Activity activity;
    private String title;
    private String content;

    public FinancialListAdapter(Activity activity, Context context, List<FinancialListBean.DataBean> Data) {
        super(context, Data);
        this.context = context;
        this.activity = activity;
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, int viewType) {
        return inflate(R.layout.adapter_financiallist);
    }

    @Override
    public void bindViewDatas(final ViewHolder holder, final FinancialListBean.DataBean datas, final int position) {
        GlideUtil.loadImages(context, NetHelperNew.URL + datas.getIcon(), (ImageView) holder.getView(R.id.iv_pic));
        for (int i = 0; i < datas.getAttr().size(); i++) {
            if (datas.getAttr().get(i).getAttrKey().equals("k0")) {
                holder.setText(R.id.tv_gjName, datas.getText());
                holder.setText(R.id.tv_gjSub, datas.getAttr().get(i).getAttrValue());
            } else if (datas.getAttr().get(i).getAttrKey().equals("k1")) {
                holder.setText(R.id.tv_jzName, datas.getAttr().get(i).getAttrValue());
                holder.setText(R.id.tv_jzSub, datas.getAttr().get(i).getName());
            } else if (datas.getAttr().get(i).getAttrKey().equals("k2")) {
                holder.setText(R.id.tv_lvName, datas.getAttr().get(i).getAttrValue());
                holder.setText(R.id.tv_lvSub, datas.getAttr().get(i).getName());
            }
        }
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!datas.getChildren().equals("0"))
                    FinancialListActivity.start(activity, datas.getId(), datas.getText());
                else
                    FinanicalDetailActivity.start(activity, datas.getText(), datas.getAttr().get(num(datas.getAttr())).getName(), datas.getAttr().get(num(datas.getAttr())).getAttrValue(), datas.getId());
            }
        });

    }

    private int num(List<FinancialListBean.DataBean.attrBean> list) {
        int m = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getAttrKey().equals("k3")) {
                m = i;
                break;
            }
        }
        return m;

    }
}
