package ercs.com.ercshouseresources.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.king.base.adapter.ViewHolderAdapter;
import com.king.base.adapter.holder.ViewHolder;

import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.bean.RenSelectListBean;

/**
 * Created by Administrator on 2017/8/14.
 */

public class RenDesignAreaoPopAdapter extends ViewHolderAdapter<RenSelectListBean.DataBean.AreaTagBean> {
    private Context context;

    public RenDesignAreaoPopAdapter(Context context, List<RenSelectListBean.DataBean.AreaTagBean> listData) {
        super(context, listData);
        this.context = context;
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, RenSelectListBean.DataBean.AreaTagBean string, int position) {
        return inflate(R.layout.adapter_rendesignpop);
    }

    @Override
    public void bindViewDatas(ViewHolder holder, RenSelectListBean.DataBean.AreaTagBean string, final int position) {
        holder.setText(R.id.tv_content, string.getValue());

    }
}
