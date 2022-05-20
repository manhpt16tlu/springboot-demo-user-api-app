package com.ncc.mn.mapper;

import com.ncc.mn.dto.UserDTO;
import com.ncc.mn.entity.User;
import com.ncc.mn.model.UserRequest;
import com.ncc.mn.model.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
//    @Mapping(target = "",source = "")//default value khi map
    UserDTO userRequestToUserDTO(UserRequest user);

    UserResponse userDtoToUserResponse(UserDTO user);

    User userDtoToUser(UserDTO user);

    UserDTO userToUserDto(User user);
}
