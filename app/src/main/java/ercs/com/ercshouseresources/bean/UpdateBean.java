package ercs.com.ercshouseresources.bean;

/**
 * Created by Administrator on 2017/9/4.
 */

public class UpdateBean extends BaseBean {
    private DataBean Data;

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean data) {
        Data = data;
    }

    public class DataBean {
        private String VersionCode;
        private String UpdateUrl;
        private String UpdateInfo;

        public String getVersionCode() {
            return VersionCode;
        }

        public void setVersionCode(String versionCode) {
            VersionCode = versionCode;
        }

        public String getUpdateUrl() {
            return UpdateUrl;
        }

        public void setUpdateUrl(String updateUrl) {
            UpdateUrl = updateUrl;
        }

        public String getUpdateInfo() {
            return UpdateInfo;
        }

        public void setUpdateInfo(String updateInfo) {
            UpdateInfo = updateInfo;
        }
    }

}
