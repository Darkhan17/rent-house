package kz.kbtu.renthouse.repository;

import kz.kbtu.renthouse.repository.entity.address.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, String> {
}
