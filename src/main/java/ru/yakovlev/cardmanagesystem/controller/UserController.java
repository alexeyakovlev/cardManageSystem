package ru.yakovlev.cardmanagesystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yakovlev.cardmanagesystem.model.dto.UserDTO;
import ru.yakovlev.cardmanagesystem.service.UserService;
import ru.yakovlev.cardmanagesystem.util.exception.UserNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUserById(@PathVariable Long id) throws UserNotFoundException {
        return userService.getUserById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return userService.addUser(userDTO);
    }

    @PostMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserById(@PathVariable Long id) throws UserNotFoundException {
        userService.deleteUserById(id);
    }

    @PostMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO updateUserById(@PathVariable Long id, @RequestBody UserDTO userDTO) throws UserNotFoundException {
        userService.updateUser(id, userDTO);
        return userService.getUserById(id);
    }
}
