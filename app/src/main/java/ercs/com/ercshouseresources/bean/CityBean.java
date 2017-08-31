package ercs.com.ercshouseresources.bean;

import java.util.List;

import ercs.com.ercshouseresources.activity.BaseActivity;

/**
 * Created by Administrator on 2017/8/31.
 */

public class CityBean extends BaseBean {
    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> data) {
        Data = data;
    }

    public class DataBean {
        private String ProvinceID;
        private String ProvinceName;
        private String CityID;
        private String CityName;

        public String getProvinceID() {
            return ProvinceID;
        }

        public void setProvinceID(String provinceID) {
            ProvinceID = provinceID;
        }

        public String getProvinceName() {
            return ProvinceName;
        }

        public void setProvinceName(String provinceName) {
            ProvinceName = provinceName;
        }

        public String getCityID() {
            return CityID;
        }

        public void setCityID(String cityID) {
            CityID = cityID;
        }

        public String getCityName() {
            return CityName;
        }

        public void setCityName(String cityName) {
            CityName = cityName;
        }
    }
}
