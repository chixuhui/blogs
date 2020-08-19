package com.mehow.blog.controller.admin;

import com.mehow.blog.entity.Type;
import com.mehow.blog.entity.User;
import com.mehow.blog.service.TypeService;
import com.mehow.blog.service.UserService;
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
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 默认进入的 list分页页面
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/users")
    public String list(@PageableDefault(size=3,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,
                       Model model)
    {
        model.addAttribute("page",userService.listUser(pageable));
        return "admin/users";
    }

    /**
     * 默认跳转的页面
     * @param model
     * @return
     */
    @GetMapping("/users/input")
    public String typesInput(Model model){
        model.addAttribute("users", new User());
        return "admin/users-input";
    }

    /**
     * 新增处理
     * @param user
     * @param result
     * @param attributes
     * @return
     */
    @PostMapping("/users")
    public String post(@Valid User user, BindingResult  result, RedirectAttributes attributes){
        User user1=userService.getUserByName(user.getUsername());
        System.out.println("--------type1---------->>>"+user1);
        if(user1!=null){
            result.rejectValue("name","nameError","不能添加重复分类");
           // return "admin/types-input";
        }
        if(result.hasErrors()){
            return "admin/users-input";
        }
        User t=userService.saveUser(user);
        if(t==null){
            attributes.addFlashAttribute("message","操作失败");
        }else{
            attributes.addFlashAttribute("message","操作成功");
        }
        /**
         * 需要或者页面数据所有需要重定向
         */
        return "redirect:/admin/users";
    }
    /**
     * 编辑页面
     */

    @GetMapping("/users/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("users",userService.getUser(id));
        return "admin/users-input";
    }

    /**
     * 更新处理
     * @param user
     * @param result
     * @param attributes
     * @return
     */
    @PostMapping("/users/{id}")
    public String editPost(@Valid User user, BindingResult result,@PathVariable Long id, RedirectAttributes attributes) {
        User type1 = userService.getUserByName(user.getUsername());
        if (type1 != null) {
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if (result.hasErrors()) {
            return "admin/users-input";
        }
        User t = userService.updateUser(id,user);
        if (t == null ) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/users";
    }
    @GetMapping("/users/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){

        userService.deleteUser(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/users";
    }
}
