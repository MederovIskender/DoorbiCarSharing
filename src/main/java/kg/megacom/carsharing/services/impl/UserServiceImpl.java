package kg.megacom.carsharing.services.impl;

import kg.megacom.carsharing.dtos.user.UserCreateDto;
import kg.megacom.carsharing.models.entities.User;
import kg.megacom.carsharing.repositories.UserRepository;
import kg.megacom.carsharing.services.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean existsByPhoneNumber(String email) {
        return userRepository.existsByPhoneNumber(email);
    }

    @Override
    public User save(UserCreateDto userCreateDto) {

        User user = new User();

        user.setName(userCreateDto.getName());
        user.setPhoneNumber(userCreateDto.getPhoneNumber());
        user.setAddress(userCreateDto.getAddress());
        user.setCity(userCreateDto.getCity());
        user.setDob(LocalDate.parse(userCreateDto.getDob()));

        return userRepository.save(user);
    }

    @Override
    public User findByPhoneNumber(String userPhoneNumber) {
        return userRepository.getByPhoneNumber(userPhoneNumber);
    }
}