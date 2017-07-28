package ercs.com.ercshouseresources.newbean;

import java.util.List;

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
        private List<StaffListBean> StaffList;

        public List<StaffListBean> getStaffList() {
            return StaffList;
        }

        public void setStaffList(List<StaffListBean> staffList) {
            StaffList = staffList;
        }

        public class StaffListBean {
            private String ModuleID;
            private String StaffID;
            private String Name;
            private String Phone;
            private String Sex;
            private String Remark;

            public String getModuleID() {
                return ModuleID;
            }

            public void setModuleID(String moduleID) {
                ModuleID = moduleID;
            }

            public String getName() {
                return Name;
            }

            public void setName(String name) {
                Name = name;
            }

            public String getStaffID() {
                return StaffID;
            }

            public void setStaffID(String staffID) {
                StaffID = staffID;
            }

            public String getPhone() {
                return Phone;
            }

            public void setPhone(String phone) {
                Phone = phone;
            }

            public String getSex() {
                return Sex;
            }

            public void setSex(String sex) {
                Sex = sex;
            }

            public String getRemark() {
                return Remark;
            }

            public void setRemark(String remark) {
                Remark = remark;
            }
        }

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
