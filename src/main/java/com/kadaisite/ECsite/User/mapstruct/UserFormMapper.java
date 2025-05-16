package com.kadaisite.ECsite.User.mapstruct;

import com.kadaisite.ECsite.User.Entity.User;
import com.kadaisite.ECsite.User.Form.NewAddForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserFormMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "deleteFlg", ignore = true)
    User toEntity(NewAddForm newAddForm);
}
