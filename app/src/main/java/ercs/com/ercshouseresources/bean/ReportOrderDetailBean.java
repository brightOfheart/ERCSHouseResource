package ercs.com.ercshouseresources.bean;

import java.util.List;

/**
 * 报备订单详情
 * Created by Administrator on 2017/7/29.
 */

public class ReportOrderDetailBean {


    /**
     * Type : 1
     * Content : 成功
     * Data : {"Id":7,"BuildingName":"","UserName":"张六","UserPhone":"13888888883","State":8,"NewHouseRunningsInfoShowList":[{"StyleID":1,"ModuleName":"报备","ModuleNameColor":"93B219","ModuleIcon":2,"ModuleIconColor":"93B219","Title":"报备客户","OperTime":"17-07-29 09:53","ViceTitle":"审核通过","ViceTitleColor":"93B219","FilingAuditingRemark":"ssss","IsCameraButton":0,"ImageType":0,"ImageRemark":"","ImageList":[{"GroupID":3,"InterfixID":2,"ImageType":1,"ImagePath":"/Images/BuildingsRunningsImages/","FileName":"201707311114187765307.jpg","ShowIndex":0,"Tag":"","Remark":"","IsLocked":false,"IsDeleted":true,"CreatedTime":"2017-07-31T11:14:19.027","Id":39},{"GroupID":3,"InterfixID":2,"ImageType":1,"ImagePath":"/Images/BuildingsRunningsImages/","FileName":"201707311433408619410.jpg","ShowIndex":0,"Tag":"","Remark":"","IsLocked":false,"IsDeleted":false,"CreatedTime":"2017-07-31T14:33:41.127","Id":53}],"IsViewButton":0,"SortID":1},{"StyleID":3,"ModuleName":"带看","ModuleNameColor":"93B219","ModuleIcon":2,"ModuleIconColor":"93B219","Title":"已带看","OperTime":null,"ViceTitle":"","ViceTitleColor":"","FilingAuditingRemark":null,"IsCameraButton":1,"ImageType":1,"ImageRemark":"带看后请上传照片，\"客户带看单\"照片为必传照片，带看单必需有置业顾问、经纪人、项目经理签字，其他照片可以是\"客户与售楼员合影\"、\"客户与经纪人合影\"等等!","ImageList":[],"IsViewButton":1,"SortID":2},{"StyleID":3,"ModuleName":"认购","ModuleNameColor":"93B219","ModuleIcon":2,"ModuleIconColor":"93B219","Title":"已付定金","OperTime":"17-07-29 09:53","ViceTitle":"0万元","ViceTitleColor":"","FilingAuditingRemark":null,"IsCameraButton":0,"ImageType":2,"ImageRemark":"请上传\"认购确认单\"照片！","ImageList":[],"IsViewButton":0,"SortID":0},{"StyleID":1,"ModuleName":"首付","ModuleNameColor":"93B219","ModuleIcon":2,"ModuleIconColor":"93B219","Title":"已交首付","OperTime":"17-07-29 09:53","ViceTitle":"0万元","ViceTitleColor":"3370FD","FilingAuditingRemark":null,"IsCameraButton":0,"ImageType":0,"ImageRemark":null,"ImageList":null,"IsViewButton":0,"SortID":4},{"StyleID":3,"ModuleName":"备案","ModuleNameColor":"93B219","ModuleIcon":2,"ModuleIconColor":"93B219","Title":"已备案","OperTime":"17-07-29 09:53","ViceTitle":"","ViceTitleColor":"","FilingAuditingRemark":null,"IsCameraButton":1,"ImageType":1,"ImageRemark":"带看后请上传照片，\"客户带看单\"照片为必传照片，带看单必需有置业顾问、经纪人、项目经理签字，其他照片可以是\"客户与售楼员合影\"、\"客户与经纪人合影\"等等!","ImageList":[],"IsViewButton":0,"SortID":5},{"StyleID":4,"ModuleName":"完成","ModuleNameColor":"93B219","ModuleIcon":2,"ModuleIconColor":"93B219","Title":null,"OperTime":null,"ViceTitle":null,"ViceTitleColor":null,"FilingAuditingRemark":null,"IsCameraButton":0,"ImageType":0,"ImageRemark":null,"ImageList":null,"IsViewButton":0,"SortID":6}],"TotalAmount":0,"TaxAmount":0,"AchieveAmount":0,"UnAchieveAmount":0,"BrokerageState":2}
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
         * Id : 7
         * BuildingName :
         * UserName : 张六
         * UserPhone : 13888888883
         * State : 8
         * NewHouseRunningsInfoShowList : [{"StyleID":1,"ModuleName":"报备","ModuleNameColor":"93B219","ModuleIcon":2,"ModuleIconColor":"93B219","Title":"报备客户","OperTime":"17-07-29 09:53","ViceTitle":"审核通过","ViceTitleColor":"93B219","FilingAuditingRemark":"ssss","IsCameraButton":0,"ImageType":0,"ImageRemark":"","ImageList":[{"GroupID":3,"InterfixID":2,"ImageType":1,"ImagePath":"/Images/BuildingsRunningsImages/","FileName":"201707311114187765307.jpg","ShowIndex":0,"Tag":"","Remark":"","IsLocked":false,"IsDeleted":true,"CreatedTime":"2017-07-31T11:14:19.027","Id":39},{"GroupID":3,"InterfixID":2,"ImageType":1,"ImagePath":"/Images/BuildingsRunningsImages/","FileName":"201707311433408619410.jpg","ShowIndex":0,"Tag":"","Remark":"","IsLocked":false,"IsDeleted":false,"CreatedTime":"2017-07-31T14:33:41.127","Id":53}],"IsViewButton":0,"SortID":1},{"StyleID":3,"ModuleName":"带看","ModuleNameColor":"93B219","ModuleIcon":2,"ModuleIconColor":"93B219","Title":"已带看","OperTime":null,"ViceTitle":"","ViceTitleColor":"","FilingAuditingRemark":null,"IsCameraButton":1,"ImageType":1,"ImageRemark":"带看后请上传照片，\"客户带看单\"照片为必传照片，带看单必需有置业顾问、经纪人、项目经理签字，其他照片可以是\"客户与售楼员合影\"、\"客户与经纪人合影\"等等!","ImageList":[],"IsViewButton":1,"SortID":2},{"StyleID":3,"ModuleName":"认购","ModuleNameColor":"93B219","ModuleIcon":2,"ModuleIconColor":"93B219","Title":"已付定金","OperTime":"17-07-29 09:53","ViceTitle":"0万元","ViceTitleColor":"","FilingAuditingRemark":null,"IsCameraButton":0,"ImageType":2,"ImageRemark":"请上传\"认购确认单\"照片！","ImageList":[],"IsViewButton":0,"SortID":0},{"StyleID":1,"ModuleName":"首付","ModuleNameColor":"93B219","ModuleIcon":2,"ModuleIconColor":"93B219","Title":"已交首付","OperTime":"17-07-29 09:53","ViceTitle":"0万元","ViceTitleColor":"3370FD","FilingAuditingRemark":null,"IsCameraButton":0,"ImageType":0,"ImageRemark":null,"ImageList":null,"IsViewButton":0,"SortID":4},{"StyleID":3,"ModuleName":"备案","ModuleNameColor":"93B219","ModuleIcon":2,"ModuleIconColor":"93B219","Title":"已备案","OperTime":"17-07-29 09:53","ViceTitle":"","ViceTitleColor":"","FilingAuditingRemark":null,"IsCameraButton":1,"ImageType":1,"ImageRemark":"带看后请上传照片，\"客户带看单\"照片为必传照片，带看单必需有置业顾问、经纪人、项目经理签字，其他照片可以是\"客户与售楼员合影\"、\"客户与经纪人合影\"等等!","ImageList":[],"IsViewButton":0,"SortID":5},{"StyleID":4,"ModuleName":"完成","ModuleNameColor":"93B219","ModuleIcon":2,"ModuleIconColor":"93B219","Title":null,"OperTime":null,"ViceTitle":null,"ViceTitleColor":null,"FilingAuditingRemark":null,"IsCameraButton":0,"ImageType":0,"ImageRemark":null,"ImageList":null,"IsViewButton":0,"SortID":6}]
         * TotalAmount : 0
         * TaxAmount : 0
         * AchieveAmount : 0
         * UnAchieveAmount : 0
         * BrokerageState : 2
         */

        private int Id;
        private String BuildingName;
        private String UserName;
        private String UserPhone;
        private String CustomerName;
        private String CustomerPhone;
        private int State;
        private String TotalAmount;
        private String TaxAmount;
        private String AchieveAmount;
        private String UnAchieveAmount;
        private int BrokerageState;

        public String getCustomerPhone() {
            return CustomerPhone;
        }

        public void setCustomerPhone(String customerPhone) {
            CustomerPhone = customerPhone;
        }

        public String getCustomerName() {
            return CustomerName;
        }

        public void setCustomerName(String customerName) {
            CustomerName = customerName;
        }

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

        public String getTotalAmount() {
            return TotalAmount;
        }

        public void setTotalAmount(String totalAmount) {
            TotalAmount = totalAmount;
        }

        public String getUnAchieveAmount() {
            return UnAchieveAmount;
        }

        public void setUnAchieveAmount(String unAchieveAmount) {
            UnAchieveAmount = unAchieveAmount;
        }

        public String getAchieveAmount() {
            return AchieveAmount;
        }

        public void setAchieveAmount(String achieveAmount) {
            AchieveAmount = achieveAmount;
        }

        private List<NewHouseRunningsInfoShowListBean> NewHouseRunningsInfoShowList;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getBuildingName() {
            return BuildingName;
        }

        public void setBuildingName(String BuildingName) {
            this.BuildingName = BuildingName;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getUserPhone() {
            return UserPhone;
        }

        public void setUserPhone(String UserPhone) {
            this.UserPhone = UserPhone;
        }

        public int getState() {
            return State;
        }

        public void setState(int State) {
            this.State = State;
        }


        public String getTaxAmount() {
            return TaxAmount;
        }

        public void setTaxAmount(String TaxAmount) {
            this.TaxAmount = TaxAmount;
        }


        public int getBrokerageState() {
            return BrokerageState;
        }

        public void setBrokerageState(int BrokerageState) {
            this.BrokerageState = BrokerageState;
        }

        public List<NewHouseRunningsInfoShowListBean> getNewHouseRunningsInfoShowList() {
            return NewHouseRunningsInfoShowList;
        }

        public void setNewHouseRunningsInfoShowList(List<NewHouseRunningsInfoShowListBean> NewHouseRunningsInfoShowList) {
            this.NewHouseRunningsInfoShowList = NewHouseRunningsInfoShowList;
        }

        public static class NewHouseRunningsInfoShowListBean {
            /**
             * StyleID : 1
             * ModuleName : 报备
             * ModuleNameColor : 93B219
             * ModuleIcon : 2
             * ModuleIconColor : 93B219
             * Title : 报备客户
             * OperTime : 17-07-29 09:53
             * ViceTitle : 审核通过
             * ViceTitleColor : 93B219
             * FilingAuditingRemark : ssss
             * IsCameraButton : 0
             * ImageType : 0
             * ImageRemark :
             * ImageList : [{"GroupID":3,"InterfixID":2,"ImageType":1,"ImagePath":"/Images/BuildingsRunningsImages/","FileName":"201707311114187765307.jpg","ShowIndex":0,"Tag":"","Remark":"","IsLocked":false,"IsDeleted":true,"CreatedTime":"2017-07-31T11:14:19.027","Id":39},{"GroupID":3,"InterfixID":2,"ImageType":1,"ImagePath":"/Images/BuildingsRunningsImages/","FileName":"201707311433408619410.jpg","ShowIndex":0,"Tag":"","Remark":"","IsLocked":false,"IsDeleted":false,"CreatedTime":"2017-07-31T14:33:41.127","Id":53}]
             * IsViewButton : 0
             * SortID : 1
             */

            private int StyleID;
            private String ModuleName;
            private String ModuleNameColor;
            private int ModuleIcon;
            private String ModuleIconColor;
            private String Title;
            private String OperTime;
            private String ViceTitle;
            private String ViceTitleColor;
            private String FilingAuditingRemark;
            private int IsCameraButton;
            private int ImageType;
            private String ImageRemark;
            private int IsViewButton;
            private String GroupID;
            private int SortID;

            public String getGroupID() {
                return GroupID;
            }

            public void setGroupID(String groupID) {
                GroupID = groupID;
            }

            private List<ImageListBean> ImageList;

            public int getStyleID() {
                return StyleID;
            }

            public void setStyleID(int StyleID) {
                this.StyleID = StyleID;
            }

            public String getModuleName() {
                return ModuleName;
            }

            public void setModuleName(String ModuleName) {
                this.ModuleName = ModuleName;
            }

            public String getModuleNameColor() {
                return ModuleNameColor;
            }

            public void setModuleNameColor(String ModuleNameColor) {
                this.ModuleNameColor = ModuleNameColor;
            }

            public int getModuleIcon() {
                return ModuleIcon;
            }

            public void setModuleIcon(int ModuleIcon) {
                this.ModuleIcon = ModuleIcon;
            }

            public String getModuleIconColor() {
                return ModuleIconColor;
            }

            public void setModuleIconColor(String ModuleIconColor) {
                this.ModuleIconColor = ModuleIconColor;
            }

            public String getTitle() {
                return Title;
            }

            public void setTitle(String Title) {
                this.Title = Title;
            }

            public String getOperTime() {
                return OperTime;
            }

            public void setOperTime(String OperTime) {
                this.OperTime = OperTime;
            }

            public String getViceTitle() {
                return ViceTitle;
            }

            public void setViceTitle(String ViceTitle) {
                this.ViceTitle = ViceTitle;
            }

            public String getViceTitleColor() {
                return ViceTitleColor;
            }

            public void setViceTitleColor(String ViceTitleColor) {
                this.ViceTitleColor = ViceTitleColor;
            }

            public String getFilingAuditingRemark() {
                return FilingAuditingRemark;
            }

            public void setFilingAuditingRemark(String FilingAuditingRemark) {
                this.FilingAuditingRemark = FilingAuditingRemark;
            }

            public int getIsCameraButton() {
                return IsCameraButton;
            }

            public void setIsCameraButton(int IsCameraButton) {
                this.IsCameraButton = IsCameraButton;
            }

            public int getImageType() {
                return ImageType;
            }

            public void setImageType(int ImageType) {
                this.ImageType = ImageType;
            }

            public String getImageRemark() {
                return ImageRemark;
            }

            public void setImageRemark(String ImageRemark) {
                this.ImageRemark = ImageRemark;
            }

            public int getIsViewButton() {
                return IsViewButton;
            }

            public void setIsViewButton(int IsViewButton) {
                this.IsViewButton = IsViewButton;
            }

            public int getSortID() {
                return SortID;
            }

            public void setSortID(int SortID) {
                this.SortID = SortID;
            }

            public List<ImageListBean> getImageList() {
                return ImageList;
            }

            public void setImageList(List<ImageListBean> ImageList) {
                this.ImageList = ImageList;
            }

            public static class ImageListBean {
                /**
                 * GroupID : 3
                 * InterfixID : 2
                 * ImageType : 1
                 * ImagePath : /Images/BuildingsRunningsImages/
                 * FileName : 201707311114187765307.jpg
                 * ShowIndex : 0
                 * Tag :
                 * Remark :
                 * IsLocked : false
                 * IsDeleted : true
                 * CreatedTime : 2017-07-31T11:14:19.027
                 * Id : 39
                 */

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
