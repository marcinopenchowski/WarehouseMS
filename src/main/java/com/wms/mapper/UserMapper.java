package com.wms.mapper;

import com.wms.dto.CredentialsDto;
import com.wms.dto.UserDto;
import com.wms.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<User>{

    UserDto toUserDto(User user);

    @Mapping(target = "password", ignore = true)
    User registerUser(CredentialsDto credentialsDto);
}
