package kz.kbtu.renthouse.controller;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import jakarta.validation.constraints.Min;
import kz.kbtu.renthouse.domain.dto.*;
import kz.kbtu.renthouse.mapper.HouseMapper;
import kz.kbtu.renthouse.repository.entity.HouseEntity;
import kz.kbtu.renthouse.repository.entity.QHouseEntity;
import kz.kbtu.renthouse.service.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("houses")
@RequiredArgsConstructor
public class HouseController {

    private final HouseMapper houseMapper;
    private final HouseService houseService;


    @GetMapping
    public Page<HouseDTO> getAllPagedHouses(
            @QuerydslPredicate(root = HouseEntity.class) Predicate predicate,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) @Min(0) BigDecimal minPrice,
            @RequestParam(required = false) Integer maxValueOfResidence,
            @RequestParam(required = false) @Min(1) Integer minValueOfResidence,
            Pageable pageable
    ) {
        QHouseEntity qInstance = QHouseEntity.houseEntity;
        BooleanBuilder builder = new BooleanBuilder();

        if (maxPrice != null && minPrice!=null) {
            builder.and(qInstance.price.between(minPrice, maxPrice));
        }

        Page<HouseEntity> houseEntities = houseService.getPagedAllHouse(builder.and(predicate), pageable);
        List<HouseDTO> houseDTOList = houseMapper.map(houseEntities.getContent());
        return new PageImpl<>(
                houseDTOList,
                houseEntities.getPageable(),
                houseEntities.getTotalElements()
        );
    }
    @PostMapping
    public HouseDTO createHouse(
            @RequestBody CreateHouseDTO createHouseDTO
    ) {
        return houseMapper.map(houseService.createHouse(createHouseDTO));
    }


    @GetMapping("saved")
    public Page<HouseDTO> getSavedHouses(Pageable pageable) {
        Page<HouseEntity> houseEntities = houseService.getSavedHouses(pageable);
        List<HouseDTO> houseDTOList = houseMapper.map(houseEntities.getContent());
        return new PageImpl<>(
                houseDTOList,
                houseEntities.getPageable(),
                houseEntities.getTotalElements()
        );
    }

    @PostMapping("saved")
    public void getSavedHouses(@RequestBody AddToSavedHouseDTO addToSavedHouseDTO) {
        houseService.addToSavedHouse(addToSavedHouseDTO);
    }

    @GetMapping("my-adds")
    public Page<HouseDTO> getMyHouses(Pageable pageable) {
        Page<HouseEntity> houseEntities = houseService.getMyHouses(pageable);
        List<HouseDTO> houseDTOList = houseMapper.map(houseEntities.getContent());
        return new PageImpl<>(
                houseDTOList,
                houseEntities.getPageable(),
                houseEntities.getTotalElements()
        );
    }

    @GetMapping("filters")
    public FilterResponseDTO getFilters() {
        return null;
    }
    @GetMapping("{houseId}")
    public HouseDTO getApartment (
            @PathVariable String houseId
    ) {
        return houseMapper.map(houseService.getHouseById(houseId));
    }

    @PutMapping("{houseId}")
    public HouseDTO updateHouse (
            @PathVariable String houseId,
            @RequestBody UpdateHouseDTO updateHouseDTO
    ) {
        return houseMapper.map(
                houseService.updateHouse(
                        houseId,
                        updateHouseDTO
                )
        );
    }

    @DeleteMapping("{houseId}")
    public void deleteHouse (
            @PathVariable String houseId
    ) {
        houseService.deleteHouseById(houseId);
    }

}
