package kg.megacom.carsharing.endpoints.impl;

import kg.megacom.carsharing.dtos.base.BaseResponse;
import kg.megacom.carsharing.dtos.fuel.FuelCreateDto;
import kg.megacom.carsharing.dtos.fuel.FuelResponse;
import kg.megacom.carsharing.endpoints.FuelEndpoint;
import kg.megacom.carsharing.enums.RestStatus;
import kg.megacom.carsharing.mappers.BaseMapper;
import kg.megacom.carsharing.mappers.FuelMapper;
import kg.megacom.carsharing.services.FuelService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FuelEndpointImpl implements FuelEndpoint {

    private final FuelService fuelService;

    private final BaseMapper baseMapper;

    private final FuelMapper fuelMapper;

    public FuelEndpointImpl(FuelService fuelService, BaseMapper baseMapper, FuelMapper fuelMapper) {
        this.fuelService = fuelService;
        this.baseMapper = baseMapper;
        this.fuelMapper = fuelMapper;
    }

    @Override
    public BaseResponse save(FuelCreateDto fuelCreateDto) {

        try {
            if (Objects.isNull(fuelCreateDto)
                    || Objects.isNull(fuelCreateDto.getPrice())
                    || Objects.isNull(fuelCreateDto.getFuelType())) {

                return baseMapper.mapToBaseResponse(
                        "Вы не передали цену или тип топлива!",
                        null,
                        RestStatus.FAIL
                );
            }

            FuelResponse fuelResponse = fuelMapper.mapToFuelResponse(fuelService.save(fuelCreateDto));

            return baseMapper.mapToBaseResponse(
                    "Тип топлива успешно сохранен!",
                    fuelResponse,
                    RestStatus.SUCCESS
            );
        } catch (Exception e) {

            return baseMapper.mapToBaseResponse(
                    "Произошла ошибка. Сохранить не удалось.",
                    null,
                    RestStatus.FAIL
            );
        }
    }
}