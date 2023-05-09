package kz.kbtu.renthouse.repository;

import kz.kbtu.renthouse.repository.entity.address.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, String> {

}
