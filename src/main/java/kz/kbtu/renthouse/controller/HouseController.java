package kz.kbtu.renthouse.controller;

import com.querydsl.core.types.Predicate;
import kz.kbtu.renthouse.domain.dto.CreateHouseDTO;
import kz.kbtu.renthouse.domain.dto.HouseDTO;
import kz.kbtu.renthouse.domain.dto.HouseFilters;
import kz.kbtu.renthouse.domain.dto.UpdateHouseDTO;
import kz.kbtu.renthouse.mapper.HouseMapper;
import kz.kbtu.renthouse.repository.entity.HouseEntity;
import kz.kbtu.renthouse.repository.entity.TypeOfHouse;
import kz.kbtu.renthouse.service.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;

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
            Pageable pageable
    ) {
        Page<HouseEntity> houseEntities = houseService.getPagedAllHouse(predicate, pageable);
        List<HouseDTO> houseDTOList = houseMapper.map(houseEntities.getContent());
        return new PageImpl<>(
                houseDTOList,
                houseEntities.getPageable(),
                houseEntities.getTotalElements()
        );
    }
    @PostMapping
    public HouseDTO getAllPagedHouses(
            @RequestBody CreateHouseDTO createHouseDTO
    ) {
        return houseMapper.map(houseService.createHouse(createHouseDTO));
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
