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
        private List<String> ImagePath;

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

        public List<String> getImagePath() {
            return ImagePath;
        }

        public void setImagePath(List<String> imagePath) {
            ImagePath = imagePath;
        }
    }
}
