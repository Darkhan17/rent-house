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
            @RequestParam String cityId,
            Pageable pageable
    ) {
        QHouseEntity qInstance = QHouseEntity.houseEntity;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qInstance.isActive.eq(true));
        builder.and(qInstance.isChecked.eq(true));
        builder.and(qInstance.address.city.id.eq(cityId));

        if (maxPrice != null && minPrice!=null) {
            builder.and(qInstance.price.between(minPrice, maxPrice));
        }

        if (maxValueOfResidence != null && minValueOfResidence!= null)
            builder.and(qInstance.numberOfResidents.between(minValueOfResidence, maxValueOfResidence));

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
    public List<HouseDTO> getSavedHouses(Pageable pageable) {
        Page<HouseEntity> houseEntities = houseService.getSavedHouses(pageable);
        return houseMapper.map(houseEntities.getContent());
    }

    @PostMapping("saved")
    public void addToSavedHouses(@RequestBody AddToSavedHouseDTO addToSavedHouseDTO) {
        houseService.addToSavedHouse(addToSavedHouseDTO);
    }

    @DeleteMapping("saved/{houseId}")
    public void deleteFromSaved(
            @PathVariable String houseId
    ) {
        houseService.deleteFromSaved(houseId);
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
    public FilterResponseDTO getFilters(
            @RequestParam String cityId
    ) {
        return houseService.getHouseFilters(cityId);
    }


    @PutMapping("{houseId}/status")
    public HouseDTO changeStatus(@PathVariable String houseId) {
        return houseMapper.map(houseService.changeStatus(houseId));
    }


    @GetMapping("{houseId}")
    public HouseDTO getApartment (
            @PathVariable String houseId
    ) {
        return houseMapper.map(houseService.getHouseById(houseId));
    }

    @PutMapping("{houseId}")
    public HouseDTO changeStatus(
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
