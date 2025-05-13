package com.kadaisite.ECsite.Admin.Controller;

import com.kadaisite.ECsite.Admin.Entity.Categories;
import com.kadaisite.ECsite.Admin.Entity.Products;
import com.kadaisite.ECsite.Admin.Form.ProductForm;
import com.kadaisite.ECsite.Admin.Repository.CategoriesMapper;
import com.kadaisite.ECsite.Admin.Service.ProductService;
import com.kadaisite.ECsite.Admin.mapstruct.ProductFormMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {
//    商品追加のロジック
    private final ProductService productService;
    private final ProductFormMapper productFormMapper;
    private final CategoriesMapper categoriesMapper;

/*
* 新規商品追加画面
* ProductForm productForm登録フォーム
* */
    @GetMapping("/admin/product/create")
    public String createProductForm(Model model) {
        List<Categories> categories = categoriesMapper.getAllCategories();
        model.addAttribute("categoryList",categories);
        model.addAttribute("productForm", new ProductForm()); // フォームを初期化して渡す
        return "/admin/product/productCreate";
    }
    /*
    * 入力フォームからの商品登録
    * productForm　商品登録フォーム
    * images 登録された商品画像
    * */
    @PostMapping("/admin/product/create")
    public String create(@Validated @ModelAttribute ProductForm productForm,
//                         @RequestParam(value = "categoryIds",required = false) List<Long> categoryIds,結局、Paramで使用せず下記で調整。
                         @RequestParam("images") List<MultipartFile> images,
                         BindingResult result,
                         Model model){
        // フォームデータの中身を確認
        System.out.println("Product Form: " + productForm);
        if (result.hasErrors()){
            return "/admin/product/productCreate";
        }
        //        入力フォームで選択したカテゴリを取得
        List<Long> categoryIds = productForm.getCategoryIds();
//        if(categoryIds == null){　Paramで取得していたらここ記述してNULLパターンを登録、空の配列。
//            categoryIds=new ArrayList<>();
//        }
        //        もしも画像が空なら空の配列を新たに作成。
        if(images.isEmpty()){
            images = new ArrayList<>();
        }
        try{
            //商品エンティティの型に合わせて、商品の受け取りフォームをエンティティに結びつける。
            Products products = productFormMapper.toEntity(productForm);
            System.out.println("登録対象のエンティティ：" + products);
            //サービス層を呼び出して、登録処理にもっていく。
            productService.save(products,categoryIds,images);

        } catch (Exception e) {
//            e.printStackTrace();
            model.addAttribute("error","商品の登録に失敗しました。");
            return "/admin/product/productCreate";
        }
        return "redirect:/admin/product";
    }
    /*
    * 商品一覧
    * Products　商品エンティティ
    * */
    @GetMapping("/admin/product")
    public String product(Model model){
       List<Products> products = productService.productList();
       if(products == null){
           model.addAttribute("error","商品登録がまだありません。");
       }
        model.addAttribute("products",products);
        return "/admin/product/product";
    }
}
