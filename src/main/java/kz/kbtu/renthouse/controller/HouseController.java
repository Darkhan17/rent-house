package kz.kbtu.renthouse.controller;

import kz.kbtu.renthouse.domain.dto.CreateHouseDTO;
import kz.kbtu.renthouse.domain.dto.HouseDTO;
import kz.kbtu.renthouse.domain.dto.UpdateHouseDTO;
import kz.kbtu.renthouse.mapper.HouseMapper;
import kz.kbtu.renthouse.service.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("houses")
@RequiredArgsConstructor
public class HouseController {

    private final HouseMapper houseMapper;
    private final HouseService houseService;

    @PostMapping
    public HouseDTO createHouse (
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
