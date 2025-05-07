package com.kadaisite.ECsite.Admin.mapstruct;



import com.kadaisite.ECsite.Admin.Entity.Products;
import com.kadaisite.ECsite.Admin.Form.ProductForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

//商品用マッパーストラクト
@Mapper(componentModel = "spring")//Spring Boot の DI（依存性注入）対象にするための指定。
// これを指定すると、@Autowired や @RequiredArgsConstructor などで注入できるようになります。
public interface ProductFormMapper {
//    エンティティと比べてないカラムがあるよというエラーを無視してねという記述。
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "deleteFlg", ignore = true)
    @Mapping(target = "categoriesList",ignore = true)
//    入力フォームとエンティティを合致させる。
    Products toEntity(ProductForm form);
}
