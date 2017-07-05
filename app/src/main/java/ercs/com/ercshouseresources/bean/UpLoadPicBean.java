package ercs.com.ercshouseresources.bean;

/**
 * Created by Administrator on 2017/7/4.
 * 上传打卡拍照图片的BEAN
 */

public class UpLoadPicBean extends BaseBean {
    private DataBean Data;

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean data) {
        Data = data;
    }

    public class DataBean {
        private String PhotoPath;
        private String PhotoName;
        private String PhotoExName;
        private String InUse;
        private String IsLocked;
        private String IsDeleted;
        private String CreatedTime;
        private String Id;

        public String getPhotoPath() {
            return PhotoPath;
        }

        public void setPhotoPath(String photoPath) {
            PhotoPath = photoPath;
        }

        public String getPhotoExName() {
            return PhotoExName;
        }

        public void setPhotoExName(String photoExName) {
            PhotoExName = photoExName;
        }

        public String getPhotoName() {
            return PhotoName;
        }

        public void setPhotoName(String photoName) {
            PhotoName = photoName;
        }

        public String getInUse() {
            return InUse;
        }

        public void setInUse(String inUse) {
            InUse = inUse;
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
