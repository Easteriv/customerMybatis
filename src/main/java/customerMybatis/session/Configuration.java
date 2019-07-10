package customerMybatis.session;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhaojiejun
 * @program: customerMybatis
 * @description: 获取connection对象
 * @create: 2019-07-10 19:47
 **/
public class Configuration {
    private String driver;
    private String url;
    private String username;
    private String password;
    private Map<String,Mapper> mappers = new HashMap();


    public String getDriver() {
        return driver;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取连接对象
     *
     * @return
     */
    public Connection getConnection() throws SQLException {
        try {
            //注册驱动
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //创建连接
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }
    public void setMappers(Map<String, Mapper> mappers) {
        //每次合并maper
        this.mappers.putAll(mappers);
    }

    public Map getMappers() {
        return mappers;
    }
}
