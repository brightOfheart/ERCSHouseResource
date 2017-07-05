package ercs.com.ercshouseresources.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 * 封装外勤统计的bean
 */

public class FieldBean extends BaseBean{
    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> data) {
        Data = data;
    }

    public class DataBean
    {
        private String CreatedTime;
        private String Id;
        private String Location;
        private String OutSideContent;
        private String ImagePage;

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

        public String getLocation() {
            return Location;
        }

        public void setLocation(String location) {
            Location = location;
        }

        public String getOutSideContent() {
            return OutSideContent;
        }

        public void setOutSideContent(String outSideContent) {
            OutSideContent = outSideContent;
        }

        public String getImagePage() {
            return ImagePage;
        }

        public void setImagePage(String imagePage) {
            ImagePage = imagePage;
        }
    }
}
