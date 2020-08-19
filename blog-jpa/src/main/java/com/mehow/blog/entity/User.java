package com.mehow.blog.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户表
 * @Author: xuhui
 * @Date: 2020/8/2 0:03
 */

@Entity
@Table(name="t_user")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    /*作者名称*/
    private String nickName;
    /*登录名*/
    private String username;
    private String password;
    /**
     * email
     */
    private String email;
    /**
     * 类型
     */
    private String type;
    /**
     * 是否有效，默认为0
     */
    private boolean status=true;
    /**
     * 头像
     */
    private String avatar;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateTime;
    @OneToMany(mappedBy = "user")
    private List<Blog> blogs=new ArrayList<>();
    public User(){}

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
