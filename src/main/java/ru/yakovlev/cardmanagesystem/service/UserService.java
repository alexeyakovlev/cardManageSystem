package ru.yakovlev.cardmanagesystem.service;

import ru.yakovlev.cardmanagesystem.model.dto.UserDTO;
import ru.yakovlev.cardmanagesystem.util.exception.UserNotFoundException;

import java.util.List;

public interface UserService {

    public UserDTO getUserById(Long id) throws UserNotFoundException;

    public List<UserDTO> getAllUsers();

    public UserDTO addUser(UserDTO userDTO);

    public void deleteUserById(Long id) throws UserNotFoundException;

    public UserDTO updateUser(Long id, UserDTO userDTO) throws UserNotFoundException;

}
