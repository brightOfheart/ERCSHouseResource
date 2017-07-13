package ercs.com.ercshouseresources.bean;

/**
 * Created by Administrator on 2017/7/12.
 */

public class AtendanceDetailBean {

    /**
     * Type : 1
     * Content : 加载成功
     * Data : {"AttStrtime":"09:00:00","AttEndtime":"18:00:00","StartImagePageName":"ss","EndImagePageName":"ss","StartTime":"2017-07-12T00:00:00Z","StartContent":"ss","StartLeaveType":0,"StartLocation":"ss","StartImagePage":0,"EndTime":"2017-07-12T00:00:00Z","EndContent":"ss","EndLeaveType":0,"EndLocation":"ss","EndImagePage":0,"CreatorId":"ss","IsLocked":false,"IsDeleted":false,"CreatedTime":"2017-07-12T01:44:55.027Z","Id":0}
     */

    private int Type;
    private String Content;
    private DataBean Data;

    public int getType() {
        return Type;
    }

    public void setType(int Type) {
        this.Type = Type;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * AttStrtime : 09:00:00
         * AttEndtime : 18:00:00
         * StartImagePageName : ss
         * EndImagePageName : ss
         * StartTime : 2017-07-12T00:00:00Z
         * StartContent : ss
         * StartLeaveType : 0
         * StartLocation : ss
         * StartImagePage : 0
         * EndTime : 2017-07-12T00:00:00Z
         * EndContent : ss
         * EndLeaveType : 0
         * EndLocation : ss
         * EndImagePage : 0
         * CreatorId : ss
         * IsLocked : false
         * IsDeleted : false
         * CreatedTime : 2017-07-12T01:44:55.027Z
         * Id : 0
         */

        private String AttStrtime;
        private String AttEndtime;
        private String StartImagePageName;
        private String EndImagePageName;
        private String StartTime;
        private String StartContent;
        private int StartLeaveType;
        private String StartLocation;
        private int StartImagePage;
        private String EndTime;
        private String EndContent;
        private int EndLeaveType;
        private String EndLocation;
        private int EndImagePage;
        private String CreatorId;
        private boolean IsLocked;
        private boolean IsDeleted;
        private String CreatedTime;
        private int Id;

        public String getAttStrtime() {
            return AttStrtime;
        }

        public void setAttStrtime(String AttStrtime) {
            this.AttStrtime = AttStrtime;
        }

        public String getAttEndtime() {
            return AttEndtime;
        }

        public void setAttEndtime(String AttEndtime) {
            this.AttEndtime = AttEndtime;
        }

        public String getStartImagePageName() {
            return StartImagePageName;
        }

        public void setStartImagePageName(String StartImagePageName) {
            this.StartImagePageName = StartImagePageName;
        }

        public String getEndImagePageName() {
            return EndImagePageName;
        }

        public void setEndImagePageName(String EndImagePageName) {
            this.EndImagePageName = EndImagePageName;
        }

        public String getStartTime() {
            return StartTime;
        }

        public void setStartTime(String StartTime) {
            this.StartTime = StartTime;
        }

        public String getStartContent() {
            return StartContent;
        }

        public void setStartContent(String StartContent) {
            this.StartContent = StartContent;
        }

        public int getStartLeaveType() {
            return StartLeaveType;
        }

        public void setStartLeaveType(int StartLeaveType) {
            this.StartLeaveType = StartLeaveType;
        }

        public String getStartLocation() {
            return StartLocation;
        }

        public void setStartLocation(String StartLocation) {
            this.StartLocation = StartLocation;
        }

        public int getStartImagePage() {
            return StartImagePage;
        }

        public void setStartImagePage(int StartImagePage) {
            this.StartImagePage = StartImagePage;
        }

        public String getEndTime() {
            return EndTime;
        }

        public void setEndTime(String EndTime) {
            this.EndTime = EndTime;
        }

        public String getEndContent() {
            return EndContent;
        }

        public void setEndContent(String EndContent) {
            this.EndContent = EndContent;
        }

        public int getEndLeaveType() {
            return EndLeaveType;
        }

        public void setEndLeaveType(int EndLeaveType) {
            this.EndLeaveType = EndLeaveType;
        }

        public String getEndLocation() {
            return EndLocation;
        }

        public void setEndLocation(String EndLocation) {
            this.EndLocation = EndLocation;
        }

        public int getEndImagePage() {
            return EndImagePage;
        }

        public void setEndImagePage(int EndImagePage) {
            this.EndImagePage = EndImagePage;
        }

        public String getCreatorId() {
            return CreatorId;
        }

        public void setCreatorId(String CreatorId) {
            this.CreatorId = CreatorId;
        }

        public boolean isIsLocked() {
            return IsLocked;
        }

        public void setIsLocked(boolean IsLocked) {
            this.IsLocked = IsLocked;
        }

        public boolean isIsDeleted() {
            return IsDeleted;
        }

        public void setIsDeleted(boolean IsDeleted) {
            this.IsDeleted = IsDeleted;
        }

        public String getCreatedTime() {
            return CreatedTime;
        }

        public void setCreatedTime(String CreatedTime) {
            this.CreatedTime = CreatedTime;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }
    }
}
