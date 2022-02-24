package kg.megacom.carsharing.endpoints.impl;

import kg.megacom.carsharing.dtos.base.BaseResponse;
import kg.megacom.carsharing.dtos.car.CarCreateDto;
import kg.megacom.carsharing.dtos.car.CarResponse;
import kg.megacom.carsharing.endpoints.CarEndpoint;
import kg.megacom.carsharing.enums.CarStatus;
import kg.megacom.carsharing.enums.CarType;
import kg.megacom.carsharing.enums.RestStatus;
import kg.megacom.carsharing.mappers.BaseMapper;
import kg.megacom.carsharing.mappers.CarMapper;
import kg.megacom.carsharing.services.CarService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CarEndpointImpl implements CarEndpoint {

    private final CarService carService;

    private final BaseMapper baseMapper;

    private final CarMapper carMapper;

    public CarEndpointImpl(CarService carService, BaseMapper baseMapper, CarMapper carMapper) {
        this.carService = carService;
        this.baseMapper = baseMapper;
        this.carMapper = carMapper;
    }
    @Override
    public BaseResponse save(CarCreateDto carCreateDto) {
        try {

            if (Objects.isNull(carCreateDto)
                    || Objects.isNull(carCreateDto.getVolume())
                    || Objects.isNull(carCreateDto.getPricePerDay())
                    || Objects.isNull(carCreateDto.getFuelPerKm())
                    || carCreateDto.getManufacturer().isBlank()
                    || carCreateDto.getModel().isBlank()
                    || carCreateDto.getColor().isBlank()
                    || carCreateDto.getCarStatus().toString().isBlank()
                    || carCreateDto.getCarType().toString().isBlank()
                    || carCreateDto.getFuelType().toString().isBlank()) {

                return baseMapper.mapToBaseResponse(
                        "Вы ввели не все данные!",
                        null,
                        RestStatus.FAIL
                );
            }

            CarResponse carResponse = carMapper.mapToCarResponse(carService.save(carCreateDto));

            return baseMapper.mapToBaseResponse(
                    "Машина успешно сохранена!",
                    carResponse,
                    RestStatus.SUCCESS
            );
        } catch (Exception e) {

            return baseMapper.mapToBaseResponse(
                    "Произошла ошибка на стороне сервера",
                    null,
                    RestStatus.FAIL
            );
        }
    }

    @Override
    public BaseResponse getByTypeOrStatus(CarStatus carStatus, CarType carType) {
        try {
            List<CarResponse> carResponseList = carMapper.mapToCarResponseList(
                    carService.getByStatusOrType(carStatus, carType)
            );

            return baseMapper.mapToBaseResponse(
                    "Список машин по вашим критериям!",
                    carResponseList,
                    RestStatus.SUCCESS
            );
        } catch (Exception e) {

            return baseMapper.mapToBaseResponse(
                    "Произошла ошибка. Возможно вы ввели некорректные данные!",
                    null,
                    RestStatus.FAIL
            );
        }
    }
}




