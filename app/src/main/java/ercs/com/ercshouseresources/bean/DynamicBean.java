package ercs.com.ercshouseresources.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/1.
 * 封装动态的Bean
 */

public class DynamicBean extends BaseBean {
    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> data) {
        Data = data;
    }

    public class DataBean {
        private String Id;
        private String Title;
        private String Subject;
        private String ReleaseTime;
        private List<ImagePathBean> ImagePath;

        public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            Title = title;
        }

        public String getSubject() {
            return Subject;
        }

        public void setSubject(String subject) {
            Subject = subject;
        }

        public String getReleaseTime() {
            return ReleaseTime;
        }

        public void setReleaseTime(String releaseTime) {
            ReleaseTime = releaseTime;
        }

        public List<ImagePathBean> getImagePath() {
            return ImagePath;
        }

        public void setImagePath(List<ImagePathBean> imagePath) {
            ImagePath = imagePath;
        }

        public class ImagePathBean {
            private String GroupID;
            private String InterfixID;
            private String ImageType;
            private String ImagePath;
            private String FileName;
            private String ShowIndex;
            private String Tag;
            private String Remark;
            private String IsLocked;
            private String IsDeleted;
            private String CreatedTime;

            public String getGroupID() {
                return GroupID;
            }

            public void setGroupID(String groupID) {
                GroupID = groupID;
            }

            public String getInterfixID() {
                return InterfixID;
            }

            public void setInterfixID(String interfixID) {
                InterfixID = interfixID;
            }

            public String getImageType() {
                return ImageType;
            }

            public void setImageType(String imageType) {
                ImageType = imageType;
            }

            public String getImagePath() {
                return ImagePath;
            }

            public void setImagePath(String imagePath) {
                ImagePath = imagePath;
            }

            public String getFileName() {
                return FileName;
            }

            public void setFileName(String fileName) {
                FileName = fileName;
            }

            public String getShowIndex() {
                return ShowIndex;
            }

            public void setShowIndex(String showIndex) {
                ShowIndex = showIndex;
            }

            public String getTag() {
                return Tag;
            }

            public void setTag(String tag) {
                Tag = tag;
            }

            public String getIsLocked() {
                return IsLocked;
            }

            public void setIsLocked(String isLocked) {
                IsLocked = isLocked;
            }

            public String getRemark() {
                return Remark;
            }

            public void setRemark(String remark) {
                Remark = remark;
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
    }
}
