package ercs.com.ercshouseresources.view.dialog;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.adapter.ConfirmationClientsAdapter;

/**
 * 确认报备信息
 * Created by Administrator on 2017/7/26.
 */

public class ConfirmationClientsDialog extends BaseDialog {

    private TextView tv_name;
    private TextView tv_quit;//取消键
    private TextView tv_sure;//确定
    private ListView listView;

    private String name;
    private List<String> tels;

    private Activity context;
    private ConfirmationClientsAdapter confirmationClientsAdapter;

    public ConfirmationClientsDialog(Activity mActivity, String name, List<String> tels) {
        super(mActivity);
        this.name = name;
        this.tels = tels;
        context=mActivity;
    }

    @Override
    public int getAnimStyle() {
        return 0;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.dialog_comfirmation_clients;
    }

    @Override
    public void initView() {
        tv_name=findViewById(R.id.tv_name);
        tv_quit=findViewById(R.id.tv_quit);
        tv_sure=findViewById(R.id.tv_sure);
        listView=findViewById(R.id.listview);

        confirmationClientsAdapter = new ConfirmationClientsAdapter(context,tels);
        listView.setAdapter(confirmationClientsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                confirmationClientsAdapter.setPos(i);
                confirmationClientsAdapter.notifyDataSetChanged();
            }
        });
    }
}
