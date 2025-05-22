package com.kadaisite.ECsite.Admin.Controller;

import com.kadaisite.ECsite.Admin.Entity.Admin_users;
import com.kadaisite.ECsite.Admin.Form.NewUserForm;
import com.kadaisite.ECsite.Admin.Form.UpdateForm;
import com.kadaisite.ECsite.Admin.Repository.AdminMapper;
import com.kadaisite.ECsite.Admin.Service.AdminUserService;
import com.kadaisite.ECsite.Admin.Service.ECsiteUserService;
import com.kadaisite.ECsite.Admin.mapstruct.AdminFormMapper;
import com.kadaisite.ECsite.User.Validate.AdminNewCreateGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminUserController {
    private final ECsiteUserService insertUser;
    private final AdminMapper adminMapper;
    private final AdminFormMapper adminFormMapper;
    private final AdminUserService adminUserService;

    //  管理者ユーザーの新規登録画面
    @GetMapping("/admin/create")
    public String create(@ModelAttribute NewUserForm newUserForm){
        return "admin/AdminUser/createUser";
    }
    //    管理者ユーザーの新規追加
    @PostMapping("/admin/create")
    public String createUser(
            @Validated(AdminNewCreateGroup.class) @ModelAttribute NewUserForm newUserForm,
            BindingResult result,
            Model model){
        if (result.hasErrors()) {
            return "admin/AdminUser/createUser"; // バリデーションエラー時は元の画面に戻す
        }
        try {
//            責任分離の書き方をしてみました。insertUserサービスの中で登録処理実装。
            insertUser.register(newUserForm);
        } catch (Exception e) {
            model.addAttribute("error","登録に失敗しました。メールアドレスがすでに登録されている可能性があります。");
            return "admin/AdminUser/createUser";
        }
//        TOP画面に返す。
        return "redirect:/admin";
    }

//    管理者ユーザーの詳細画面
    @GetMapping("/admin/adminsUsers/{id}")
    public String adminDetail(
            @PathVariable("id") Long id,
            Model model){
        Admin_users admin= adminMapper.selectbyId(id);
        model.addAttribute("adminUser",admin);
        return "admin/AdminUser/adminUserDetail";
    }
//    管理者編集画面
    @GetMapping("/admin/adminsUsers/detail/{id}")
    public String adminEdit(
                    @PathVariable("id")Long id,
                    Model model
                    ){
        Admin_users admin_users =adminMapper.selectbyId(id);
        UpdateForm updateForm = adminFormMapper.toForm(admin_users);
        List<String>roles= List.of("ADMIN","USER");
        model.addAttribute("updateForm",updateForm);
        model.addAttribute("roles",roles);
        return "admin/AdminUser/adminUserEdit";
    }
//    編集・更新
    @PostMapping("/admin/adminsUsers/detail/{id}")
    public String adminEditForm(@PathVariable("id")Long id,
                                @Validated @ModelAttribute UpdateForm updateForm,
                                BindingResult result,
                                Model model,
                                RedirectAttributes redirectAttributes){
        List<String>roles=List.of("ADMIN","USER");
        if(result.hasErrors()){

            model.addAttribute("roles",roles);
            model.addAttribute("error","下記内容を確認して改めて更新してください");
            return "admin/AdminUser/adminUserEdit";
        }
        try {
            Admin_users admin_users = adminFormMapper.toEntity(updateForm);
            admin_users.setId(id);
            int num =adminUserService.updateAdmin(admin_users);
            if (num>0){
                redirectAttributes.addFlashAttribute("message","変更を更新しました");
            }else {
                model.addAttribute("roles",admin_users.getRole());
                model.addAttribute("error","変更はありませんでした");
                return "admin/AdminUser/adminUserEdit";
            }
        } catch (RuntimeException e) {
            model.addAttribute("roles",roles);
            model.addAttribute("error","同じメールアドレスが既に登録されています");
//            model.addAttribute("error",e.getMessage());
            return "admin/AdminUser/adminUserEdit";
        }
        return "redirect:/admin/adminsUsers/{id}";
    }
}
