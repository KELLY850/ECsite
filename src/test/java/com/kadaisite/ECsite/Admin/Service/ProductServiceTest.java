package com.kadaisite.ECsite.Admin.Service;

import com.kadaisite.ECsite.Admin.Entity.Product_images;
import com.kadaisite.ECsite.Admin.Entity.Products;
import com.kadaisite.ECsite.Admin.Repository.ProductCategoryMapper;
import com.kadaisite.ECsite.Admin.Repository.ProductImageMapper;
import com.kadaisite.ECsite.Admin.Repository.ProductsMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

//Mockitoを使うので拡張。
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
//    サービスに依存クラスをモック化するのだ！！
    @Mock
    private ProductsMapper productsMapper;
    @Mock
    private ProductCategoryMapper productCategoryMapper;
    @Mock
    private ProductImageMapper productImageMapper;
//    テストするサービス名を指名指名〜〜〜！！テスト対象を生成するのだ！！
    @InjectMocks
    private ProductService productService;
//    JUnitが自動で作ってくれる一時フォルダ。ファイル保存のテストに最適テスト終了後、自動で削除されます
    @TempDir
    Path path;
//    テストクラスの各テストメソッドの実行前に毎回実行される。
    @BeforeEach
    void setUp(){
// productService インスタンスのuploadDir フィールドに@TempDirで生成された一時ディレクトリのパスを設定する
// 実行環境の本番設定（uploads/imagesなど）を使わずに、安全な一時ディレクトリで画像保存のテストをする
        ReflectionTestUtils.setField(productService,"uploadDir",path.toString());
    }

    @Test
    void ProductSerVice_saveTest()throws Exception {
        Products products =new Products();
        products.setId(1L);
        products.setName("テスト番号");
//   　Service層で商品登録の件数が１以上なら登録処理を行うことから、
    // モックの戻り値を定義（insert時に1を返し、product.getId()で1Lが返る）
        doReturn(1).when(productsMapper).insertProduct(products);
//        カテゴリIdのリスト
        List<Long> categoryIds=List.of(101L,102L);
//                アップロードファイルのダミーを作成
        MultipartFile mockFile =mock(MultipartFile.class);
        when(mockFile.getOriginalFilename()).thenReturn("image.jpg");
//        実際にファイルを保存しないように指定
        doNothing().when(mockFile).transferTo(any(File.class));
        List<MultipartFile>images =List.of(mockFile);
        //実行
        productService.save(products,categoryIds,images);
        // 各Mapperが正しく呼ばれたか確認
        verify(productsMapper).insertProduct(products);
        verify(productCategoryMapper).insertCategoryProduct(1L,101L);
        verify(productCategoryMapper).insertCategoryProduct(1L,102L);
        verify(productImageMapper).insertImages(any(Product_images.class));
    }

    @Test
    void productList_test(){
        Products products1 =new Products();
        products1.setId(1L);
        products1.setName("テスト");

        Products products2 =new Products();
        products2.setId(2L);
        products2.setName("テスト３");

        List<Products> productsList=List.of(products1,products2);
        doReturn(productsList).when(productsMapper).getAllProducts();
//        実行
        List<Products>TestList =productService.productList();
        // 検証
        assertThat(TestList).hasSize(2);
        assertThat(TestList.get(0).getName()).isEqualTo("テスト");
        assertThat(TestList.get(1).getName()).isEqualTo("テスト３");
//        呼び出し確認
        verify(productsMapper).getAllProducts();
    }
}
