package kz.kbtu.renthouse.repository;

import kz.kbtu.renthouse.repository.entity.HouseEntity;
import kz.kbtu.renthouse.repository.entity.TypeOfHouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface HouseRepository extends JpaRepository<HouseEntity, String>,
        QuerydslPredicateExecutor<HouseEntity> {

    Page<HouseEntity> getByAuthor_Id(String authorId, Pageable pageable);

    @Query("select max(price) from HouseEntity where address.city.id = ?1")
    Optional<BigDecimal> findMaxPrice(String cityId);

    @Query("select min(price) from HouseEntity where address.city.id = ?1 ")
    Optional<BigDecimal> findMinPrice(String cityId);

    @Query("select max(numberOfResidents) from HouseEntity where address.city.id = ?1")
    Optional<Integer> findMaxNumberOfResidence(String cityId);

    @Query("select min(numberOfResidents) from HouseEntity where address.city.id = ?1")
    Optional<Integer> findMinNumberOfResidence(String cityId);

    @Query("select DISTINCT (c.typeOfHouse) from HouseEntity c where c.address.city.id = ?1")
    List<TypeOfHouse> findDistinctByTypeOfHouse(String cityId);


}
