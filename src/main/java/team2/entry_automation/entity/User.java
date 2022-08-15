package team2.entry_automation.entity;

/**
 * @author JackChern @create 2022-08-14 17:03
 */
public class User {
    private int id;
    private String username;
    private String password;
    //  盐，密码后面加的随机uuid，增强密码的安全性
    private String salt;
    private String email;
    private int type;

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername( String username ) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt( String salt ) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public int getType() {
        return type;
    }

    public void setType( int type ) {
        this.type = type;
    }
}
