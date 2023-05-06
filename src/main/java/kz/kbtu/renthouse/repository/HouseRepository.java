package kz.kbtu.renthouse.repository;

import com.querydsl.core.types.Predicate;
import kz.kbtu.renthouse.domain.dto.HouseDTO;
import kz.kbtu.renthouse.repository.entity.HouseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<HouseEntity, String>,
        QuerydslPredicateExecutor<HouseEntity> {

    Page<HouseEntity> getByAuthor_Id(String authorId, Pageable pageable);
}
