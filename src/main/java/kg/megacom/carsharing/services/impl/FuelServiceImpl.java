package kg.megacom.carsharing.services.impl;

import kg.megacom.carsharing.dtos.fuel.FuelCreateDto;
import kg.megacom.carsharing.enums.FuelType;
import kg.megacom.carsharing.models.entities.Fuel;
import kg.megacom.carsharing.repositories.FuelRepository;
import kg.megacom.carsharing.services.FuelService;
import org.springframework.stereotype.Service;

    @Service
    public class FuelServiceImpl implements FuelService {

        private final FuelRepository fuelRepository;

        public FuelServiceImpl(FuelRepository fuelRepository) {
            this.fuelRepository = fuelRepository;
        }

        @Override
        public Fuel save(FuelCreateDto fuelCreateDto) {
            Fuel fuel = new Fuel();
            fuel.setPrice(fuelCreateDto.getPrice());
            fuel.setFuelType(fuelCreateDto.getFuelType());
            fuel.setActive(fuelCreateDto.isActive());
            fuel = fuelRepository.save(fuel);
            return fuel;
        }

    @Override
    public double findFuelPriceByType(FuelType fuelType) {
        return fuelRepository.findByFuelType(fuelType).getPrice();
    }
}
