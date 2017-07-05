package ercs.com.ercshouseresources.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/23.
 */

public class ProcessBean {
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

    public class DataBean
    {
        private String Id;
        private String UserName;
        private String LeaveType;
        private String CreatedTime;
        private String PhotoPath;

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String userName) {
            UserName = userName;
        }

        public String getLeaveType() {
            return LeaveType;
        }

        public void setLeaveType(String leaveType) {
            LeaveType = leaveType;
        }

        public String getCreatedTime() {
            return CreatedTime;
        }

        public void setCreatedTime(String createdTime) {
            CreatedTime = createdTime;
        }

        public String getPhotoPath() {
            return PhotoPath;
        }

        public void setPhotoPath(String photoPath) {
            PhotoPath = photoPath;
        }

        public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }
    }
}
