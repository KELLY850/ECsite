package com.kadaisite.ECsite.Admin.mapstruct;

import com.kadaisite.ECsite.Admin.Entity.Admin_users;
import com.kadaisite.ECsite.Admin.Form.UpdateForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdminFormMapper {
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "deleteFlg", ignore = true)
    Admin_users toEntity(UpdateForm updateForm);

    UpdateForm toForm(Admin_users admin_users);
}
