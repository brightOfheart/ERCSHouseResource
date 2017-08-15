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

public class RenDesignStylePopAdapter extends ViewHolderAdapter<RenSelectListBean.DataBean.StyleBean> {
    private Context context;

    public RenDesignStylePopAdapter(Context context, List<RenSelectListBean.DataBean.StyleBean> listData) {
        super(context, listData);
        this.context = context;
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, RenSelectListBean.DataBean.StyleBean string, int position) {
        return inflate(R.layout.adapter_rendesignpop);
    }

    @Override
    public void bindViewDatas(ViewHolder holder, RenSelectListBean.DataBean.StyleBean string, final int position) {
        holder.setText(R.id.tv_content, string.getValue());

    }
}
