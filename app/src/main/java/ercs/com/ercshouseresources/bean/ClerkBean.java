package ercs.com.ercshouseresources.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/22.
 * 封装职员列表的数据
 */

public class ClerkBean {
    private String Content;
    private String Type;
    private List<Datas> Data;

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

    public List<Datas> getData() {
        return Data;
    }

    public void setData(List<Datas> data) {
        Data = data;
    }

    public class Datas {
        private String Authority;
        private String Name;
        private String Phone;
        private String PhotoPath;
        private String Id;
        private String Sex;
        private String IsShopkeeper;

        public String getSex() {
            return Sex;
        }

        public void setSex(String sex) {
            Sex = sex;
        }

        public String getIsShopkeeper() {
            return IsShopkeeper;
        }

        public void setIsShopkeeper(String isShopkeeper) {
            IsShopkeeper = isShopkeeper;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getAuthority() {
            return Authority;
        }

        public void setAuthority(String authority) {
            Authority = authority;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String phone) {
            Phone = phone;
        }

        public String getPhotoPath() {
            return PhotoPath;
        }

        public void setPhotoPath(String photoPath) {
            PhotoPath = photoPath;
        }

        public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }
    }
}
