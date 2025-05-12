package com.kadaisite.ECsite.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

//Spring MVC の設定をカスタマイズ
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        Path url = Paths.get("uploads/images");
//Path オブジェクトをファイルとして扱い、絶対パス（フルパス）に変換
        String path =url.toFile().getAbsolutePath();
        registry.addResourceHandler("/images/**").addResourceLocations("file:" + path + "/");
    }
}
