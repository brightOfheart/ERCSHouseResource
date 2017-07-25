package ercs.com.ercshouseresources.activity.service;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.adapter.AddClientAdapter;
import ercs.com.ercshouseresources.bean.ClientTelBean;
import ercs.com.ercshouseresources.util.SPUtil;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.view.NoScrollListView;

/**
 * 添加客户
 */
public class AddClientActivity extends AppCompatActivity {

    @BindView(R.id.listview)
    NoScrollListView listView;
    @BindView(R.id.radio_man)
    RadioButton radio_man;
    @BindView(R.id.radio_woman)
    RadioButton radio_woman;
    private List<ClientTelBean> list;
    private AddClientAdapter addClientAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);
        ButterKnife.bind(this);
        initTitle();
        initView();
    }

    private void initView() {
        list=new ArrayList<>();
        list.add(new ClientTelBean(""));
        list.add(new ClientTelBean(""));
        addClientAdapter = new AddClientAdapter(this, list, new AddClientAdapter.OnDataChangeListener() {
            @Override
            public void getData(List<ClientTelBean> lists) {
                list=lists;
                addClientAdapter.notifyDataSetChanged();
            }
        });
        listView.setAdapter(addClientAdapter);

        radio_man.setChecked(true);
    }

    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle(getString(R.string.str_addclient));

        t.setRightText("导入", new TitleControl.OnClickRight() {
            @Override
            public void onRight() {

            }
        });
    }
}
