package ercs.com.ercshouseresources.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.king.base.adapter.ViewHolderRecyclerAdapter;
import com.king.base.adapter.holder.ViewHolder;

import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.bean.HouseListBean;
import ercs.com.ercshouseresources.bean.ProcessBean;
import ercs.com.ercshouseresources.fragment.HouseFragment;
import ercs.com.ercshouseresources.util.imageUtil.GlideUtil;

/**
 * Created by Administrator on 2017/7/12.
 * 房源列表的Adapter
 */

public class HouseAdapter extends ViewHolderRecyclerAdapter<HouseListBean.DataBean> {

    private Context context;
    public HouseAdapter( Context context, List<HouseListBean.DataBean> listData) {
        super(context, listData);
        this.context=context;
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, int viewType) {
        return inflate(R.layout.adapter_house);
    }

    @Override
    public void bindViewDatas(final ViewHolder holder, HouseListBean.DataBean datas, final int position) {
        GlideUtil.loadImage(context,"", (ImageView) holder.getView(R.id.iv_goods),R.mipmap.ic_launcher,R.mipmap.ic_launcher);
        holder.setText(R.id.tv_housename,datas.getEstateName());
        String content=datas.getRoomType().substring(0,datas.getRoomType().indexOf("厅")+1)+"·"+datas.getSquare()+"m²·"+datas.getOrientationName()+"·"+datas.getDataStatusName();
        holder.setText(R.id.tv_content,content);
        //售价
        holder.setText(R.id.tv_saletotal,datas.getSaleTotal()+"万");
        //租价
        holder.setText(R.id.tv_renttotal,datas.getRentTotal()+"/月");
    }
}
