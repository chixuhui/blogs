package com.mehow.blog.controller.admin;

import com.mehow.blog.entity.Blog;
import com.mehow.blog.entity.Type;
import com.mehow.blog.entity.User;
import com.mehow.blog.service.BlogService;
import com.mehow.blog.service.TagService;
import com.mehow.blog.service.TypeService;
import com.mehow.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


/**
 * 博客管理
 * @Author: xuhui
 * @Date: 2020/8/4 16:55
 */

@Controller
@RequestMapping("/admin")
public class BlogController {
    private static final String INPUT = "admin/blogs-input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService  tagService;

    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 5,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                        BlogQuery blog, Model model){
        model.addAttribute("types", typeService.listType());
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        return LIST;
    }
    /*新增*/
    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes  attributes, HttpSession session){
        blog.setUser((User) session.getAttribute("user"));//从session里面取 user对象
        blog.setType(typeService.getType(blog.getType().getIds()));
        blog.setTags(tagService.listTag(blog.getTagIds()));
        Blog b;
        if (blog.getId() == null) {//为 null 则新增
            b =  blogService.saveBlog(blog);
        } else {
            b = blogService.updateBlog(blog.getId(), blog);
        }
        if(b==null){
            attributes.addFlashAttribute("message","新增失败");
        }else{
            attributes.addFlashAttribute("message","操作成功");
        }
        return REDIRECT_LIST;
    }
    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 5,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                        BlogQuery blog, Model model){
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        /*这种方法 相当于返回一个片段*/
        return "admin/blogs :: blogList";
    }
    @GetMapping("/blogs/input")
    public String input(Model model) {
        setTypeAndTag(model);
        model.addAttribute("blog", new Blog());
        return INPUT;
    }
    private void setTypeAndTag(Model model) {
        System.out.println("---typeService.listType()------>>>"+typeService.listType());
        System.out.println("---tagService.listTag()------>>>"+tagService.listTag());
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
    }
    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        setTypeAndTag(model);
        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog",blog);
        return INPUT;
    }
    @PostMapping("/blogs/{id}/delete")
    public String delete(@PageableDefault(size = 5,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                         @PathVariable Long id,RedirectAttributes attributes,
                         BlogQuery blog, Model model) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        /*init table data*/
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        return "admin/blogs :: blogList";
    }

}
