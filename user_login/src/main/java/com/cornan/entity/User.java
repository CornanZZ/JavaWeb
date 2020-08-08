package com.cornan.entity;

import lombok.Data;
import lombok.experimental.Accessors;

//import java.sql.DatabaseMetaData;
import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class User implements Serializable {
    private String id;
    private String username;
    private String realname;
    private String password;
    private String sex;
    private Date registerTime;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id.toString();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String name) {
        this.realname = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date date) {
        this.registerTime = date;
    }
}
