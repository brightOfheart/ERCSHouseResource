package ercs.com.ercshouseresources.activity.service;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.adapter.AddClientAdapter;
import ercs.com.ercshouseresources.adapter.EditClientAdapter;
import ercs.com.ercshouseresources.adapter.EditClientAdapter1;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.bean.BaseBean;
import ercs.com.ercshouseresources.bean.ClientTelBean;
import ercs.com.ercshouseresources.bean.CustomersListBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.CloseActivityClass;
import ercs.com.ercshouseresources.util.NetWorkUtil;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.view.NoScrollListView;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;

/**
 * Created by Administrator on 2017/10/9.
 * 编辑客户
 */

public class EditClientActivity extends BaseActivity {

    @BindView(R.id.listview)
    NoScrollListView listView;
    @BindView(R.id.listview1)
    NoScrollListView listView1;
    @BindView(R.id.edit_name)
    EditText edit_name;

    private List<ClientTelBean> list;
    private List<ClientTelBean> lists;
    private EditClientAdapter addClientAdapter;
    private EditClientAdapter1 adapter1;
    private LoadingDialog loadingDialog;

    /**
     * 页面跳转
     */
    public static void start(Activity mactivity, ArrayList<CustomersListBean.DataBean.PhoneListBean> list, String name,String id) {

        Intent intent = new Intent(mactivity, EditClientActivity.class);
        intent.putExtra("list", list);
        intent.putExtra("name", name);
        intent.putExtra("id", id);
        mactivity.startActivity(intent);
    }

    private String getName() {
        return getIntent().getStringExtra("name");
    }

    private String getId() {
        return getIntent().getStringExtra("id");
    }

    private ArrayList<CustomersListBean.DataBean.PhoneListBean> getList() {
        return (ArrayList<CustomersListBean.DataBean.PhoneListBean>) getIntent().getSerializableExtra("list");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_client);
        ButterKnife.bind(this);
        initTitle();
        initView();
        Log.d("XX", getList().size() + "/");
        if (!CloseActivityClass.activityList.contains(this)) {
            CloseActivityClass.activityList.add(this);
        }
    }

    private void initView() {
        list = new ArrayList<>();
        lists = new ArrayList<>();
        for (int i = 0; i < getList().size(); i++) {
            ClientTelBean clientTelBean = new ClientTelBean("");
            clientTelBean.setTel(getList().get(i).getPhone());
            list.add(clientTelBean);
        }

        adapter1 = new EditClientAdapter1(this, list);
        listView1.setAdapter(adapter1);
        edit_name.setText(getName());
        lists.add(new ClientTelBean(""));
        addClientAdapter = new EditClientAdapter(this, lists, new AddClientAdapter.OnDataChangeListener() {
            @Override
            public void getData(List<ClientTelBean> listss) {
                lists = listss;
                addClientAdapter.notifyDataSetChanged();
            }
        });
        listView.setAdapter(addClientAdapter);
    }

    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle("编辑客户");
        loadingDialog = new LoadingDialog(this, 0);
    }

    @OnClick({R.id.btn_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                //保存
                if ("".equals(edit_name.getText().toString())) {
                    ToastUtil.showToast(EditClientActivity.this, getString(R.string.error_inputname));
                } else if (NetWorkUtil.check(this)) {
                    addClientNet();
                }

                break;
        }
    }

    /**
     * 添加客户网络请求
     */
    private void addClientNet() {

        String tels = "";
        boolean isTelNull = true;//电话号是否为空
        for (int i = 0; i < lists.size(); i++) {
            if (i == 0) {
                tels = lists.get(i).getTel();
            } else {
                tels = tels + "|" + lists.get(i).getTel();
            }

            if (!"".equals(lists.get(i).getTel())) {
                isTelNull = false;
            }
        }

        if (isTelNull) {
            //有电话号输入
            ToastUtil.showToast(EditClientActivity.this, getString(R.string.error_tel_number));
        } else {
            loadingDialog.show();
            NetHelperNew.UpdateCustomer(getId(), tels, new HttpUtils.HttpCallback() {
                @Override
                public void onSuccess(String data) {
                    final BaseBean baseBean = MyGson.getInstance().fromJson(data, BaseBean.class);
                    if (baseBean.getType().equals("1")) {
                        finish();
                    }
                    ToastUtil.showToast(EditClientActivity.this, baseBean.getContent());
                    loadingDialog.dismiss();
                }

                @Override
                public void onError(String msg) {
                    super.onError(msg);
                    loadingDialog.dismiss();
                }
            });
        }

    }
}
