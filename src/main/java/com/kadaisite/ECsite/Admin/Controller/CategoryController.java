package com.kadaisite.ECsite.Admin.Controller;

import com.kadaisite.ECsite.Admin.Entity.Categories;
import com.kadaisite.ECsite.Admin.Form.CategoryForm;
import com.kadaisite.ECsite.Admin.Service.CategoryService;
import com.kadaisite.ECsite.Admin.mapstruct.CategoryFormMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryFormMapper categoryFormMapper;
    //カテゴリー登録画面
    @GetMapping("/admin/config/createCategory")
    public String CreateCategory(Model model){
        List<Categories> categories = categoryService.CategoriesList();
        if(categories == null){
            model.addAttribute("error","カテゴリーの登録はまだありません");
        }
        model.addAttribute("categories",categories);
        model.addAttribute("categoryForm", new CategoryForm());
        return "/admin/config/category";
    }
    @PostMapping("/admin/config/createCategory")
    public String CategoryAdd(@Validated @ModelAttribute CategoryForm categoryForm,
                              BindingResult result,
                              Model model){
        if (result.hasErrors()){
            return "/admin/config/category";
        }
        try {
            Categories categories = categoryFormMapper.toEntity(categoryForm);
            categoryService.save(categories);
        } catch (Exception e) {
            model.addAttribute("error","登録に失敗しました");
            return "/admin_config/category";
        }
        return "redirect:/admin/config/createCategory";
    }

}
