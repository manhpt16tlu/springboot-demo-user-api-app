package com.ncc.mn.service.user;


import com.ncc.mn.dto.UserDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService{
    UserDTO createUser(UserDTO userDTO);
    Page<UserDTO> getAllUser(int page, int limit);
    UserDTO getUser(String userId);
    UserDTO updateUser(String userId, UserDTO userDTO);
    void deleteUser(String userId);
}
