package kg.megacom.carsharing.repositories;

import kg.megacom.carsharing.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;

public interface UserRepository extends JpaRepository<User, Long> {


    boolean existsByPhoneNumber(String email);

    User getByPhoneNumber(String userPhoneNumber);
}
