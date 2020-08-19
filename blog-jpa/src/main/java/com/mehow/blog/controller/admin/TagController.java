package com.mehow.blog.controller.admin;

import com.mehow.blog.entity.Tag;
import com.mehow.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * 分类管理
 * @Author: xuhui
 * @Date: 2020/8/5 13:57
 */
@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TagService tagService;

    /**
     * 默认进入的 list分页页面
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/tags")
    public String list(@PageableDefault(size=3,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,
                       Model model)
    {
        model.addAttribute("page",tagService.listTag(pageable));
        return "admin/tags";
    }

    /**
     * 默认跳转的页面
     * @param model
     * @return
     */
    @GetMapping("/tags/input")
    public String tagsInput(Model model){
        model.addAttribute("tag", new Tag());
        return "admin/tags-input";
    }

    /**
     * 新增处理
     * @param tag
     * @param result
     * @param attributes
     * @return
     */
    @PostMapping("/tags")
    public String post(@Valid Tag tag, BindingResult  result, RedirectAttributes attributes){
        Tag tag1=tagService.getTagByName(tag.getName());
        System.out.println("--------tag---------->>>"+tag1);
        if(tag1!=null){
            result.rejectValue("name","nameError","不能添加重复分类");
        }
        if(result.hasErrors()){
            return "admin/tags-input";
        }
        Tag t=tagService.saveTag(tag);
        if(t==null){
            attributes.addFlashAttribute("message","操作失败");
        }else{
            attributes.addFlashAttribute("message","操作成功");
        }
        /**
         * 需要或者页面数据所有需要重定向
         */
        return "redirect:/admin/tags";
    }
    /**
     * 编辑页面
     */

    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("tag",tagService.getTag(id));
        return "admin/tags-input";
    }

    /**
     * 更新处理
     * @param tag
     * @param result
     * @param attributes
     * @return
     */
    @PostMapping("/tags/{id}")
    public String editPost(@Valid Tag tag, BindingResult result,@PathVariable Long id, RedirectAttributes attributes) {
        Tag tag1 = tagService.getTagByName(tag.getName());
        if (tag1 != null) {
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if (result.hasErrors()) {
            return "admin/tags-input";
        }
        Tag t = tagService.updateTag(id,tag);
        if (t == null ) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/tags";
    }
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){

        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/tags";
    }
}
