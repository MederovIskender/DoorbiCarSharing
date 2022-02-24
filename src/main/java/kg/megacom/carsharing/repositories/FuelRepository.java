package kg.megacom.carsharing.repositories;

import kg.megacom.carsharing.enums.FuelType;
import kg.megacom.carsharing.models.entities.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuelRepository extends JpaRepository<Fuel,Long> {

    Fuel findByFuelType(FuelType fuelType);
}
