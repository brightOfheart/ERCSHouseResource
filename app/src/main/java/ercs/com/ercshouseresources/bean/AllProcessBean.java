package ercs.com.ercshouseresources.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 * 封装所有流程的Bean
 */

public class AllProcessBean extends BaseBean {
    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> data) {
        Data = data;
    }

    private List<DataBean> Data;
    public class DataBean
    {
        private String Id;
        private String LeaveType;
        private String LeaveState;
        private String CreatedTime;
        private String PhotoPath;

        public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }

        public String getLeaveType() {
            return LeaveType;
        }

        public void setLeaveType(String leaveType) {
            LeaveType = leaveType;
        }

        public String getLeaveState() {
            return LeaveState;
        }

        public void setLeaveState(String leaveState) {
            LeaveState = leaveState;
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
    }
}
