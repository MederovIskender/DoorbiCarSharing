package kg.megacom.carsharing.endpoints;

import kg.megacom.carsharing.dtos.base.BaseResponse;
import kg.megacom.carsharing.dtos.user.UserCreateDto;

public interface UserEndpoint {

    BaseResponse saveUser(UserCreateDto userCreateDto);
}
