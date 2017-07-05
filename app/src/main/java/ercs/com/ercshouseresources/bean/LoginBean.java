package ercs.com.ercshouseresources.bean;

/**
 * Created by Administrator on 2017/6/21.
 * 封装登录的返回结果
 */

public class LoginBean {
    private String Type;
    private String Content;

    public Datas getData() {
        return Data;
    }

    public void setData(Datas data) {
        Data = data;
    }

    private Datas Data;

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public class Datas {
        private String Id;
        private String Name;
        private String PhotoPath;
        private String DepName;

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

        public String getPhotoPath() {
            return PhotoPath;
        }

        public void setPhotoPath(String photoPath) {
            PhotoPath = photoPath;
        }

        public String getDepName() {
            return DepName;
        }

        public void setDepName(String depName) {
            DepName = depName;
        }
    }
}
