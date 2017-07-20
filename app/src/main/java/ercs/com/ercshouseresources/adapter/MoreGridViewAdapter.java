package ercs.com.ercshouseresources.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.king.base.adapter.ViewHolderAdapter;
import com.king.base.adapter.holder.ViewHolder;

import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.bean.UserDictionaryBean;

/**
 * 更多
 * Created by Administrator on 2017/7/18.
 */

public class MoreGridViewAdapter extends ViewHolderAdapter<UserDictionaryBean.DataBean>{

    private int selectedPos;//选中的id 没有为-1

    public MoreGridViewAdapter(Context context, List<UserDictionaryBean.DataBean> listData, int selectedPos) {
        super(context, listData);
        this.selectedPos = selectedPos;
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, UserDictionaryBean.DataBean s, int position) {
        return layoutInflater.inflate(R.layout.item_more_gridview,null);
    }

    @Override
    public void bindViewDatas(ViewHolder holder, UserDictionaryBean.DataBean s, int position) {
           holder.setText(R.id.tv_content,s.getText());

           if (position==selectedPos)
           {
               //选中的项
               holder.getView(R.id.tv_content).setBackgroundResource(R.drawable.tv_system_smallbg);
               ((TextView)holder.getView(R.id.tv_content)).setTextColor(context.getResources().getColor(R.color.black));
           }else
           {
               holder.getView(R.id.tv_content).setBackgroundResource(R.drawable.tv_gary_smallbg);
               ((TextView)holder.getView(R.id.tv_content)).setTextColor(context.getResources().getColor(R.color.black3));

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
