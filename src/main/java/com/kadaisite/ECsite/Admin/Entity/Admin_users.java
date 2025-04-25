package com.kadaisite.ECsite.Admin.Entity;

import lombok.Data;

@Data
public class Admin_users {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;
    private java.sql.Timestamp createdAt;
    private java.sql.Timestamp updatedAt;
    private java.sql.Timestamp deletedAt;
    private Integer deleteFlg;
}
