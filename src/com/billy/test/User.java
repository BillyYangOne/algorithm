package com.billy.test;

import java.util.Date;

/**
 * 联系用户对象
 */
public class User {

    private String userName;
    private String sex;
    private Date birthDay;
    private Integer age;
    private String[] names;

    public User() {}

    public User(String UserName) {
        this.userName = UserName;
    }

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
