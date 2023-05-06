package kz.kbtu.renthouse.repository;

import kz.kbtu.renthouse.repository.entity.Code;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CodeRepository extends JpaRepository<Code, String> {
    Optional<Code> findByEmail(String email);
}
