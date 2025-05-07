package com.kadaisite.ECsite.Admin.Entity;

import lombok.Data;

@Data
public class Categories {
    private Long id;
    private String name;
    private java.sql.Timestamp createdAt;
    private java.sql.Timestamp updatedAt;
    private java.sql.Timestamp deletedAt;
    private Integer deleteFlg;
}
