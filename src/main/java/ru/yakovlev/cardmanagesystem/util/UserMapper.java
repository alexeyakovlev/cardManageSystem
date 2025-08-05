package ru.yakovlev.cardmanagesystem.util;

import ru.yakovlev.cardmanagesystem.model.User;
import ru.yakovlev.cardmanagesystem.model.dto.UserDTO;

public class UserMapper {
    public static UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        return userDTO;
    }

    public static User toUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        return user;
    }
}
