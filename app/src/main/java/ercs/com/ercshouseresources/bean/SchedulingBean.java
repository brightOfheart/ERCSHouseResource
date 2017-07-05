package ercs.com.ercshouseresources.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/26.
 * 封装排班情况的BEAN
 */

public class SchedulingBean {
    private String Type;
    private String Content;
    private List<DataBean> Data;
    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> data) {
        Data = data;
    }

    public class DataBean {
        private String ID;
        private String Date;
        private List<UDataBean> UData;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public List<UDataBean> getUData() {
            return UData;
        }

        public void setUData(List<UDataBean> UData) {
            this.UData = UData;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String date) {
            Date = date;
        }

        public class UDataBean {
            private String ID;
            private String UserId;
            private String UserName;
            private String Phone;
            private String PhotoPath;

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public String getUserId() {
                return UserId;
            }

            public void setUserId(String userId) {
                UserId = userId;
            }

            public String getUserName() {
                return UserName;
            }

            public void setUserName(String userName) {
                UserName = userName;
            }

            public String getPhone() {
                return Phone;
            }

            public void setPhone(String phone) {
                Phone = phone;
            }

            public String getPhotoPath() {
                return PhotoPath;
            }

            public void setPhotoPath(String photoPath) {
                PhotoPath = photoPath;
            }
        }
    }
}
