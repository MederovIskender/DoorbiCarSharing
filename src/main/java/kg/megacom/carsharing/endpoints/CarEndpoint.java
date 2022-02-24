package kg.megacom.carsharing.endpoints;

import kg.megacom.carsharing.dtos.base.BaseResponse;
import kg.megacom.carsharing.dtos.car.CarCreateDto;
import kg.megacom.carsharing.enums.CarStatus;
import kg.megacom.carsharing.enums.CarType;

public interface CarEndpoint {
    BaseResponse save(CarCreateDto carCreateDto);

    BaseResponse getByTypeOrStatus(CarStatus carStatus, CarType carType);
}
