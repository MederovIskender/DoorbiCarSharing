package kg.megacom.carsharing.services;

import kg.megacom.carsharing.dtos.user.UserCreateDto;
import kg.megacom.carsharing.models.entities.User;

public interface UserService {
    boolean existsByPhoneNumber(String email);

    User save(UserCreateDto userCreateDto);

    User findByPhoneNumber(String userPhoneNumber);
}
