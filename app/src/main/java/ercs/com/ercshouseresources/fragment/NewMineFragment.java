package ercs.com.ercshouseresources.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.UpdateActivity;
import ercs.com.ercshouseresources.activity.clerk.ClerkActivity;
import ercs.com.ercshouseresources.activity.mine.MyOrderActivity;
import ercs.com.ercshouseresources.activity.service.DynamicActivity;
import ercs.com.ercshouseresources.activity.set.SetActivity;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.bean.BaseBean;
import ercs.com.ercshouseresources.bean.UpdateBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.newbean.LoginBean;
import ercs.com.ercshouseresources.util.OtherUitl;
import ercs.com.ercshouseresources.util.SPUtil;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.util.imageUtil.GlideUtil;
import ercs.com.ercshouseresources.view.dialog.ChosePicDialog;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;
import ercs.com.ercshouseresources.view.lazyviewpager.LazyFragmentPagerAdapter;

import static android.app.Activity.RESULT_OK;
import static ercs.com.ercshouseresources.util.StringUtil.getStr;

/**
 * Created by Administrator on 2017/8/22.
 */

public class NewMineFragment extends Fragment implements LazyFragmentPagerAdapter.Laziable {
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_dep)
    TextView tv_dep;
    @BindView(R.id.tv_company)
    TextView tv_company;
    @BindView(R.id.iv_photo)
    ImageView iv_photo;
    @BindView(R.id.ly_manager)
    LinearLayout ly_manager;
    private SPUtil spUtil;
    public static String SAVED_IMAGE_DIR_PATH =
            Environment.getExternalStorageDirectory().getPath()
                    + "/AppNames/camera/";// 拍照路径
    private String cameraPath = "";
    public final static int CAMERA_REQUEST_CODE = 3;
    private String iconurl = Environment.getExternalStorageDirectory().getPath() + "/AppNames/" + "small.jpg";
    private LoadingDialog dialog;
    private String name = "";
    private String sex = "";


    //最新APK的下载地址
    public static final String APK_URL = "http://mobile.ac.qq.com/qqcomic_android.apk";
    //下载后的APK的命名
    public static final String APK_NAME = "update";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newmine, container, false);
        ButterKnife.bind(this, view);
        initview();
        return view;

    }


    /**
     * 点击事件的处理
     *
     * @param view
     */
    @OnClick({R.id.ly_clerk, R.id.ly_set, R.id.ly_process, R.id.ly_memberAssess})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ly_clerk://职员列表
                startActivity(new Intent(getContext(), ClerkActivity.class));
                break;
            case R.id.ly_process://我的订单
                startActivity(new Intent(getContext(), MyOrderActivity.class));
                break;
            case R.id.ly_memberAssess://最新动态
                DynamicActivity.start(getActivity(), "0");
                break;

            case R.id.ly_set://设置
                startActivity(new Intent(getContext(), SetActivity.class));
                break;
        }
    }

    /**
     * 初始化
     */
    private void initview() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)//判断当前的版本是否大于6.0
        {
            OtherUitl.verifyStoragePermissions(getActivity());//开启安卓6.0以上系统开启sd卡写入的权限
        }
        if (spUtil == null)
            spUtil = new SPUtil(getContext(), "fileName");
        dialog = new LoadingDialog(getContext(), 0);
        name = spUtil.getString(BaseApplication.NAME, "");
//        if (BaseApplication.loginBean.getData().getStaffList().size() > 0)
//            sex = BaseApplication.loginBean.getData().getStaffList().get(0).getSex();
//        else
        sex = "1";
        setShowData();
        isHideManager();
    }


    /**
     * 是否显示管理员的功能
     */
    private void isHideManager() {
        if (spUtil.getString(BaseApplication.AUTHORITY, "").equals("1") || spUtil.getString(BaseApplication.AUTHORITY, "").equals("2"))
            ly_manager.setVisibility(View.VISIBLE);
        else
            ly_manager.setVisibility(View.VISIBLE);
    }

    /**
     * 设置显示的View数据
     */
    private void setShowData() {
        tv_name.setText(getStr(R.string.str_welcome) + spUtil.getString(BaseApplication.NAME, ""));
        if (spUtil.getString(BaseApplication.DEPNAME, "").equals("")) {
            tv_dep.setText(spUtil.getString(BaseApplication.DEPNAME, ""));
        } else {
            tv_dep.setText(getStr(R.string.str_leftbracket) + spUtil.getString(BaseApplication.DEPNAME, "") + getStr(R.string.str_rightbracket));
        }
        tv_company.setText(spUtil.getString(BaseApplication.COMPANY, ""));
        if (spUtil.getString(BaseApplication.PHOTOPATH, "").equals("")) {
            GlideUtil.loadCircleImage(R.mipmap.default_photo, iv_photo);
        } else {
            byte[] bytes = OtherUitl.stringtoBitmapbyte(spUtil.getString(BaseApplication.PHOTOPATH, ""));
            GlideUtil.loadCircleImage(bytes, iv_photo, R.mipmap.default_photo);
        }

        iv_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)//判断当前的版本是否大于6.0
                {
                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 66);

                    } else {
                        //有权限，直接拍照
                        chosedialog();

                    }
                } else {
                    chosedialog();

                }

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 11) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //申请成功，可以拍照
                chosedialog();
            } else {
                Toast.makeText(getContext(), "请开启拍照权限", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //申请成功，可以拍照
            } else {

                Toast.makeText(getContext(), "请开启SD卡读写权限", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void chosedialog() {
        ChosePicDialog chosePicDialog = new ChosePicDialog(getActivity(), new ChosePicDialog.OnClickLister() {
            @Override
            public void camera() {
                savePath();

            }

            @Override
            public void pic() {
                //调用相册
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 10);
            }
        });
        chosePicDialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQUEST_CODE) {
                startPhotoZoom(Uri.fromFile(new File(cameraPath)));
            }
            if (requestCode == 2) {
                String str = OtherUitl.imageToBase64(iconurl);
                upLoadPic(str);
            }
        }
        //获取图片路径
        if (requestCode == 10 && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContext().getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            startPhotoZoom(Uri.fromFile(new File(imagePath)));
            c.close();
        }
    }

    /**
     * 指定相机拍摄照片保存地址
     */
    private void savePath() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            cameraPath = SAVED_IMAGE_DIR_PATH + System.currentTimeMillis() + ".png";
            Intent intent = new Intent();
            // 指定开启系统相机的Action
            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            // intent.setAction("android.media.action.STILL_IMAGE_CAMERA");
            String out_file_path = SAVED_IMAGE_DIR_PATH;
            File dir = new File(out_file_path);
            if (!dir.exists()) {
                dir.mkdirs();
            } // 把文件地址转换成Uri格式
            Uri uri = Uri.fromFile(new File(cameraPath));
            // 设置系统相机拍摄照片完成后图片文件的存放地址
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intent, CAMERA_REQUEST_CODE);
        } else {
            Toast.makeText(getContext(), "请确认已经插入SD卡",
                    Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 收缩图片
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");//调用Android系统自带的一个图片剪裁页面,
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");//进行修剪
// aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
// outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
//        intent.putExtra("return-data", true);
        //uritempFile为Uri类变量，实例化uritempFile
        Uri uritempFile = Uri.parse(iconurl);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(iconurl)));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        startActivityForResult(intent, 2);
    }

    /**
     * 上传图片
     */
    private void upLoadPic(final String path) {
        dialog.show();
        NetHelperNew.updatePerMes(name, path, sex, new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                dialog.dismiss();
                final BaseBean baseBean = MyGson.getInstance().fromJson(data, BaseBean.class);
                if (baseBean.getType().equals("1")) {
                    spUtil.putString(BaseApplication.PHOTOPATH, path);
                    byte[] bytes = OtherUitl.stringtoBitmapbyte(path);
                    GlideUtil.loadCircleImage(bytes, iv_photo, R.mipmap.default_photo);
                } else {
                    ToastUtil.showToast(getContext(), baseBean.getContent());
                }

            }

            @Override
            public void onError(String msg) {
                dialog.dismiss();
                super.onError(msg);
                ToastUtil.showToast(getContext(), msg);
            }
        });

    }
}
