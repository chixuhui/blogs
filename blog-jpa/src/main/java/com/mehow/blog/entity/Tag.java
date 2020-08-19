package com.mehow.blog.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 标签
 * @Author: xuhui
 * @Date: 2020/8/1 23:52
 */
@Entity
@Table(name="t_tag")
public class Tag {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "标签名称不能为空")
    private String name;
    /**
     * 添加日期
     */
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateTime;
    /**
     * 是否有效，默认为0
     */
    private boolean status=true;
    @ManyToMany(mappedBy = "tags")
    private List<Blog> blogs=new ArrayList<>();
    public Tag(){}

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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status='" + status + '\'' +
                ", blogs=" + blogs +
                '}';
    }
}
