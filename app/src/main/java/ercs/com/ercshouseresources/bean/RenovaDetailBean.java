package ercs.com.ercshouseresources.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/11.
 * 封装装修详情页面
 */

public class RenovaDetailBean extends BaseBean {
    private DataBean Data;

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean data) {
        Data = data;
    }

    public class DataBean {
        private String ImagePath;
        private BaseInfoBean BaseInfo;
        private List<BrokerageGroupListBean> BrokerageGroupList;
        private List<StoreListBean> StoreList;
        private String StaffName;
        private String StaffPhone;
        private List<DecorationCasePartListBean> DecorationCasePartList;
        private String CaseCount;

        public String getCaseCount() {
            return CaseCount;
        }

        public void setCaseCount(String caseCount) {
            CaseCount = caseCount;
        }

        public List<DecorationCasePartListBean> getDecorationCasePartList() {
            return DecorationCasePartList;
        }

        public void setDecorationCasePartList(List<DecorationCasePartListBean> decorationCasePartList) {
            DecorationCasePartList = decorationCasePartList;
        }

        public String getStaffName() {
            return StaffName;
        }

        public void setStaffName(String staffName) {
            StaffName = staffName;
        }

        public String getStaffPhone() {
            return StaffPhone;
        }

        public void setStaffPhone(String staffPhone) {
            StaffPhone = staffPhone;
        }

        public String getImagePath() {
            return ImagePath;
        }

        public void setImagePath(String imagePath) {
            ImagePath = imagePath;
        }

        public BaseInfoBean getBaseInfo() {
            return BaseInfo;
        }

        public void setBaseInfo(BaseInfoBean baseInfo) {
            BaseInfo = baseInfo;
        }

        public List<BrokerageGroupListBean> getBrokerageGroupList() {
            return BrokerageGroupList;
        }

        public void setBrokerageGroupList(List<BrokerageGroupListBean> brokerageGroupList) {
            BrokerageGroupList = brokerageGroupList;
        }

        public List<StoreListBean> getStoreList() {
            return StoreList;
        }

        public void setStoreList(List<StoreListBean> storeList) {
            StoreList = storeList;
        }


        public class BaseInfoBean {
            private String Name;
            private String Brokerage;
            private String BandSawBrokerage;
            private String AwardDescription;
            private String CommissionAccount;
            private String FilingRules;
            private String BandSawRules;
            private String TransactionRules;
            private String DecorationCompanyInfo;
            private String StarTarget;
            private String StarTargetRemark;
            private String ShowIndex;
            private String Remark;
            private String AgentID;
            private String MediumOfCommunicationStaffID;
            private String FeeScale;
            private String Preferential;
            private String State;
            private String IsLocked;
            private String IsDeleted;
            private String CreatedTime;
            private String Id;

            public String getId() {
                return Id;
            }

            public void setId(String id) {
                Id = id;
            }

            public String getName() {
                return Name;
            }

            public void setName(String name) {
                Name = name;
            }

            public String getBrokerage() {
                return Brokerage;
            }

            public void setBrokerage(String brokerage) {
                Brokerage = brokerage;
            }

            public String getBandSawBrokerage() {
                return BandSawBrokerage;
            }

            public void setBandSawBrokerage(String bandSawBrokerage) {
                BandSawBrokerage = bandSawBrokerage;
            }

            public String getAwardDescription() {
                return AwardDescription;
            }

            public void setAwardDescription(String awardDescription) {
                AwardDescription = awardDescription;
            }

            public String getCommissionAccount() {
                return CommissionAccount;
            }

            public void setCommissionAccount(String commissionAccount) {
                CommissionAccount = commissionAccount;
            }

            public String getFilingRules() {
                return FilingRules;
            }

            public void setFilingRules(String filingRules) {
                FilingRules = filingRules;
            }

            public String getBandSawRules() {
                return BandSawRules;
            }

            public void setBandSawRules(String bandSawRules) {
                BandSawRules = bandSawRules;
            }

            public String getTransactionRules() {
                return TransactionRules;
            }

            public void setTransactionRules(String transactionRules) {
                TransactionRules = transactionRules;
            }

            public String getDecorationCompanyInfo() {
                return DecorationCompanyInfo;
            }

            public void setDecorationCompanyInfo(String decorationCompanyInfo) {
                DecorationCompanyInfo = decorationCompanyInfo;
            }

            public String getStarTarget() {
                return StarTarget;
            }

            public void setStarTarget(String starTarget) {
                StarTarget = starTarget;
            }

            public String getStarTargetRemark() {
                return StarTargetRemark;
            }

            public void setStarTargetRemark(String starTargetRemark) {
                StarTargetRemark = starTargetRemark;
            }

            public String getShowIndex() {
                return ShowIndex;
            }

            public void setShowIndex(String showIndex) {
                ShowIndex = showIndex;
            }

            public String getRemark() {
                return Remark;
            }

            public void setRemark(String remark) {
                Remark = remark;
            }

            public String getAgentID() {
                return AgentID;
            }

            public void setAgentID(String agentID) {
                AgentID = agentID;
            }

            public String getMediumOfCommunicationStaffID() {
                return MediumOfCommunicationStaffID;
            }

            public void setMediumOfCommunicationStaffID(String mediumOfCommunicationStaffID) {
                MediumOfCommunicationStaffID = mediumOfCommunicationStaffID;
            }

            public String getFeeScale() {
                return FeeScale;
            }

            public void setFeeScale(String feeScale) {
                FeeScale = feeScale;
            }

            public String getPreferential() {
                return Preferential;
            }

            public void setPreferential(String preferential) {
                Preferential = preferential;
            }

            public String getState() {
                return State;
            }

            public void setState(String state) {
                State = state;
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
        }

        public class BrokerageGroupListBean {
            private String DecorationCompanyID;
            private String Name;
            private String Brokerage;
            private String Remark;
            private String ShowIndex;
            private String PercentageType;
            private String Id;

            public String getDecorationCompanyID() {
                return DecorationCompanyID;
            }

            public void setDecorationCompanyID(String decorationCompanyID) {
                DecorationCompanyID = decorationCompanyID;
            }

            public String getName() {
                return Name;
            }

            public void setName(String name) {
                Name = name;
            }

            public String getBrokerage() {
                return Brokerage;
            }

            public void setBrokerage(String brokerage) {
                Brokerage = brokerage;
            }

            public String getRemark() {
                return Remark;
            }

            public void setRemark(String remark) {
                Remark = remark;
            }

            public String getShowIndex() {
                return ShowIndex;
            }

            public void setShowIndex(String showIndex) {
                ShowIndex = showIndex;
            }

            public String getPercentageType() {
                return PercentageType;
            }

            public void setPercentageType(String percentageType) {
                PercentageType = percentageType;
            }

            public String getId() {
                return Id;
            }

            public void setId(String id) {
                Id = id;
            }
        }

        public class StoreListBean {
            private String DecorationCompanyID;
            private String Name;
            private String Address;
            private String ProvinceID;
            private String CityID;
            private String AreaID;
            private String X;
            private String Y;
            private String ShowIndex;
            private String State;
            private String CreatedTime;
            private String Id;

            public String getDecorationCompanyID() {
                return DecorationCompanyID;
            }

            public void setDecorationCompanyID(String decorationCompanyID) {
                DecorationCompanyID = decorationCompanyID;
            }

            public String getName() {
                return Name;
            }

            public void setName(String name) {
                Name = name;
            }

            public String getAddress() {
                return Address;
            }

            public void setAddress(String address) {
                Address = address;
            }

            public String getProvinceID() {
                return ProvinceID;
            }

            public void setProvinceID(String provinceID) {
                ProvinceID = provinceID;
            }

            public String getCityID() {
                return CityID;
            }

            public void setCityID(String cityID) {
                CityID = cityID;
            }

            public String getAreaID() {
                return AreaID;
            }

            public void setAreaID(String areaID) {
                AreaID = areaID;
            }

            public String getX() {
                return X;
            }

            public void setX(String x) {
                X = x;
            }

            public String getY() {
                return Y;
            }

            public void setY(String y) {
                Y = y;
            }

            public String getShowIndex() {
                return ShowIndex;
            }

            public void setShowIndex(String showIndex) {
                ShowIndex = showIndex;
            }

            public String getState() {
                return State;
            }

            public void setState(String state) {
                State = state;
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

        public class DecorationCasePartListBean {
            private String CaseImagePath;
            private String Name;
            private String Content;
            private String Id;

            public String getId() {
                return Id;
            }

            public void setId(String id) {
                Id = id;
            }

            public String getCaseImagePath() {
                return CaseImagePath;
            }

            public void setCaseImagePath(String caseImagePath) {
                CaseImagePath = caseImagePath;
            }

            public String getName() {
                return Name;
            }

            public void setName(String name) {
                Name = name;
            }

            public String getContent() {
                return Content;
            }

            public void setContent(String content) {
                Content = content;
            }
        }

    }
}
