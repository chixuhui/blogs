package com.mehow.blog.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 博客主表
 * @Author: xuhui
 * @Date: 2020/8/1 23:30
 */
@Entity
@Table(name="t_blog")
public class Blog {
    @Id
    @GeneratedValue
    private Long id;
    /**
     * 标题   是否原创等
     */
    private String title;
    /**
     * 内容
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String content;
    /**
     * 首图
     */
    private String firstPicture;
    /**
     * 标记   原创/转载
     */
    private String flag;
    /**
     * 浏览几次
     */
    private Integer views;
    /**
     * 赞赏是否开启
     */
    private boolean appreciation;
    /**
     * 转载声明是否开起
     */
    private boolean shareStatement;
    /**
     * 评论似是否开启
     */
    private boolean commentabled;
    /**
     * 是否发布  点击保存默认为草稿，点击发布才为true
     */
    private boolean published;
    /**
     * 是否推荐
     */
    private boolean recommend;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 博客描述
     */
    private String description;
    private Date createTime;
    private Date updateTime;
    @ManyToOne
    private Type type;
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

    /**
     * 级联新增
     */
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tags=new ArrayList<>();
    @ManyToOne
    private User user;
    @OneToMany(mappedBy="blog")
    private List<Comment> comments=new ArrayList<>();


    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Type getType() {
        return type;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void setType(Type type) {
        this.type = type;
    }
    @Transient
    private String tagIds;
    //1,2,3
    private String tagsToIds(List<Tag> tags) {
        if (!tags.isEmpty()) {
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        } else {
            return tagIds;
        }
    }
    /*测试字段，不需要写入 数据库*/
    @Transient
    private String typeIds;

    public String getTypeIds() {
        return typeIds;
    }

    public void setTypeIds(String typeIds) {
        this.typeIds = typeIds;
    }

    public Blog() {

    }
    public void init() {
        this.tagIds = tagsToIds(this.getTags());
    }
    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public boolean isAppreciation() {
        return appreciation;
    }

    public void setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
    }

    public boolean isShareStatement() {
        return shareStatement;
    }

    public void setShareStatement(boolean shareStatement) {
        this.shareStatement = shareStatement;
    }

    public boolean isCommentabled() {
        return commentabled;
    }

    public void setCommentabled(boolean commentabled) {
        this.commentabled = commentabled;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
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
