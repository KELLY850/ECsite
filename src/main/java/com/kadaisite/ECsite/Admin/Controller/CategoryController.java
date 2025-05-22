package com.kadaisite.ECsite.Admin.Controller;

import com.kadaisite.ECsite.Admin.Common.CategoriesList;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryFormMapper categoryFormMapper;
    private final CategoriesList categoriesList;
    //カテゴリー登録画面
    @GetMapping("/admin/config/createCategory")
    public String CreateCategory(Model model){
//        登録されているカテゴリを取得。
        List<Categories> categories =categoriesList.categoryList();
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
                              Model model,
                              RedirectAttributes redirectAttributes){
        List<Categories> categoryList=categoriesList.categoryList();
        if (result.hasErrors()){
            if(categoryList.isEmpty()){
                model.addAttribute("error", "カテゴリーの登録はまだありません");
            }
            model.addAttribute("categories",categoryList);
            return "/admin/config/category";
        }
        try {
            Categories categories = categoryFormMapper.toEntity(categoryForm);
            int category=categoryService.save(categories);
            if(category!=1){
                model.addAttribute("categories", categoryList);
                model.addAttribute("error","問題が発生しました");
                return "/admin/config/category";
            }
        } catch (Exception e) {
            model.addAttribute("categories", categoryList);
            model.addAttribute("error","登録に失敗しました");
            return "/admin/config/category";
        }
        redirectAttributes.addFlashAttribute("success","カテゴリーの登録に成功しました");
        return "redirect:/admin/config/createCategory";
    }

//    カテゴリー編集画面
    @GetMapping("/admin/config/createCategory/edit/{id}")
    public String categoryEdit(@PathVariable("id")Long id,
                               Model model){
        Categories categories =categoryService.categoryId(id);
        CategoryForm categoryForm = new CategoryForm();
        categoryForm=categoryFormMapper.toForm(categories);
        System.out.println("categoryForm:"+categoryForm);
        model.addAttribute("categoryForm",categoryForm);
        return "/admin/config/categoryEdit";
    }
//    カテゴリー編集・更新
    @PostMapping("/admin/config/createCategory/edit/{id}")
    public String categroyEditForm(@PathVariable("id") Long id,
                                   @Validated @ModelAttribute CategoryForm categoryForm,
                                   BindingResult result,
                                   Model model
                                   ){

        if(result.hasErrors()){
            return "/admin/config/categoryEdit";
        }
        try {
            Categories categories = categoryFormMapper.toEntity(categoryForm);
            categories.setId(id);
            int newCate = categoryService.updateCategory(categories);
            if(newCate != 1){
                model.addAttribute("error","既に登録されている内容です");
                return "/admin/config/categoryEdit";
            }
        }catch (RuntimeException e){
            model.addAttribute("error" ,e.getMessage());
            return "/admin/config/categoryEdit";
        }
        return "redirect:/admin/config/createCategory";
    }
}
