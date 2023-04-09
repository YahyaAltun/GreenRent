package com.greenrent.dto.mapper;


import com.greenrent.domain.User;
import com.greenrent.dto.UserDTO;
import com.greenrent.dto.request.AdminUserUpdateRequest;
import com.greenrent.dto.request.RegisterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO userToUserDTO(User user);

    List<UserDTO> map(List<User> user);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "roles",ignore = true)
    User adminUserUpdateRequestToUser(AdminUserUpdateRequest request);

}
