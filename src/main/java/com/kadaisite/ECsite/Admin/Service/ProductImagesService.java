package com.kadaisite.ECsite.Admin.Service;

import com.kadaisite.ECsite.Admin.Entity.Product_images;
import com.kadaisite.ECsite.Admin.Repository.ProductImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductImagesService {
    @Value("${upload.image.path}")
    private String uploadDir;

    private final ProductImageMapper productImageMapper;
    public void saveImage(Long productId, List<MultipartFile>images){
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
}
