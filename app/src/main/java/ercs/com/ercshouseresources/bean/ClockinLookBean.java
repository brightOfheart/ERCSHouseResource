package ercs.com.ercshouseresources.bean;

/**
 * Created by Administrator on 2017/7/1.
 */

public class ClockinLookBean extends BaseBean {
    private DataBean Data;

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean data) {
        Data = data;
    }

    public class DataBean {
        private String Attstrtime;
        private String Attendtime;
        private String StartImagePageName;
        private String EndImagePageName;
        private String StartTime;
        private String StartContent;
        private String StartLocation;
        private String StartImagePage;
        private String EndTime;
        private String EndContent;
        private String EndLocation;
        private String EndImagePage;
        private String CreatorId;
        private String IsLocked;
        private String IsDeleted;
        private String CreatedTime;
        private String Id;

        public String getEndContent() {
            return EndContent;
        }

        public void setEndContent(String endContent) {
            EndContent = endContent;
        }

        public String getAttendtime() {
            return Attendtime;
        }

        public void setAttendtime(String attendtime) {
            Attendtime = attendtime;
        }

        public String getAttstrtime() {
            return Attstrtime;
        }

        public void setAttstrtime(String attstrtime) {
            Attstrtime = attstrtime;
        }

        public String getStartImagePageName() {
            return StartImagePageName;
        }

        public void setStartImagePageName(String startImagePageName) {
            StartImagePageName = startImagePageName;
        }

        public String getEndImagePageName() {
            return EndImagePageName;
        }

        public void setEndImagePageName(String endImagePageName) {
            EndImagePageName = endImagePageName;
        }

        public String getStartTime() {
            return StartTime;
        }

        public void setStartTime(String startTime) {
            StartTime = startTime;
        }

        public String getStartContent() {
            return StartContent;
        }

        public void setStartContent(String startContent) {
            StartContent = startContent;
        }

        public String getStartLocation() {
            return StartLocation;
        }

        public void setStartLocation(String startLocation) {
            StartLocation = startLocation;
        }

        public String getStartImagePage() {
            return StartImagePage;
        }

        public void setStartImagePage(String startImagePage) {
            StartImagePage = startImagePage;
        }

        public String getEndTime() {
            return EndTime;
        }

        public void setEndTime(String endTime) {
            EndTime = endTime;
        }

        public String getEndLocation() {
            return EndLocation;
        }

        public void setEndLocation(String endLocation) {
            EndLocation = endLocation;
        }

        public String getEndImagePage() {
            return EndImagePage;
        }

        public void setEndImagePage(String endImagePage) {
            EndImagePage = endImagePage;
        }

        public String getCreatorId() {
            return CreatorId;
        }

        public void setCreatorId(String creatorId) {
            CreatorId = creatorId;
        }

        public String getIsLocked() {
            return IsLocked;
        }

        public void setIsLocked(String isLocked) {
            IsLocked = isLocked;
        }

        public String getIsDeleted() {
            return IsDeleted;
        }

        public void setIsDeleted(String isDeleted) {
            IsDeleted = isDeleted;
        }

        public String getCreatedTime() {
            return CreatedTime;
        }

        public void setCreatedTime(String createdTime) {
            CreatedTime = createdTime;
        }

        public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }
    }
}
