package ercs.com.ercshouseresources.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.king.base.adapter.ViewHolderAdapter;
import com.king.base.adapter.holder.ViewHolder;

import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.bean.PriceBean;

/**
 * Created by Administrator on 2017/7/17.
 */

public class PriceSelectListviewAdapter extends ViewHolderAdapter<PriceBean> {


    private int selectedPos=-1;//选中的id 没有为-1
    public PriceSelectListviewAdapter(Context context, List<PriceBean> listData) {
        super(context, listData);
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, PriceBean priceBean, int position) {
        return layoutInflater.inflate(R.layout.item_priceselect_listview,null);
    }

    @Override
    public void bindViewDatas(ViewHolder holder, PriceBean priceBean, int position) {

        holder.setText(R.id.tv_content,priceBean.getTitle());

        if (position==selectedPos)
        {
            //选中的项
            ((TextView)holder.getView(R.id.tv_content)).setTextColor(context.getResources().getColor(R.color.system_color));
        }else
        {
            ((TextView)holder.getView(R.id.tv_content)).setTextColor(context.getResources().getColor(R.color.black));


        }
    }

    /**
     * 设置选中
     * @param i
     */
    public void setSelected(int i)
    {
        selectedPos=i;
        notifyDataSetChanged();
    }

    /**
     * 设置选中id
     */
    public int getSelectedPos() {
        return selectedPos;
    }
}
