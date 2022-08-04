package com.greenrent.dto.mapper;


import com.greenrent.domain.User;
import com.greenrent.dto.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO userToUserDTO(User user);
    List<UserDTO> map(List<User> user);

}
