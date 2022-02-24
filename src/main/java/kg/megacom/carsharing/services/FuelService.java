package kg.megacom.carsharing.services;

import kg.megacom.carsharing.dtos.fuel.FuelCreateDto;
import kg.megacom.carsharing.enums.FuelType;
import kg.megacom.carsharing.models.entities.Fuel;

public interface FuelService {
    Fuel save(FuelCreateDto fuelCreateDto);

    double findFuelPriceByType(FuelType fuelType);
}
