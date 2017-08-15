package ercs.com.ercshouseresources.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.king.base.adapter.ViewHolderAdapter;
import com.king.base.adapter.holder.ViewHolder;

import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.bean.RenSelectListBean;

/**
 * Created by Administrator on 2017/8/11.
 */

public class RenDesignPopAdapter extends ViewHolderAdapter<RenSelectListBean.DataBean.HouseTypeBean> {
    private Context context;

    public RenDesignPopAdapter(Context context, List<RenSelectListBean.DataBean.HouseTypeBean> listData) {
        super(context, listData);
        this.context = context;
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, RenSelectListBean.DataBean.HouseTypeBean string, int position) {
        return inflate(R.layout.adapter_rendesignpop);
    }

    @Override
    public void bindViewDatas(ViewHolder holder, RenSelectListBean.DataBean.HouseTypeBean string, final int position) {
        holder.setText(R.id.tv_content, string.getValue());
//        final TextView tv= holder.getView(R.id.tv_content);
//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Drawable drawale = context.getResources().getDrawable(R.drawable.tv_backs);
//                tv.setBackgroundDrawable(drawale);
//                tv.setTextColor(Color.parseColor("#1AB394"));
//            }
//        });
    }
}
