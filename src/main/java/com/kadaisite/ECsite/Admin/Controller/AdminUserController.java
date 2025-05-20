package com.kadaisite.ECsite.Admin.Controller;

import com.kadaisite.ECsite.Admin.Form.NewUserForm;
import com.kadaisite.ECsite.Admin.Service.ECsiteUserService;
import com.kadaisite.ECsite.User.Validate.AdminNewCreateGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AdminUserController {
    private final ECsiteUserService insertUser;

    //  ユーザーの新規登録画面
    @GetMapping("/admin/create")
    public String create(@ModelAttribute NewUserForm newUserForm){
        return "admin/AdminUser/createUser";
    }
    //    ユーザーの新規追加
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
}
