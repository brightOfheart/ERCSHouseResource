package ercs.com.ercshouseresources.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/31.
 */

public class ChoseCityBean extends BaseBean {
    private DataBean Data;

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean data) {
        Data = data;
    }

    public class DataBean {
        private String CityID;
        private String CityName;
        private List<String> Tabs;

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

        public List<String> getTabs() {
            return Tabs;
        }

        public void setTabs(List<String> tabs) {
            Tabs = tabs;
        }
    }
}
