package com.citynow.smallerp.mapper;

import com.citynow.smallerp.entity.User;
import com.citynow.smallerp.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    @Mapping(source = "username", target = "username")
    UserModel userToUserModel(User user);


    @Mapping(source = "username", target = "username")
    User userModelToUser(UserModel user);
}
