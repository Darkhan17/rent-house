package kz.kbtu.renthouse.repository;

import kz.kbtu.renthouse.repository.entity.HouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<HouseEntity, String>,
        QuerydslPredicateExecutor<HouseEntity> {

}
