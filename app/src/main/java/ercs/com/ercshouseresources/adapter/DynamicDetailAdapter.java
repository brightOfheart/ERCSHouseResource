package ercs.com.ercshouseresources.adapter;

import android.app.Activity;
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
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.imageUtil.GlideUtil;

/**
 * Created by Administrator on 2017/8/5.
 */

public class DynamicDetailAdapter  extends ViewHolderAdapter<String> {
    private Activity context;

    public DynamicDetailAdapter(Activity context, List<String> listData) {
        super(context, listData);
        this.context = context;
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, String dataBean, int position) {
        return inflate(R.layout.adapter_dynamicdetail);
    }

    @Override
    public void bindViewDatas(ViewHolder holder, final String dataBean, final int position) {

        GlideUtil.loadImage(context, NetHelperNew.URL + dataBean, (ImageView) holder.getView(R.id.iv_pic));
    }
}
