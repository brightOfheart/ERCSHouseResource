package ercs.com.ercshouseresources.bean;

/**
 * Created by Administrator on 2017/6/21.
 * 封装登录的返回结果
 */

public class LoginBean {


    /**
     * Type : 1
     * Content : 登录成功
     * Data : {"PhotoPath":"/Content/Images/profile_small.jpg","DepName":"推广部11","Authority":2,"SuperiorUser":{"Id":1,"Name":"tommy","Phone":"1384056621"},"Id":2009,"Name":"ee","Phone":"152222222222"}
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
         * PhotoPath : /Content/Images/profile_small.jpg
         * DepName : 推广部11
         * Authority : 2
         * SuperiorUser : {"Id":1,"Name":"tommy","Phone":"1384056621"}
         * Id : 2009
         * Name : ee
         * Phone : 152222222222
         */

        private String PhotoPath;
        private String DepName;
        private int Authority;
        private SuperiorUserBean SuperiorUser;
        private int Id;
        private String Name;
        private String Phone;

        public String getPhotoPath() {
            return PhotoPath;
        }

        public void setPhotoPath(String PhotoPath) {
            this.PhotoPath = PhotoPath;
        }

        public String getDepName() {
            return DepName;
        }

        public void setDepName(String DepName) {
            this.DepName = DepName;
        }

        public int getAuthority() {
            return Authority;
        }

        public void setAuthority(int Authority) {
            this.Authority = Authority;
        }

        public SuperiorUserBean getSuperiorUser() {
            return SuperiorUser;
        }

        public void setSuperiorUser(SuperiorUserBean SuperiorUser) {
            this.SuperiorUser = SuperiorUser;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
        }

        public static class SuperiorUserBean {
            /**
             * Id : 1
             * Name : tommy
             * Phone : 1384056621
             */

            private int Id;
            private String Name;
            private String Phone;

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public String getPhone() {
                return Phone;
            }

            public void setPhone(String Phone) {
                this.Phone = Phone;
            }
        }
    }
}
