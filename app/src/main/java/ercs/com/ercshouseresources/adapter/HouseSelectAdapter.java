package ercs.com.ercshouseresources.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.king.base.adapter.ViewHolderAdapter;
import com.king.base.adapter.holder.ViewHolder;

import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.view.popupwindow.MorePop;

/**
 * Created by Administrator on 2017/10/11.
 */

public class HouseSelectAdapter extends ViewHolderAdapter<String> {

    private int selectedPos;//选中的id 没有为-1


    public HouseSelectAdapter(Context context, List<String> listData) {
        super(context, listData);

    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, String dataBean, int position) {
        return layoutInflater.inflate(R.layout.adapter_more, null);
    }

    @Override
    public void bindViewDatas(ViewHolder holder, final String s, final int position) {
        holder.setText(R.id.tv_content, s);
        if (position == selectedPos) {
            //选中的项
            ((TextView) holder.getView(R.id.tv_content)).setTextColor(context.getResources().getColor(R.color.system_color));
            ((TextView) holder.getView(R.id.tv_content)).setBackgroundResource(R.drawable.corners_bgnos);
        } else {
            ((TextView) holder.getView(R.id.tv_content)).setTextColor(context.getResources().getColor(R.color.black));
            ((TextView) holder.getView(R.id.tv_content)).setBackgroundResource(R.drawable.corners_bgno);
        }
    }

    /**
     * 设置选中
     *
     * @param i
     */
    public void setSelected(int i) {
        selectedPos = i;
        notifyDataSetChanged();
    }

    /**
     * 设置选中id
     */
    public int getSelectedPos() {
        return selectedPos;
    }


}
