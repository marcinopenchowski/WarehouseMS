package com.wms.mapper;

import com.wms.dto.CredentialsDto;
import com.wms.dto.UserDto;
import com.wms.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<User> {
    String SEPARATOR = ",";

    @Mapping(target = "authRoles", qualifiedByName = "authRoleToAuthRole")
    UserDto toUserDto(User user);

    @Mapping(target = "password", ignore = true)
    User registerUser(CredentialsDto credentialsDto);

    @Named("authRoleToAuthRole")
    default List<String> authRoleToAuthRole(String authRoles) {
        return Optional.ofNullable(authRoles)
                .map(authRole -> Arrays.stream(authRole.split(SEPARATOR))
                        .map(String::trim)
                        .toList())
                .orElse(Collections.emptyList());
    }
}
