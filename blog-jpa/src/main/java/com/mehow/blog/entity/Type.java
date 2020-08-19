package com.mehow.blog.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 类型
 * @Author: xuhui
 * @Date: 2020/8/1 23:50
 */
@Entity
@Table(name="t_type")
public class Type {
    @Id
    @GeneratedValue
    private Long id;
    /**
     * 分类名字
     */
    @NotBlank(message = "分类名称不能为空")
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
    /*ids 用来给id 传值的 不需要写入数据库*/
    @Transient
    private Long ids;

    public Long getIds() {
        return ids;
    }

    public void setIds(Long ids) {
        this.ids = ids;
    }

    public boolean isStatus() {
        return status;
    }

    /**
     * 是否有效，默认为0
     */
    private boolean status=true;

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

    @OneToMany(mappedBy = "type")
    private List<Blog> blogs=new ArrayList<>();
    public Type(){}

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
        id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status='" + status + '\'' +
                ", blogs=" + blogs +
                '}';
    }
}
