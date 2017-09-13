package ercs.com.ercshouseresources.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.king.base.adapter.ViewHolderAdapter;
import com.king.base.adapter.holder.ViewHolder;

import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.PhotoViewActivity;
import ercs.com.ercshouseresources.bean.FieldCustomContentBean;
import ercs.com.ercshouseresources.network.NetHelper;
import ercs.com.ercshouseresources.util.imageUtil.GlideUtil;

/**
 * Created by Administrator on 2017/9/13.
 */

public class ScroAdapter extends ViewHolderAdapter<String> {
    private Context context;

    public ScroAdapter(Context context, List<String> listData) {
        super(context, listData);
        this.context = context;
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, String dataBean, int position) {
        return inflate(R.layout.adapter_scrov);
    }

    @Override
    public void bindViewDatas(ViewHolder holder, final String dataBean, final int position) {
        holder.setText(R.id.tv, dataBean);

    }
}
