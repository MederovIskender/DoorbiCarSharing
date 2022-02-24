package kg.megacom.carsharing.endpoints;

import kg.megacom.carsharing.dtos.base.BaseResponse;
import kg.megacom.carsharing.dtos.order.OrderCreateDto;
import kg.megacom.carsharing.dtos.order.RentCalculationRequestDto;

public interface OrderEndpoint {

    BaseResponse rentCalculation(RentCalculationRequestDto rentRequest);

    BaseResponse provide(OrderCreateDto orderCreateDto);
}