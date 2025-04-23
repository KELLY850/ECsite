package com.kadaisite.ECsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ECsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECsiteApplication.class, args);
//		パスワード取得用。
//		BCryptPasswordEncoder encord = new BCryptPasswordEncoder();
//		String encordepassword = encord.encode("12345");
//		System.out.println(encordepassword);
	}

}
