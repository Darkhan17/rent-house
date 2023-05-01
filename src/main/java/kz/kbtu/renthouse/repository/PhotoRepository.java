package kz.kbtu.renthouse.repository;

import kz.kbtu.renthouse.repository.entity.Photo;
import org.springframework.data.repository.CrudRepository;


public interface PhotoRepository extends CrudRepository<Photo, String> {
}
