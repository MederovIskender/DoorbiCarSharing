package kg.megacom.carsharing.dtos.order;

import kg.megacom.carsharing.dtos.car.CarResponse;
import kg.megacom.carsharing.dtos.user.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private Long id;
    private UserResponse user;
    private CarResponse car;
    private String startDate;
    private String endDate;
    private Double sum;
    private Double km;
}