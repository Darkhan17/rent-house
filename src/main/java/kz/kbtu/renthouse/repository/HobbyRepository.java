package kz.kbtu.renthouse.repository;


import kz.kbtu.renthouse.repository.entity.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HobbyRepository extends JpaRepository<Hobby, String> {

}
