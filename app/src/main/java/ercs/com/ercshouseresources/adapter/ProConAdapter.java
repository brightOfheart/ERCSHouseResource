package ercs.com.ercshouseresources.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import com.king.base.adapter.ViewHolderAdapter;
import com.king.base.adapter.holder.ViewHolder;

import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.bean.NewHouseDetailBean;


/**
 * Created by Administrator on 2017/7/24.
 */

public class ProConAdapter extends ViewHolderAdapter<NewHouseDetailBean.DataBean.PropertyConsultantList> {
    private Context context;
    private Activity activity;

    public ProConAdapter(Activity activity, Context context, List<NewHouseDetailBean.DataBean.PropertyConsultantList> listData) {
        super(context, listData);
        this.context = context;
        this.activity = activity;
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, NewHouseDetailBean.DataBean.PropertyConsultantList dataBean, int position) {
        return inflate(R.layout.item_procon);
    }

    @Override
    public void bindViewDatas(final ViewHolder holder, NewHouseDetailBean.DataBean.PropertyConsultantList datas, final int position) {
        holder.setText(R.id.tv_x, datas.getName().substring(0, 1));
        holder.setText(R.id.tv_name, datas.getName() + "  " + datas.getPhone());
        holder.setText(R.id.tv_type, datas.getDuties());

    }
}
