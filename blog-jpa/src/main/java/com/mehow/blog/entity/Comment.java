package com.mehow.blog.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 评论表
 * @Author: xuhui
 * @Date: 2020/8/1 23:57
 */
@Entity
@Table(name="t_comment")
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 邮箱
     */
    private String email;

    /*
     * 头像
     */
    private String avatar;
    /**
     * 评论内容
     */
    /**
     * 是否有效，默认为0
     */
    private boolean status=true;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @ManyToOne
    private Blog blog;

    public boolean isAdminComment() {
        return adminComment;
    }

    public void setAdminComment(boolean adminComment) {
        this.adminComment = adminComment;
    }

    private boolean adminComment;
    @ManyToOne
    private Comment parentComment;
    /**
     * 子关联
     * @return
     */
    @OneToMany(mappedBy = "parentComment")
    private List<Comment> replyComments = new ArrayList<>();

    public List<Comment> getReplyComments() {
        return replyComments;
    }

    public void setReplyComments(List<Comment> replyComments) {
        this.replyComments = replyComments;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }


    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public Comment(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", nickName='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", status=" + status +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", blog=" + blog +
                ", adminComment=" + adminComment +
                ", parentComment=" + parentComment +
                ", replyComments=" + replyComments +
                '}';
    }
}
