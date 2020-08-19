package com.mehow.blog.controller.admin;

import com.mehow.blog.entity.Type;
import com.mehow.blog.service.TypeService;
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
public class TypeController {
    @Autowired
    private TypeService typeService;

    /**
     * 默认进入的 list分页页面
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/types")
    public String list(@PageableDefault(size=3,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,
                       Model model)
    {
        model.addAttribute("page",typeService.listType(pageable));
        return "admin/types";
    }

    /**
     * 默认跳转的页面
     * @param model
     * @return
     */
    @GetMapping("/types/input")
    public String typesInput(Model model){
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    /**
     * 新增处理
     * @param type
     * @param result
     * @param attributes
     * @return
     */
    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult  result, RedirectAttributes attributes){
        Type type1=typeService.getTypeByName(type.getName());
        System.out.println("--------type1---------->>>"+type1);
        if(type1!=null){
            result.rejectValue("name","nameError","不能添加重复分类");
           // return "admin/types-input";
        }
        if(result.hasErrors()){
            return "admin/types-input";
        }
        Type t=typeService.saveType(type);
        if(t==null){
            attributes.addFlashAttribute("message","操作失败");
        }else{
            attributes.addFlashAttribute("message","操作成功");
        }
        /**
         * 需要或者页面数据所有需要重定向
         */
        return "redirect:/admin/types";
    }
    /**
     * 编辑页面
     */

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }

    /**
     * 更新处理
     * @param type
     * @param result
     * @param attributes
     * @return
     */
    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, BindingResult result,@PathVariable Long id, RedirectAttributes attributes) {
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if (result.hasErrors()) {
            return "admin/types-input";
        }
        Type t = typeService.updateType(id,type);
        if (t == null ) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/types";
    }
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){

        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }
}
