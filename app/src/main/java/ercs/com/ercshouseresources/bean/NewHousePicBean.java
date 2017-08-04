package ercs.com.ercshouseresources.bean;

/**
 * 新房图片
 * Created by Administrator on 2017/7/31.
 */

public class NewHousePicBean {
    private String id;
    private String path;

    public NewHousePicBean(String id, String path) {
        this.id = id;
        this.path = path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
