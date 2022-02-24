package kg.megacom.carsharing.endpoints.impl;

import kg.megacom.carsharing.dtos.base.BaseResponse;
import kg.megacom.carsharing.dtos.car.CarResponse;
import kg.megacom.carsharing.dtos.order.OrderCreateDto;
import kg.megacom.carsharing.dtos.order.OrderResponse;
import kg.megacom.carsharing.dtos.order.RentCalculationRequestDto;
import kg.megacom.carsharing.dtos.order.RentCalculationResponseDto;
import kg.megacom.carsharing.dtos.user.UserResponse;
import kg.megacom.carsharing.endpoints.OrderEndpoint;
import kg.megacom.carsharing.enums.FuelType;
import kg.megacom.carsharing.enums.RestStatus;
import kg.megacom.carsharing.mappers.BaseMapper;
import kg.megacom.carsharing.mappers.CarMapper;
import kg.megacom.carsharing.mappers.OrderMapper;
import kg.megacom.carsharing.mappers.UserMapper;
import kg.megacom.carsharing.services.CarService;
import kg.megacom.carsharing.services.FuelService;
import kg.megacom.carsharing.services.OrderService;
import kg.megacom.carsharing.services.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Pattern;

@Service
public class OrderEndpointImpl implements OrderEndpoint {

    private final OrderService orderService;

    private final CarService carService;

    private final FuelService fuelService;

    private final UserService userService;

    private final BaseMapper baseMapper;

    private final CarMapper carMapper;

    private final UserMapper userMapper;

    private final OrderMapper orderMapper;

    public OrderEndpointImpl(OrderService orderService, CarService carService, FuelService fuelService, UserService userService, BaseMapper baseMapper, CarMapper carMapper, UserMapper userMapper, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.carService = carService;
        this.fuelService = fuelService;
        this.userService = userService;
        this.baseMapper = baseMapper;
        this.carMapper = carMapper;
        this.userMapper = userMapper;
        this.orderMapper = orderMapper;
    }

    @Override
    public BaseResponse rentCalculation(RentCalculationRequestDto rentRequest) {

        try {
            CarResponse carResponse = carMapper.mapToCarResponse(carService.getById(rentRequest.getCarId()));

            if (Objects.isNull(carResponse)) {

                return baseMapper.mapToBaseResponse(
                        "Машина с такой ID нет в базе!",
                        null,
                        RestStatus.FAIL
                );
            }

            if (Objects.isNull(rentRequest.getStartDate())
                    || Objects.isNull(rentRequest.getEndDate())
                    || Objects.isNull(rentRequest.getKm())) {

                return baseMapper.mapToBaseResponse(
                        "Вы не заполнили все поля!",
                        null,
                        RestStatus.FAIL
                );
            }

            String validateDate = "^\\d{4}-\\d{2}-\\d{2}";

            if (!Pattern.matches(validateDate, rentRequest.getStartDate())
                    || !Pattern.matches(validateDate, rentRequest.getEndDate())) {

                return baseMapper.mapToBaseResponse(
                        "Вы ввели некорректную дату!",
                        null,
                        RestStatus.FAIL
                );
            }

            int days = calculateDays(rentRequest.getStartDate(), rentRequest.getEndDate());

            double totalSum = calculateRentSum(
                    days,
                    rentRequest.getKm(),
                    carResponse.getPricePerDay(),
                    carResponse.getFuelPerKm(),
                    carResponse.getFuelType()
            );

            RentCalculationResponseDto rentResponseDto = new RentCalculationResponseDto();

            rentResponseDto.setCar(carResponse);
            rentResponseDto.setStartDate(rentRequest.getStartDate());
            rentResponseDto.setEndDate(rentRequest.getEndDate());
            rentResponseDto.setSum(totalSum);
            rentResponseDto.setKm(rentRequest.getKm());

            return baseMapper.mapToBaseResponse(
                    "Стоимость аредны!",
                    rentResponseDto,
                    RestStatus.SUCCESS
            );

        } catch (Exception e) {

            return baseMapper.mapToBaseResponse(
                    "Ошибка системы!",
                    null,
                    RestStatus.FAIL
            );
        }
    }

    @Override
    public BaseResponse provide(OrderCreateDto orderCreateDto) {

        try {

            if (!Pattern.matches("^(996)?\\s\\d{3}-\\d{3}-\\d{3}", orderCreateDto.getUserPhoneNumber())) {

                return baseMapper.mapToBaseResponse(
                        "Вы ввели некорректный номер телефона!",
                        null,
                        RestStatus.FAIL
                );
            }

            UserResponse userResponse = userMapper.mapToUserResponse(userService.findByPhoneNumber(orderCreateDto.getUserPhoneNumber()));

            if (Objects.isNull(userResponse)) {

                return baseMapper.mapToBaseResponse(
                        "Пользователя с введенным номером нет в базе, пройдите регистрацию!",
                        null,
                        RestStatus.FAIL
                );
            }

            CarResponse carResponse = carMapper.mapToCarResponse(carService.getById(orderCreateDto.getCarId()));

            if (Objects.isNull(carResponse)) {

                return baseMapper.mapToBaseResponse(
                        "Машина с такой ID нет в базе!",
                        null,
                        RestStatus.FAIL
                );
            }

            String validateDate = "^\\d{4}-\\d{2}-\\d{2}";

            if (!Pattern.matches(validateDate, orderCreateDto.getStartDate())
                    || !Pattern.matches(validateDate, orderCreateDto.getEndDate())) {

                return baseMapper.mapToBaseResponse(
                        "Вы ввели некорректную дату!",
                        null,
                        RestStatus.FAIL
                );
            }

            int days = calculateDays(orderCreateDto.getStartDate(), orderCreateDto.getEndDate());

            double totalSum = calculateRentSum(
                    days,
                    orderCreateDto.getKm(),
                    carResponse.getPricePerDay(),
                    carResponse.getFuelPerKm(),
                    carResponse.getFuelType()
            );

            OrderResponse orderResponse = orderMapper.mapToOrderResponseDto(
                    orderService.save(
                            userMapper.mapToUser(userResponse),
                            carMapper.mapToCar(carResponse),
                            orderCreateDto.getStartDate(),
                            orderCreateDto.getEndDate(),
                            totalSum,
                            orderCreateDto.getKm()
                    )
            );

            return baseMapper.mapToBaseResponse(
                    "Заказ успешно оформлен!",
                    orderResponse,
                    RestStatus.SUCCESS
            );
        } catch (Exception e) {

            return baseMapper.mapToBaseResponse(
                    "Произошла ошибка! Обратитесь к системному аднимнистратору!",
                    null,
                    RestStatus.FAIL
            );
        }
    }

    private double calculateRentSum(int days, double km, double pricePerDay, double fuelPer100Km, FuelType fuelType) {

        double sumByDays = days * pricePerDay;
        double fuelPrice = ((fuelPer100Km / 100.0) * km) * fuelService.findFuelPriceByType(fuelType);

        return sumByDays + fuelPrice;
    }

    private int calculateDays(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);

        Period period = Period.between(start, end);

        System.out.println(period.getDays());
        return period.getDays();
    }
}