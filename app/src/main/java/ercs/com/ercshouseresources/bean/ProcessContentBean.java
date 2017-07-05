package ercs.com.ercshouseresources.bean;

/**
 * Created by Administrator on 2017/6/23.
 * 封装流程审批的返回数据
 */

public class ProcessContentBean {
    private String Type;
    private String Content;
    private DataBean Data;

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

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean data) {
        Data = data;
    }

    public class DataBean
    {
        private String MonthCount;
        private String PageIndex;
        private String PageSize;
        private String DataCounts;
        private String UserName;
        private String UserId;
        private String ParentId;
        private String StartTime;
        private String EndTime;
        private String TimeCount;
        private String LeaveType;
        private String LeaveState;
        private String ApprovalUserId;
        private String LeaveContent;
        private String ApprovalContent;
        private String ApprovalTime;
        private String CreatorId;
        private String IsLocked;
        private String IsDeleted;
        private String CreatedTime;
        private String Id;

        public String getMonthCount() {
            return MonthCount;
        }

        public void setMonthCount(String monthCount) {
            MonthCount = monthCount;
        }

        public String getPageIndex() {
            return PageIndex;
        }

        public void setPageIndex(String pageIndex) {
            PageIndex = pageIndex;
        }

        public String getPageSize() {
            return PageSize;
        }

        public void setPageSize(String pageSize) {
            PageSize = pageSize;
        }

        public String getDataCounts() {
            return DataCounts;
        }

        public void setDataCounts(String dataCounts) {
            DataCounts = dataCounts;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String userName) {
            UserName = userName;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String userId) {
            UserId = userId;
        }

        public String getParentId() {
            return ParentId;
        }

        public void setParentId(String parentId) {
            ParentId = parentId;
        }

        public String getStartTime() {
            return StartTime;
        }

        public void setStartTime(String startTime) {
            StartTime = startTime;
        }

        public String getEndTime() {
            return EndTime;
        }

        public void setEndTime(String endTime) {
            EndTime = endTime;
        }

        public String getTimeCount() {
            return TimeCount;
        }

        public void setTimeCount(String timeCount) {
            TimeCount = timeCount;
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

        public String getApprovalUserId() {
            return ApprovalUserId;
        }

        public void setApprovalUserId(String approvalUserId) {
            ApprovalUserId = approvalUserId;
        }

        public String getApprovalContent() {
            return ApprovalContent;
        }

        public void setApprovalContent(String approvalContent) {
            ApprovalContent = approvalContent;
        }

        public String getLeaveContent() {
            return LeaveContent;
        }

        public void setLeaveContent(String leaveContent) {
            LeaveContent = leaveContent;
        }

        public String getApprovalTime() {
            return ApprovalTime;
        }

        public void setApprovalTime(String approvalTime) {
            ApprovalTime = approvalTime;
        }

        public String getCreatorId() {
            return CreatorId;
        }

        public void setCreatorId(String creatorId) {
            CreatorId = creatorId;
        }

        public String getIsDeleted() {
            return IsDeleted;
        }

        public void setIsDeleted(String isDeleted) {
            IsDeleted = isDeleted;
        }

        public String getIsLocked() {
            return IsLocked;
        }

        public void setIsLocked(String isLocked) {
            IsLocked = isLocked;
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
