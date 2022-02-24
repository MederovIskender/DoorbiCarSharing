package kg.megacom.carsharing.mappers;

import kg.megacom.carsharing.dtos.user.UserResponse;
import kg.megacom.carsharing.models.entities.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class UserMapper {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public UserResponse mapToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();

        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setPhoneNumber(user.getPhoneNumber());
        userResponse.setAddress(user.getAddress());
        userResponse.setCity(user.getCity());
        userResponse.setDob(formatter.format(user.getDob()));

        return userResponse;
    }

    public User mapToUser(UserResponse userResponse) {
        User user = new User();

        user.setId(userResponse.getId());
        user.setName(userResponse.getName());
        user.setPhoneNumber(userResponse.getPhoneNumber());
        user.setAddress(userResponse.getAddress());
        user.setCity(userResponse.getCity());
        user.setDob(LocalDate.parse(userResponse.getDob()));

        return user;
    }
}