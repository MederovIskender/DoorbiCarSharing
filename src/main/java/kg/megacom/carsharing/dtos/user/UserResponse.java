package kg.megacom.carsharing.dtos.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String name;
    private String phoneNumber;
    private String address;
    private String city;
    @JsonProperty(namespace = "date_of_birth")
    private String dob;
}