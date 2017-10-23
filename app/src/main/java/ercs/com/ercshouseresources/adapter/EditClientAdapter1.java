package ercs.com.ercshouseresources.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.Text;
import ercs.com.ercshouseresources.bean.ClientTelBean;

/**
 * Created by Administrator on 2017/10/9.
 */

public class EditClientAdapter1 extends BaseAdapter {
    private Context context;
    private List<ClientTelBean> listData;
    public EditClientAdapter1(Context context, List<ClientTelBean> listData) {

        this.context=context;
        this.listData=listData;

    }


    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int i) {
        return listData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view= LayoutInflater.from(context).inflate(R.layout.item_add_clients,null);
        final TextView editText=view.findViewById(R.id.et_tel);
        editText.setText(listData.get(i).getTel());
        return view;
    }



}
