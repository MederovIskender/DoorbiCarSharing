package kg.megacom.carsharing.controllers;

import kg.megacom.carsharing.dtos.base.BaseResponse;
import kg.megacom.carsharing.dtos.user.UserCreateDto;
import kg.megacom.carsharing.endpoints.UserEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserEndpoint userEndpoint;

    public UserController(UserEndpoint userEndpoint) {
        this.userEndpoint = userEndpoint;
    }

    @PostMapping("/save")
    public ResponseEntity<BaseResponse> save(@RequestBody UserCreateDto userCreateDto) {
        return ResponseEntity.ok().body(userEndpoint.saveUser(userCreateDto));
    }
}
