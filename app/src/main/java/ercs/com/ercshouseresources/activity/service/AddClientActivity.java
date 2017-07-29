package ercs.com.ercshouseresources.activity.service;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.adapter.AddClientAdapter;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.bean.ClientTelBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.NetWorkUtil;
import ercs.com.ercshouseresources.util.SPUtil;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.view.NoScrollListView;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;

/**
 * 添加客户
 */
public class AddClientActivity extends AppCompatActivity {

    @BindView(R.id.listview)
    ListView listView;
    @BindView(R.id.radio_man)
    RadioButton radio_man;
    @BindView(R.id.radio_woman)
    RadioButton radio_woman;
    @BindView(R.id.edit_name)
    EditText edit_name;

    private List<ClientTelBean> list;
    private AddClientAdapter addClientAdapter;

    private LoadingDialog loadingDialog;

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
                //跳转到通讯录
                Uri uri = Uri.parse("content://contacts/people");
                Intent intent = new Intent(Intent.ACTION_PICK, uri);
                startActivityForResult(intent, 0);
            }
        });

        loadingDialog=new LoadingDialog(this,0);
    }


    /*
    * 跳转联系人列表的回调函数
    * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 0:
                if(data==null)
                {
                    return;
                }
                //处理返回的data,获取选择的联系人信息
                Uri uri=data.getData();
                String[] contacts=getPhoneContacts(uri);
                edit_name.setText(contacts[0]);
                list.clear();
                list.add(new ClientTelBean(contacts[1]));
                addClientAdapter.notifyDataSetChanged();

                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 获取联系人姓名和手机号
     * @param uri
     * @return
     */
    private String[] getPhoneContacts(Uri uri){
        String[] contact=new String[2];
        //得到ContentResolver对象
        ContentResolver cr = getContentResolver();
        //取得电话本中开始一项的光标
        Cursor cursor=cr.query(uri,null,null,null,null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
            //取得联系人姓名
            int nameFieldColumnIndex=cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            contact[0]=cursor.getString(nameFieldColumnIndex);
            //取得电话号码
            String ContactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor phone = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + ContactId, null, null);
            if(phone != null){
                phone.moveToFirst();
                contact[1] = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            }
            phone.close();
            cursor.close();
        }
        else
        {
            return null;
        }
        return contact;
    }
    @OnClick({R.id.btn_save})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btn_save:
                //保存
                if ("".equals(edit_name.getText().toString()))
                {
                    ToastUtil.showToast(AddClientActivity.this,getString(R.string.error_inputname));
                }else if (NetWorkUtil.check(this))
                {
                    addClientNet();
                }

                break;
        }
    }

    /**
     * 添加客户网络请求
     */
    private void addClientNet() {

       String tels="";
        boolean isTelNull=true;//电话号是否为空
        for (int i = 0; i < list.size(); i++) {
            if (i==0)
            {
                tels=list.get(i).getTel();
            }else
            {
                tels=tels+"|"+list.get(i).getTel();
            }

            if (!"".equals(list.get(i).getTel()))
            {
                isTelNull=false;
            }
        }
        int sex=radio_man.isChecked() ? 0 : 1;

        if (isTelNull)
        {
            //有电话号输入
            ToastUtil.showToast(AddClientActivity.this,getString(R.string.error_tel_number));
        }
        else {
            loadingDialog.show();
            NetHelperNew.getInsertNewCustomer(BaseApplication.loginBean.getData().getId(), edit_name.getText().toString(),tels , sex+ "", new HttpUtils.HttpCallback() {
                @Override
                public void onSuccess(String data) {
                    finish();
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
