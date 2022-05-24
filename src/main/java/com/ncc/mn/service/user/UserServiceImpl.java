package com.ncc.mn.service.user;

import com.ncc.mn.dto.UserDTO;
import com.ncc.mn.entity.User;
import com.ncc.mn.exception.UserServiceException;
import com.ncc.mn.mapper.UserMapper;
import com.ncc.mn.model.ErrorMessage;
import com.ncc.mn.repository.UserRepository;
import com.ncc.mn.utils.GenerateString;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GenerateString generateString;

//    private UserMapper userMapper;
//    private UserRepository userRepository;
//    private GenerateString generateString;
//
//    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository, GenerateString generateString) {
//        this.userMapper = userMapper;
//        this.userRepository = userRepository;
//        this.generateString = generateString;
//    }
    //    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User exist = userRepository.findByEmail(userDTO.getEmail());
        if (Objects.nonNull(exist)) throw new UserServiceException(ErrorMessage.RECORD_ALREADY_EXIST.getMessage());
        User user = userMapper.userDtoToUser(userDTO);
        user.setEncpyptPassword(userDTO.getPassword());
        user.setUserId(generateString.generate(48, 57, 4, "185106"));
        User createdUser = userRepository.save(user);
        UserDTO returnValue = userMapper.userToUserDto(createdUser);
        return returnValue;
    }

    @Override
//    @Cacheable(cacheNames = "allUserCache",condition = "#limit < 5") //cacheNames  == value
    public Page<UserDTO> getAllUser(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC, "firstName"));
        Page<User> pageUser = userRepository.findAll(pageable);
        Page<UserDTO> returnValue = pageUser.map(userMapper::userToUserDto);
        return returnValue;
    }

    @Override
    public UserDTO getUser(String userId) {
        User user = userRepository.findByUserId(userId);
        if (user == null) throw new UserServiceException(userId + " " + ErrorMessage.RECORD_NOT_FOUND.getMessage());
        UserDTO returnValue = userMapper.userToUserDto(user);
        return returnValue;
    }

    @Override
    public UserDTO updateUser(String userId, UserDTO userDTO) {
        User exist = userRepository.findByUserId(userId);
        if (exist == null) throw new UserServiceException(userId + " " + ErrorMessage.RECORD_NOT_FOUND.getMessage());
        //check null vs empty
        if (!StringUtils.isEmpty(userDTO.getFirstName())) exist.setFirstName(userDTO.getFirstName());
        if (!StringUtils.isEmpty(userDTO.getLastName())) exist.setLastName(userDTO.getLastName());
        if (!StringUtils.isEmpty(userDTO.getPassword())) exist.setEncpyptPassword(userDTO.getPassword());
        User updatedUser = userRepository.save(exist);
        UserDTO returnValue = userMapper.userToUserDto(updatedUser);
        return returnValue;
    }

    @Override
    public void deleteUser(String userId) {
        User exist = userRepository.findByUserId(userId);
        if (exist == null) throw new UserServiceException(userId + " " + ErrorMessage.RECORD_NOT_FOUND.getMessage());
        userRepository.delete(exist);
    }
}
