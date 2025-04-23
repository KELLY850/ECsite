package com.kadaisite.ECsite.User.Entity;

import lombok.Data;
@Data
public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Integer status;
    private String tel;
    private java.sql.Timestamp createdAt;
    private java.sql.Timestamp updatedAt;
    private java.sql.Timestamp deletedAt;
    private Integer deleteFlg;
}

