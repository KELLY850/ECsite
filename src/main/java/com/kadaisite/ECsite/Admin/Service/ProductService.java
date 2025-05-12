package com.kadaisite.ECsite.Admin.Service;

import com.kadaisite.ECsite.Admin.Entity.Product_images;
import com.kadaisite.ECsite.Admin.Entity.Products;
import com.kadaisite.ECsite.Admin.Repository.ProductCategoryMapper;
import com.kadaisite.ECsite.Admin.Repository.ProductImageMapper;
import com.kadaisite.ECsite.Admin.Repository.ProductsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

/*
* 商品登録、商品一覧、
* */
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductsMapper productsMapper;
    private final ProductCategoryMapper productCategoryMapper;
    private final ProductImageMapper productImageMapper;

    /*
    * products　登録する商品情報(productForm)
    * categoryIds　商品に紐づいたカテゴリーのID List型
    * images　商品に紐づいた商品画像の有無　List型
    * */
    @Value("${upload.image.path}")
    private String uploadDir;
    public void save(Products products, List<Long>categoryIds, List<MultipartFile> images){
        int product = productsMapper.insertProduct(products);
        System.out.println("登録結果：" + product);
        if(product != 1){
            throw new RuntimeException("登録に失敗しました");
        }
//        商品IDを取得。
        Long productId = products.getId();
        System.out.println("数字を出せ商品IDの！！"+productId);
        System.out.println("よこせ、categoryIdを！！"+categoryIds);
//        カテゴリーを商品とカテゴリテーブル（中間テーブル）に保存。
        if (categoryIds != null){
            for(Long categoryId :categoryIds){
                productCategoryMapper.insertCategoryProduct(productId,categoryId);
            }
        }
//        画像のパスを商品ごとに登録。
        if (images !=null){
            for(MultipartFile image: images){
                try{
//                    ランダムなIDを取得してそれを文字列に変えて、元画像の名前をつける。
                    String imageName = UUID.randomUUID().toString()+"_"+image.getOriginalFilename();
//                   登録するためのディレクトリルートを設置、そしてそのルートを新たに作成。自分のシステムから絶対パス。
                    Path url = Paths.get(System.getProperty("user.dir"), uploadDir);
//                    Path url = Paths.get("uploads/images");
                    if(Files.notExists(url)){
                        Files.createDirectories(url);
                    }

//                    上記作成したルートで画像を保存。
                    Path savePath = url.resolve(imageName);
//                  画像としてパスに登録（画像生成）
                    image.transferTo(savePath.toFile());
                    Product_images productImages = new Product_images();
                    productImages.setProductId(productId);
                    productImages.setImageUrl("/images/"+imageName);
//                    DBにパス含めてデータ登録
                    productImageMapper.insertImages(productImages);
                }catch (Exception e){
//                    本番に行く際は下記削除すること。
                    e.printStackTrace();
                    throw  new RuntimeException("画像の登録に失敗しました。");
                }
            }
        }
    }
//    商品一覧を表示
    public List<Products> productList(){
        return productsMapper.getAllProducts();
    }
}
