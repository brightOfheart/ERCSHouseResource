package ercs.com.ercshouseresources.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/3.
 * 打卡设置信息接口
 */

public class ClockinSetBean extends BaseBean {
    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> data) {
        Data = data;
    }

    public class DataBean {
        private String MacAddress;
        private String Longitude;
        private String Latitude;
        private String Radius;
        private String DepartmentId;
        private String IsLocked;

        public String getMacAddress() {
            return MacAddress;
        }

        public void setMacAddress(String macAddress) {
            MacAddress = macAddress;
        }

        public String getLongitude() {
            return Longitude;
        }

        public void setLongitude(String longitude) {
            Longitude = longitude;
        }

        public String getLatitude() {
            return Latitude;
        }

        public void setLatitude(String latitude) {
            Latitude = latitude;
        }

        public String getRadius() {
            return Radius;
        }

        public void setRadius(String radius) {
            Radius = radius;
        }

        public String getDepartmentId() {
            return DepartmentId;
        }

        public void setDepartmentId(String departmentId) {
            DepartmentId = departmentId;
        }

        public String getIsLocked() {
            return IsLocked;
        }

        public void setIsLocked(String isLocked) {
            IsLocked = isLocked;
        }
    }
}
