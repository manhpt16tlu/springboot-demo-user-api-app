package com.ncc.mn.service.user;

import com.ncc.mn.dto.UserDTO;
import com.ncc.mn.entity.User;
import com.ncc.mn.mapper.UserMapper;
import com.ncc.mn.repository.UserRepository;
import com.ncc.mn.utils.GenerateString;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserMapper userMapper;
    @Mock
    private UserRepository userRepository;
    @Mock
    private GenerateString generateString;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void getUser() {
        User user = User.builder().userId("1851063512").email("mn@gmail.com").build();
        UserDTO userDTO = UserDTO.builder().userId("1851063512").email("mn@gmail.com").build();
        Mockito.when(userRepository.findByUserId("1851063512")).thenReturn(user);
        Mockito.when(userMapper.userToUserDto(user)).thenReturn(userDTO);
        Assertions.assertThat(userService.getUser("1851063512").getEmail()).isEqualTo("mn@gmail.com");
    }

}