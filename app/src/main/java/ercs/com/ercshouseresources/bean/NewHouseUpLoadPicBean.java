package ercs.com.ercshouseresources.bean;

/**
 * 新房上传图片成功回调
 * Created by Administrator on 2017/7/31.
 */

public class NewHouseUpLoadPicBean {


    /**
     * Type : 1
     * Content : 上传成功
     * Data : {"GroupID":3,"InterfixID":2,"ImageType":1,"ImagePath":"/Images/BuildingsRunningsImages/","FileName":"201708041434127397494.jpg","ShowIndex":0,"Tag":"","Remark":"","IsLocked":false,"IsDeleted":false,"CreatedTime":"2017-08-04T14:34:12.9581498+08:00","Id":62}
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

    public static class DataBean   {
        /**
         * GroupID : 3
         * InterfixID : 2
         * ImageType : 1
         * ImagePath : /Images/BuildingsRunningsImages/
         * FileName : 201708041434127397494.jpg
         * ShowIndex : 0
         * Tag :
         * Remark :
         * IsLocked : false
         * IsDeleted : false
         * CreatedTime : 2017-08-04T14:34:12.9581498+08:00
         * Id : 62
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
