package com.ncc.mn.service.user;

import com.ncc.mn.dto.UserDTO;
import com.ncc.mn.entity.User;
import com.ncc.mn.exception.UserServiceException;
import com.ncc.mn.mapper.UserMapper;
import com.ncc.mn.repository.UserRepository;
import com.ncc.mn.utils.GenerateString;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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
        User user = User.builder()
                .id(1)
                .userId("1851063512")
                .email("mn@gmail.com")
                .firstName("manh")
                .lastName("nguyen")
                .encpyptPassword("dfasdf6d6sf")
                .build();
        UserDTO userDTO = UserDTO.builder()
                .userId("1851063512")
                .email("mn@gmail.com")
                .build();
        Mockito.when(userRepository.findByUserId("1851063512")).thenReturn(user);
        Mockito.when(userMapper.userToUserDto(user)).thenReturn(userDTO);
        Assertions.assertThat(userService.getUser("1851063512").getEmail()).isEqualTo("mn@gmail.com");
    }

    @Test
    void getUser_exception() {
        Mockito.when(userRepository.findByUserId("1851063512")).thenReturn(null);
        org.junit.jupiter.api.Assertions.assertThrows(NullPointerException.class,()->{
           userService.getUser("1851063512");
        });


    }

    @Test
    void createUser(){
        User user = User.builder()
                .email("mn@gmail.com")
                .firstName("manh")
                .lastName("nguyen")
                .build();
        Mockito.when(userRepository.findByEmail("mn@gmail.com")).thenReturn(null);
        Mockito.when(userMapper.userDtoToUser(ArgumentMatchers.any(UserDTO.class))).thenReturn(user);
        Mockito.when(generateString.generate(48, 57, 4, "185106")).thenReturn("abc");
        String userId = generateString.generate(48,57,4,"185106");
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Mockito.when(userMapper.userToUserDto(user)).thenReturn(UserDTO.builder().email(user.getEmail()).userId(userId).build());
        Assertions.assertThat(userService.createUser(UserDTO.builder().email("mn@gmail.com").build()).getEmail()).isEqualTo("mn@gmail.com");
        Assertions.assertThat(userService.createUser(UserDTO.builder().email("mn@gmail.com").build()).getUserId()).isEqualTo("abc");
    }
}