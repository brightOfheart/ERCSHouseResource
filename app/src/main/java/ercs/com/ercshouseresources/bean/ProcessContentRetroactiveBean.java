package ercs.com.ercshouseresources.bean;

/**
 * 封装流程审批的返回数据 补签
 * Created by Administrator on 2017/7/15.
 */

public class ProcessContentRetroactiveBean {


    /**
     * Type : 1
     * Content : 加载成功
     * Data : {"UserId":2009,"RetroactiveClass":"3","RetroactiveTime":"2017-07-14T00:00:00","RetroactiveState":"1","ApplicationContent":"很好很好很好","ApplicationTime":"2017-07-14T00:00:00","ApprovalUserId":2009,"ApprovalContent":null,"ApprovalTime":"2017-07-14T13:53:02.537","CreatorId":null,"IsLocked":false,"IsDeleted":false,"CreatedTime":"2017-07-14T13:53:02.507","Id":27}
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
         * UserId : 2009
         * RetroactiveClass : 3
         * RetroactiveTime : 2017-07-14T00:00:00
         * RetroactiveState : 1
         * ApplicationContent : 很好很好很好
         * ApplicationTime : 2017-07-14T00:00:00
         * ApprovalUserId : 2009
         * ApprovalContent : null
         * ApprovalTime : 2017-07-14T13:53:02.537
         * CreatorId : null
         * IsLocked : false
         * IsDeleted : false
         * CreatedTime : 2017-07-14T13:53:02.507
         * Id : 27
         */

        private int UserId;
        private String RetroactiveClass;
        private String RetroactiveTime;
        private String RetroactiveState;
        private String ApplicationContent;
        private String ApplicationTime;
        private int ApprovalUserId;
        private Object ApprovalContent;
        private String ApprovalTime;
        private Object CreatorId;
        private boolean IsLocked;
        private boolean IsDeleted;
        private String CreatedTime;
        private int Id;

        public int getUserId() {
            return UserId;
        }

        public void setUserId(int UserId) {
            this.UserId = UserId;
        }

        public String getRetroactiveClass() {
            return RetroactiveClass;
        }

        public void setRetroactiveClass(String RetroactiveClass) {
            this.RetroactiveClass = RetroactiveClass;
        }

        public String getRetroactiveTime() {
            return RetroactiveTime;
        }

        public void setRetroactiveTime(String RetroactiveTime) {
            this.RetroactiveTime = RetroactiveTime;
        }

        public String getRetroactiveState() {
            return RetroactiveState;
        }

        public void setRetroactiveState(String RetroactiveState) {
            this.RetroactiveState = RetroactiveState;
        }

        public String getApplicationContent() {
            return ApplicationContent;
        }

        public void setApplicationContent(String ApplicationContent) {
            this.ApplicationContent = ApplicationContent;
        }

        public String getApplicationTime() {
            return ApplicationTime;
        }

        public void setApplicationTime(String ApplicationTime) {
            this.ApplicationTime = ApplicationTime;
        }

        public int getApprovalUserId() {
            return ApprovalUserId;
        }

        public void setApprovalUserId(int ApprovalUserId) {
            this.ApprovalUserId = ApprovalUserId;
        }

        public Object getApprovalContent() {
            return ApprovalContent;
        }

        public void setApprovalContent(Object ApprovalContent) {
            this.ApprovalContent = ApprovalContent;
        }

        public String getApprovalTime() {
            return ApprovalTime;
        }

        public void setApprovalTime(String ApprovalTime) {
            this.ApprovalTime = ApprovalTime;
        }

        public Object getCreatorId() {
            return CreatorId;
        }

        public void setCreatorId(Object CreatorId) {
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
