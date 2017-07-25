package ercs.com.ercshouseresources.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.king.base.adapter.ViewHolderAdapter;
import com.king.base.adapter.holder.ViewHolder;

import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.bean.ClientTelBean;

/**
 * 添加客户
 * Created by Administrator on 2017/7/24.
 */

public class AddClientAdapter extends BaseAdapter {
    private Context context;
    private OnDataChangeListener onDataChangeListener;
    private  List<ClientTelBean> listData;
    public AddClientAdapter(Context context, List<ClientTelBean> listData,OnDataChangeListener onDataChangeListener) {

        this.context=context;
        this.listData=listData;
        this.onDataChangeListener=onDataChangeListener;
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
        view=LayoutInflater.from(context).inflate(R.layout.item_add_client,null);


        final EditText editText=view.findViewById(R.id.et_tel);
        editText.setText(listData.get(i).getTel());


        final TextView tv_add=view.findViewById(R.id.tv_add);
        if (listData.size()==i+1)
        {
            //变+
            tv_add.setText(context.getString(R.string.str_add));
        }else
        {
            tv_add.setText(context.getString(R.string.str_minus));

        }

        /**
         * 点击添加或减少时
         */
        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("+".equals(tv_add.getText().toString()))
                {
                    listData.add(new ClientTelBean(""));
                    onDataChangeListener.getData(listData);
                }else
                {
                    listData.remove(i);
                    onDataChangeListener.getData(listData);
                }

            }
        });

        /**
         * 输入框改变时更新数据
         */
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                listData.get(i).setTel(editText.getText().toString());
            }
        });
        return view;
    }

    /**
     * 点击添加或减少时回调数据
     */
    public interface OnDataChangeListener
    {
        void getData(List<ClientTelBean> list);
    }

    /**
     * 获取电话数组
     * @return
     */
    public List<ClientTelBean> getListData() {
        return listData;
    }
}
