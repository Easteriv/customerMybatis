package customerMybatis.entity;

/**
 * @author: zhaojiejun
 * @program: customerMybatis
 * @description:
 * @create: 2019-07-10 11:21
 **/
public class User {
    private String uid;
    private String password;
    private String username;
    private String birthday;
    private String address;
    private String other;

    public String getUid() {
        return uid;
    }


    public String getBirthday() {
        return birthday;
    }

    public String getAddress() {
        return address;
    }

    public String getOther() {
        return other;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setOther(String other) {
        this.other = other;
    }

}
