package ercs.com.ercshouseresources.newbean;

import ercs.com.ercshouseresources.bean.BaseBean;

/**
 * Created by Administrator on 2017/7/25.
 */

public class LoginBean extends BaseBean {
    private DataBean Data;

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean data) {
        Data = data;
    }

    public class DataBean {
        private String Id;
        private String LoginName;
        private String Name;
        private String CityID;
        private String CityName;

        public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }

        public String getLoginName() {
            return LoginName;
        }

        public void setLoginName(String loginName) {
            LoginName = loginName;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
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
