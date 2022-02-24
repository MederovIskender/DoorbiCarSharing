package kg.megacom.carsharing.endpoints.impl;

import kg.megacom.carsharing.dtos.base.BaseResponse;
import kg.megacom.carsharing.dtos.user.UserCreateDto;
import kg.megacom.carsharing.dtos.user.UserResponse;
import kg.megacom.carsharing.endpoints.UserEndpoint;
import kg.megacom.carsharing.enums.RestStatus;
import kg.megacom.carsharing.mappers.BaseMapper;
import kg.megacom.carsharing.mappers.UserMapper;
import kg.megacom.carsharing.services.UserService;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.regex.Pattern;

@Service
public class UserEndpointImpl implements UserEndpoint {

    private final UserService userService;

    private final BaseMapper baseMapper;

    private final UserMapper userMapper;

    public UserEndpointImpl(UserService userService, BaseMapper baseMapper, UserMapper userMapper) {
        this.userService = userService;
        this.baseMapper = baseMapper;
        this.userMapper = userMapper;
    }

    @Override
    public BaseResponse saveUser(UserCreateDto userCreateDto) {
        try {

            String phoneNumberValidateRegEx = "^(996)?\\s\\d{3}-\\d{3}-\\d{3}";
            // if (!Pattern.matches("\\d{3}\\s\\d{3}-\\d{3}-\\d{3}", userCreateDto.getPhoneNumber()))

            if (!Pattern.matches(phoneNumberValidateRegEx, userCreateDto.getPhoneNumber())) {

                return baseMapper.mapToBaseResponse(
                        "Вы ввели некорректный номер телефона!",
                        null,
                        RestStatus.FAIL
                );
            }

            if (userService.existsByPhoneNumber(userCreateDto.getPhoneNumber())) {

                return baseMapper.mapToBaseResponse(
                        "Клиент с таким номером телефона уже существует",
                        null,
                        RestStatus.FAIL
                );
            }

            if (!Pattern.matches("^\\d{4}-\\d{2}-\\d{2}", userCreateDto.getDob())) {

                return baseMapper.mapToBaseResponse(
                        "Введите корректную дату",
                        null,
                        RestStatus.FAIL
                );
            }

            if (userCreateDto.getName().isBlank() || Objects.isNull(userCreateDto.getName())
                    || userCreateDto.getPhoneNumber().isBlank() || Objects.isNull(userCreateDto.getPhoneNumber())
                    || userCreateDto.getAddress().isBlank() || Objects.isNull(userCreateDto.getAddress())
                    || userCreateDto.getCity().isBlank() || Objects.isNull(userCreateDto.getCity())
                    || userCreateDto.getDob().isBlank() || Objects.isNull(userCreateDto.getDob())) {

                return baseMapper.mapToBaseResponse(
                        "Заполните все данные!",
                        null,
                        RestStatus.FAIL
                );
            }

            UserResponse userResponse = userMapper.mapToUserResponse(userService.save(userCreateDto));

            return baseMapper.mapToBaseResponse(
                    "Клиент успешно создан!",
                    userResponse,
                    RestStatus.SUCCESS
            );

        } catch (Exception e) {

            return baseMapper.mapToBaseResponse(
                    "Сохранить не удалось произошла ошибка системы!",
                    null,
                    RestStatus.FAIL
            );
        }
    }
}