package com.citynow.smallerp.mapper;

import com.citynow.smallerp.entity.Role;
import com.citynow.smallerp.model.RoleModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper( RoleMapper.class );

    @Mapping(source = "name", target = "name")
    RoleModel roleToRoleModel(Role role);

    @Mapping(source = "name", target = "name")
    Role roleModelToRole(RoleModel role);
}
