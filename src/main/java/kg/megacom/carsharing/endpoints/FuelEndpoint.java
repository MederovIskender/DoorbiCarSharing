package kg.megacom.carsharing.endpoints;

import kg.megacom.carsharing.dtos.base.BaseResponse;
import kg.megacom.carsharing.dtos.fuel.FuelCreateDto;

public interface FuelEndpoint {
    BaseResponse save(FuelCreateDto fuelCreateDto);
}
