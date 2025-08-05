package ru.yakovlev.cardmanagesystem.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yakovlev.cardmanagesystem.model.User;
import ru.yakovlev.cardmanagesystem.model.dto.UserDTO;
import ru.yakovlev.cardmanagesystem.repository.UserRepository;
import ru.yakovlev.cardmanagesystem.service.UserService;
import ru.yakovlev.cardmanagesystem.util.UserMapper;
import ru.yakovlev.cardmanagesystem.util.exception.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO getUserById(Long id) throws UserNotFoundException {
        User userById = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
        return UserMapper.toUserDTO(userById);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(UserMapper.toUserDTO(user));
        }
        return userDTOs;
    }

    @Override
    @Transactional
    public UserDTO addUser(UserDTO userDTO) {
        User saveUser = userRepository.save(UserMapper.toUser(userDTO));
        return UserMapper.toUserDTO(saveUser);
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) throws UserNotFoundException {
        User userById = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
        userRepository.deleteById(userById.getId());
    }

    @Override
    @Transactional
    public UserDTO updateUser(Long id, UserDTO userDTO) throws UserNotFoundException {
        User userById = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
        userDTO.setId(userById.getId());
        userRepository.save(UserMapper.toUser(userDTO));
        return userDTO;
    }
}
