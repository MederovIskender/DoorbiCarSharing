package kg.megacom.carsharing.controllers;

import kg.megacom.carsharing.dtos.base.BaseResponse;
import kg.megacom.carsharing.dtos.car.CarCreateDto;
import kg.megacom.carsharing.endpoints.CarEndpoint;
import kg.megacom.carsharing.enums.CarStatus;
import kg.megacom.carsharing.enums.CarType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/car")
public class CarController {
    private final CarEndpoint carEndpoint;

    public CarController(CarEndpoint carEndpoint) {
        this.carEndpoint = carEndpoint;
    }
    @PostMapping("/save")
    public ResponseEntity<BaseResponse> save(@RequestBody CarCreateDto carCreateDto) {
        return ResponseEntity.ok().body(carEndpoint.save(carCreateDto));
    }

    @GetMapping("/getBy")
    public ResponseEntity<BaseResponse> getBy(@RequestParam(required = false) CarStatus carStatus, @RequestParam(required = false) CarType carType) {
        return ResponseEntity.ok().body(carEndpoint.getByTypeOrStatus(carStatus, carType));
    }
}
