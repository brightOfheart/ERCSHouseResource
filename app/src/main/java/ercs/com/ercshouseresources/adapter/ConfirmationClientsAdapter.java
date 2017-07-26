package ercs.com.ercshouseresources.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ercs.com.ercshouseresources.R;

/**
 * 确认报备信息电话列表
 * Created by Administrator on 2017/7/26.
 */

public class ConfirmationClientsAdapter extends BaseAdapter{
    private List<String> list;
    private Context context;
    private int pos=0;//选中

    public ConfirmationClientsAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=LayoutInflater.from(context).inflate(R.layout.item_confirmation_clients,null);
        TextView tv_tel=view.findViewById(R.id.tv_tel);

        tv_tel.setText(list.get(i));
        if (i==pos)
        {
            tv_tel.setTextColor(context.getResources().getColor(R.color.orange));
        }else
        {
            tv_tel.setTextColor(context.getResources().getColor(R.color.gray));
        }
        return view;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
}
