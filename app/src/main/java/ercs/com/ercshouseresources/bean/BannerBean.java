package ercs.com.ercshouseresources.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/13.
 */

public class BannerBean extends BaseBean {
    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> data) {
        Data = data;
    }

    public class DataBean {
        private String Id;
        private String Text;
        private String AdvertisementType;
        private String DynamicID;
        private List<String> ImagePath;

        public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }

        public String getText() {
            return Text;
        }

        public void setText(String text) {
            Text = text;
        }

        public String getAdvertisementType() {
            return AdvertisementType;
        }

        public void setAdvertisementType(String advertisementType) {
            AdvertisementType = advertisementType;
        }

        public String getDynamicID() {
            return DynamicID;
        }

        public void setDynamicID(String dynamicID) {
            DynamicID = dynamicID;
        }

        public List<String> getImagePath() {
            return ImagePath;
        }

        public void setImagePath(List<String> imagePath) {
            ImagePath = imagePath;
        }
    }
}
