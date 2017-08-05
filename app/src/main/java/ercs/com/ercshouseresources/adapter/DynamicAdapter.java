package ercs.com.ercshouseresources.adapter;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import com.king.base.adapter.ViewHolderRecyclerAdapter;
import com.king.base.adapter.holder.ViewHolder;

import java.util.ArrayList;
import java.util.List;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.service.DynamicDetailActivity;
import ercs.com.ercshouseresources.bean.DynamicBean;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.imageUtil.GlideUtil;

/**
 * Created by Administrator on 2017/7/24.
 */

public class DynamicAdapter extends ViewHolderRecyclerAdapter<DynamicBean.DataBean> {
    private Context context;
    private Activity activity;

    public DynamicAdapter(Activity activity, Context context, List<DynamicBean.DataBean> Data) {
        super(context, Data);
        this.context = context;
        this.activity = activity;
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, int viewType) {
        return inflate(R.layout.item_dynamic);
    }

    @Override
    public void bindViewDatas(final ViewHolder holder,final DynamicBean.DataBean datas, final int position) {
        holder.setText(R.id.tv_title, datas.getTitle());
        holder.setText(R.id.tv_subtitle, datas.getSubject());
        for (int i = 0; i < datas.getImagePath().size(); i++) {
            if (i == 0)
                GlideUtil.loadImage(context, NetHelperNew.URL + datas.getImagePath().get(i) , (ImageView) holder.getView(R.id.iv_1));
            else if (i == 1)
                GlideUtil.loadImage(context, NetHelperNew.URL + datas.getImagePath().get(i), (ImageView) holder.getView(R.id.iv_2));
            else if (i == 2)
                GlideUtil.loadImage(context, NetHelperNew.URL + datas.getImagePath().get(i), (ImageView) holder.getView(R.id.iv_3));
        }
        holder.setText(R.id.tv_time, datas.getReleaseTime());
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> list= (ArrayList<String>) datas.getImagePath();
                DynamicDetailActivity.start(activity,datas.getTitle(),datas.getSubject(),list);
            }
        });
    }
}
