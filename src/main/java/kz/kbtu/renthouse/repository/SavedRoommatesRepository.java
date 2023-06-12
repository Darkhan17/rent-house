package kz.kbtu.renthouse.repository;

import kz.kbtu.renthouse.repository.entity.SavedRoommates;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SavedRoommatesRepository extends JpaRepository<SavedRoommates, String> {

    List<SavedRoommates> findByUserId(String userId);
}
