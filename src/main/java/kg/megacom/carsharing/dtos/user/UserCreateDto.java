package kg.megacom.carsharing.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDto {

    private String name;
    private String phoneNumber;
    private String address;
    private String city;
    private String dob;
}
