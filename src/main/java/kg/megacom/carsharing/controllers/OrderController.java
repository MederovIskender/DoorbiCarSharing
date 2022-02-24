package kg.megacom.carsharing.controllers;

import kg.megacom.carsharing.dtos.base.BaseResponse;
import kg.megacom.carsharing.dtos.order.OrderCreateDto;
import kg.megacom.carsharing.dtos.order.RentCalculationRequestDto;
import kg.megacom.carsharing.endpoints.OrderEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order")
public class OrderController {

    private final OrderEndpoint orderEndpoint;

    public OrderController(OrderEndpoint orderEndpoint) {
        this.orderEndpoint = orderEndpoint;
    }

    @GetMapping("/rentCalculation")
    public ResponseEntity<BaseResponse> rentCalculation(@RequestBody RentCalculationRequestDto rentRequest) {
        return ResponseEntity.ok().body(orderEndpoint.rentCalculation(rentRequest));
    }

    @PostMapping("/provide")
    public ResponseEntity<BaseResponse> provide(@RequestBody OrderCreateDto orderCreateDto) {
        return ResponseEntity.ok().body(orderEndpoint.provide(orderCreateDto));
    }
}