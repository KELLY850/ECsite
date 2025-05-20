package com.kadaisite.ECsite.Admin.Controller;

import com.kadaisite.ECsite.Admin.Repository.AdminUsersMapper;
import com.kadaisite.ECsite.Admin.Service.ECsiteUserService;
import com.kadaisite.ECsite.Admin.Validate.UpdatedGroup;
import com.kadaisite.ECsite.User.Entity.User;
import com.kadaisite.ECsite.User.Form.NewAddForm;
import com.kadaisite.ECsite.User.mapstruct.UserFormMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
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
public class UserController {
    private final AdminUsersMapper adminUsersMapper;
    private final ECsiteUserService eCsiteUserService;
    private final UserFormMapper userFormMapper;

//    お客様一覧画面
    @GetMapping("/admin/usersList")
    public String usersList(Model model){
        List<User> users= adminUsersMapper.getAllUsers();
        if(users.isEmpty()){
            model.addAttribute("message","まだお客様の登録がありません。");
            return "/admin/users/usersList";
        }
        model.addAttribute("users",users);
        return "/admin/users/usersList";
    }
//    お客様詳細画面
    @GetMapping("/admin/usersList/{id}")
    public String userDetail(@PathVariable("id") Long id,Model model){

        User user = eCsiteUserService.selectId(id);

        if(user == null){
            model.addAttribute("message","お客様情報を取得出来ませんでした。");
            return "/admin/users/userEdit";
        }
        model.addAttribute("user",user);
        return "/admin/users/userEdit";
    }
//    お客様編集画面
    @GetMapping("/admin/usersList/edit/{id}")
    public String userEditPage(@PathVariable("id") Long id,
//                           @ModelAttribute NewAddForm newAddForm,
                           Model model){
        User user =eCsiteUserService.selectId(id);
        NewAddForm newAddForm =userFormMapper.toForm(user);
        newAddForm.setId(user.getId());
        model.addAttribute("newAddForm",newAddForm);
//        model.addAttribute("user",user);
        return "/admin/users/editForm";
    }
//    お客様情報アップデート
    @PostMapping("/admin/usersList/edit/{id}")
    public String userEdit(@PathVariable("id") Long id,
                           @Validated(UpdatedGroup.class)
                           @ModelAttribute NewAddForm newAddForm,
                           BindingResult result,
                           Model model,
                           RedirectAttributes redirectAttributes
                           ){
        if(result.hasErrors()){
            model.addAttribute("error","入力内容の確認を再度してください");
            return "/admin/users/editForm";
        }
        try{
            User user =userFormMapper.toEntity(newAddForm);
            user.setId(id);//ここでIDをセットしておく。サービス層でID取得できるように。
            int num = eCsiteUserService.updatedUser(user);
            if(num>0){
                redirectAttributes.addFlashAttribute("message","変更した内容を更新しました");
            }else{
                model.addAttribute("error","更新ができませんでした。");
                return "/admin/users/editForm";
            }
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error","同じメールアドレスが既に登録されており、更新できませんでした");
            return "/admin/users/editForm";
        }
        return "redirect:/admin/usersList/{id}";
    }
}
