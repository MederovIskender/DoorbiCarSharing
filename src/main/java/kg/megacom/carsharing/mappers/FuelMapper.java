package kg.megacom.carsharing.mappers;

import kg.megacom.carsharing.dtos.fuel.FuelResponse;
import kg.megacom.carsharing.models.entities.Fuel;
import org.springframework.stereotype.Component;

@Component
public class FuelMapper {
    public FuelResponse mapToFuelResponse(Fuel fuel) {
        FuelResponse fuelResponse = new FuelResponse();
        fuelResponse.setId(fuel.getId());
        fuelResponse.setPrice(fuel.getPrice());
        fuelResponse.setFuelType(fuel.getFuelType());
        fuelResponse.setActive(fuel.isActive());
        return fuelResponse;
    }

}
