package com.kadaisite.ECsite.User.Controller;

import com.kadaisite.ECsite.User.Entity.User;
import com.kadaisite.ECsite.User.Form.LoginForm;
import com.kadaisite.ECsite.User.Form.NewAddForm;
import com.kadaisite.ECsite.User.Service.UserService;
import com.kadaisite.ECsite.User.Validate.CreateGroup;
import com.kadaisite.ECsite.User.mapstruct.UserFormMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class PageMappingController {
    private final UserFormMapper userFormMapper;
    private final UserService userService;
//    TOP画面
    @GetMapping("/")
    public  String top(@RequestParam(value = "logout",required = false)String logout,Model model){
        if(logout != null){
            model.addAttribute("message","ログアウトに成功しました");
        }
        return  "/users/index";
    }

//    ログインフォーム表示
    @GetMapping("/login")
    public String loginForm(@ModelAttribute LoginForm loginForm){
        return "/users/Auth/login";
    }
//    結局、バリデーションはVue.jsにした。完全にバリデーションをLoginFormでしたくなる可能性もあるのでこのまま残しとく。
    @PostMapping("/login")
    public  String login(@Validated @ModelAttribute LoginForm loginForm,BindingResult result,Model model){
        if(result.hasErrors()){
            return "/users/Auth/login";
        }
        return "redirect:/";
    }
    @GetMapping("/logout")
    public String logout(){
        return "/users/Auth/logout";
    }

//新規登録画面
    @GetMapping("/newEntry")
    public String newEntry(@ModelAttribute NewAddForm newAddForm){
        return "/users/newAdd/newEntry";
    }
    @PostMapping("/newEntry")
    public String Entry(@Validated(CreateGroup.class)
                            @ModelAttribute NewAddForm newAddForm,
                        BindingResult result,
                        Model model,
                        RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            return "/users/newAdd/newEntry";
        }
        try {
            User user = userFormMapper.toEntity(newAddForm);
            userService.save(user);
        }catch (Exception e){
            model.addAttribute("error","登録に失敗しました。再度入力内容を確認して登録してください");
            return "/users/newAdd/newEntry";
        }
        redirectAttributes.addFlashAttribute("message","登録に成功しました");
        return "redirect:/";
    }
}
