package kz.kbtu.renthouse.repository;

import kz.kbtu.renthouse.repository.entity.SavedHouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SavedHouseRepository extends JpaRepository<SavedHouse, String> {
    Page<SavedHouse> findSavedHouseByUser_Id(String userId, Pageable pageable);

    boolean existsByUser_IdAndHouse_Id(String userId, String houseId);

    SavedHouse findByHouse_IdAndUser_Id(String houseId, String userId);
}
