package com.kadaisite.ECsite.Admin.Controller;

import com.kadaisite.ECsite.Admin.Entity.Products;
import com.kadaisite.ECsite.Admin.Form.ProductForm;
import com.kadaisite.ECsite.Admin.Repository.ProductsMapper;
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

import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {
//    商品追加のロジック
    private final ProductService productService;
    private final ProductFormMapper productFormMapper;

/*
* 新規商品追加画面
* ProductForm productForm登録フォーム
* */
    @GetMapping("/admin/product/create")
    public String createProductForm(Model model) {
        model.addAttribute("productForm", new ProductForm()); // フォームを初期化して渡す
        return "/admin/product/productCreate";
    }
    @PostMapping("/admin/product/create")
    public String create(@Validated @ModelAttribute ProductForm productForm,
                         BindingResult result,
                         Model model){
        // フォームデータの中身を確認
        System.out.println("Product Form: " + productForm);
        if (result.hasErrors()){
            return "/admin/product/productCreate";
        }
        try{
            //商品エンティティの型に合わせて、商品の受け取りフォームをエンティティに結びつける。
            Products products = productFormMapper.toEntity(productForm);
            System.out.println("登録対象のエンティティ：" + products);
            //サービス層を呼び出して、登録処理にもっていく。
            productService.save(products);

        } catch (Exception e) {
//            e.printStackTrace();
            model.addAttribute("error","商品の登録に失敗しました。");
            return "/admin/product/productCreate";
        }
        return "redirect:/admin/product";
    }
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
