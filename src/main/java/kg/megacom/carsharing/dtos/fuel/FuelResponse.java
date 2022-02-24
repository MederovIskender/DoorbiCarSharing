package kg.megacom.carsharing.dtos.fuel;

import kg.megacom.carsharing.enums.FuelType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FuelResponse {

    private Long id;
    private Double price;
    private FuelType fuelType;
    private boolean active;
}