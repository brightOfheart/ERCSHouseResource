package ercs.com.ercshouseresources.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/13.
 */

public class GDynamicBean extends BaseBean {
    private DataBean Data;

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean data) {
        Data = data;
    }

    public class DataBean {
        private String Id;
        private String Title;
        private List<String> ImagePathList;
        private String ReleaseTime;
        private String Subject;

        public String getSubject() {
            return Subject;
        }

        public void setSubject(String subject) {
            Subject = subject;
        }

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

        public List<String> getImagePathList() {
            return ImagePathList;
        }

        public void setImagePathList(List<String> imagePathList) {
            ImagePathList = imagePathList;
        }

        public String getReleaseTime() {
            return ReleaseTime;
        }

        public void setReleaseTime(String releaseTime) {
            ReleaseTime = releaseTime;
        }
    }
}
