package ercs.com.ercshouseresources.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/22.
 */

public class CheapReportOrderDetailBean extends BaseBean {
    private DataBean Data;

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean data) {
        Data = data;
    }

    public  static class DataBean
    {
        private String Id;
        private String BuildingName;
        private String UserName;
        private String UserPhone;
        private String State;
        private String TotalAmount;
        private String TaxAmount;
        private String AchieveAmount;
        private String UnAchieveAmount;
        private String BrokerageState;
        private String CustomerName;
        private String CustomerPhone;

        public String getCustomerName() {
            return CustomerName;
        }

        public void setCustomerName(String customerName) {
            CustomerName = customerName;
        }

        public String getCustomerPhone() {
            return CustomerPhone;
        }

        public void setCustomerPhone(String customerPhone) {
            CustomerPhone = customerPhone;
        }

        private List<LowPriceHouseRunningsInfoShowListBean> LowPriceHouseRunningsInfoShowList;
        private List<MessageBoardsShowModel> MessageBoardsShowList;

        public List<MessageBoardsShowModel> getMessageBoardsShowList() {
            return MessageBoardsShowList;
        }

        public void setMessageBoardsShowList(List<MessageBoardsShowModel> messageBoardsShowList) {
            MessageBoardsShowList = messageBoardsShowList;
        }

        public static class MessageBoardsShowModel {
            public String StaffName;
            public String Content;
            public String CreatedTime;

            public String getStaffName() {
                return StaffName;
            }

            public void setStaffName(String staffName) {
                StaffName = staffName;
            }

            public String getContent() {
                return Content;
            }

            public void setContent(String content) {
                Content = content;
            }

            public String getCreatedTime() {
                return CreatedTime;
            }

            public void setCreatedTime(String createdTime) {
                CreatedTime = createdTime;
            }
        }

        public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }

        public String getBuildingName() {
            return BuildingName;
        }

        public void setBuildingName(String buildingName) {
            BuildingName = buildingName;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String userName) {
            UserName = userName;
        }

        public String getUserPhone() {
            return UserPhone;
        }

        public void setUserPhone(String userPhone) {
            UserPhone = userPhone;
        }

        public String getState() {
            return State;
        }

        public void setState(String state) {
            State = state;
        }

        public String getTotalAmount() {
            return TotalAmount;
        }

        public void setTotalAmount(String totalAmount) {
            TotalAmount = totalAmount;
        }

        public String getTaxAmount() {
            return TaxAmount;
        }

        public void setTaxAmount(String taxAmount) {
            TaxAmount = taxAmount;
        }

        public String getAchieveAmount() {
            return AchieveAmount;
        }

        public void setAchieveAmount(String achieveAmount) {
            AchieveAmount = achieveAmount;
        }

        public String getUnAchieveAmount() {
            return UnAchieveAmount;
        }

        public void setUnAchieveAmount(String unAchieveAmount) {
            UnAchieveAmount = unAchieveAmount;
        }

        public String getBrokerageState() {
            return BrokerageState;
        }

        public void setBrokerageState(String brokerageState) {
            BrokerageState = brokerageState;
        }

        public List<LowPriceHouseRunningsInfoShowListBean> getLowPriceHouseRunningsInfoShowList() {
            return LowPriceHouseRunningsInfoShowList;
        }

        public void setLowPriceHouseRunningsInfoShowList(List<LowPriceHouseRunningsInfoShowListBean> lowPriceHouseRunningsInfoShowList) {
            LowPriceHouseRunningsInfoShowList = lowPriceHouseRunningsInfoShowList;
        }

        public static class LowPriceHouseRunningsInfoShowListBean
        {
            private String StyleID;
            private String ModuleName;
            private String ModuleNameColor;
            private String ModuleIcon;
            private String ModuleIconColor;
            private String Title;
            private String OperTime;
            private String ViceTitle;
            private String ViceTitleColor;
            private String FilingAuditingRemark;
            private String IsCameraButton;
            private String GroupID;
            private String ImageType;
            private String ImageRemark;
            private String IsViewButton;
            private String ViewButtonText;
            private String ViewButtonValue;
            private String SortID;
            private List<ImageListBean> ImageList;

            public String getStyleID() {
                return StyleID;
            }

            public void setStyleID(String styleID) {
                StyleID = styleID;
            }

            public String getModuleName() {
                return ModuleName;
            }

            public void setModuleName(String moduleName) {
                ModuleName = moduleName;
            }

            public String getModuleNameColor() {
                return ModuleNameColor;
            }

            public void setModuleNameColor(String moduleNameColor) {
                ModuleNameColor = moduleNameColor;
            }

            public String getModuleIcon() {
                return ModuleIcon;
            }

            public void setModuleIcon(String moduleIcon) {
                ModuleIcon = moduleIcon;
            }

            public String getModuleIconColor() {
                return ModuleIconColor;
            }

            public void setModuleIconColor(String moduleIconColor) {
                ModuleIconColor = moduleIconColor;
            }

            public String getTitle() {
                return Title;
            }

            public void setTitle(String title) {
                Title = title;
            }

            public String getOperTime() {
                return OperTime;
            }

            public void setOperTime(String operTime) {
                OperTime = operTime;
            }

            public String getViceTitle() {
                return ViceTitle;
            }

            public void setViceTitle(String viceTitle) {
                ViceTitle = viceTitle;
            }

            public String getViceTitleColor() {
                return ViceTitleColor;
            }

            public void setViceTitleColor(String viceTitleColor) {
                ViceTitleColor = viceTitleColor;
            }

            public String getFilingAuditingRemark() {
                return FilingAuditingRemark;
            }

            public void setFilingAuditingRemark(String filingAuditingRemark) {
                FilingAuditingRemark = filingAuditingRemark;
            }

            public String getIsCameraButton() {
                return IsCameraButton;
            }

            public void setIsCameraButton(String isCameraButton) {
                IsCameraButton = isCameraButton;
            }

            public String getGroupID() {
                return GroupID;
            }

            public void setGroupID(String groupID) {
                GroupID = groupID;
            }

            public String getImageType() {
                return ImageType;
            }

            public void setImageType(String imageType) {
                ImageType = imageType;
            }

            public String getImageRemark() {
                return ImageRemark;
            }

            public void setImageRemark(String imageRemark) {
                ImageRemark = imageRemark;
            }

            public String getIsViewButton() {
                return IsViewButton;
            }

            public void setIsViewButton(String isViewButton) {
                IsViewButton = isViewButton;
            }

            public String getViewButtonText() {
                return ViewButtonText;
            }

            public void setViewButtonText(String viewButtonText) {
                ViewButtonText = viewButtonText;
            }

            public String getViewButtonValue() {
                return ViewButtonValue;
            }

            public void setViewButtonValue(String viewButtonValue) {
                ViewButtonValue = viewButtonValue;
            }

            public String getSortID() {
                return SortID;
            }

            public void setSortID(String sortID) {
                SortID = sortID;
            }

            public List<ImageListBean> getImageList() {
                return ImageList;
            }

            public void setImageList(List<ImageListBean> imageList) {
                ImageList = imageList;
            }

            public static class ImageListBean {
                private int GroupID;
                private int InterfixID;
                private int ImageType;
                private String ImagePath;
                private String FileName;
                private int ShowIndex;
                private String Tag;
                private String Remark;
                private boolean IsLocked;
                private boolean IsDeleted;
                private String CreatedTime;
                private int Id;

                public ImageListBean(String imagePath, String fileName, int id) {
                    ImagePath = imagePath;
                    FileName = fileName;
                    Id = id;
                }

                public int getGroupID() {
                    return GroupID;
                }

                public void setGroupID(int GroupID) {
                    this.GroupID = GroupID;
                }

                public int getInterfixID() {
                    return InterfixID;
                }

                public void setInterfixID(int InterfixID) {
                    this.InterfixID = InterfixID;
                }

                public int getImageType() {
                    return ImageType;
                }

                public void setImageType(int ImageType) {
                    this.ImageType = ImageType;
                }

                public String getImagePath() {
                    return ImagePath;
                }

                public void setImagePath(String ImagePath) {
                    this.ImagePath = ImagePath;
                }

                public String getFileName() {
                    return FileName;
                }

                public void setFileName(String FileName) {
                    this.FileName = FileName;
                }

                public int getShowIndex() {
                    return ShowIndex;
                }

                public void setShowIndex(int ShowIndex) {
                    this.ShowIndex = ShowIndex;
                }

                public String getTag() {
                    return Tag;
                }

                public void setTag(String Tag) {
                    this.Tag = Tag;
                }

                public String getRemark() {
                    return Remark;
                }

                public void setRemark(String Remark) {
                    this.Remark = Remark;
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
    }
}
