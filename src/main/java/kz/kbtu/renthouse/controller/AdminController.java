package kz.kbtu.renthouse.controller;


import com.querydsl.core.BooleanBuilder;
import kz.kbtu.renthouse.domain.dto.HouseDTO;
import kz.kbtu.renthouse.mapper.HouseMapper;
import kz.kbtu.renthouse.repository.entity.HouseEntity;
import kz.kbtu.renthouse.repository.entity.QHouseEntity;
import kz.kbtu.renthouse.service.HouseService;
import kz.kbtu.renthouse.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("admin")
public class AdminController {


    private final UserService userService;
    private final HouseService houseService;
    private final HouseMapper houseMapper;

    @PutMapping("users")
    //@PreAuthorize("hasRole('ADMIN')")
    public void deactivateUser(@RequestParam String userId) {
        userService.deactivateUser(userId);
    }

    @GetMapping("houses")
    //@PreAuthorize("hasRole('ADMIN')")
    public Page<HouseDTO> getPagedHouses(Pageable pageable) {
        QHouseEntity qInstance = QHouseEntity.houseEntity;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qInstance.isChecked.eq(false));
        Page<HouseEntity> houseEntities = houseService.getPagedAllHouse(builder.getValue(), pageable);
        List<HouseDTO> houseDTOList = houseMapper.map(houseEntities.getContent());

        return new PageImpl<>(
                houseDTOList,
                houseEntities.getPageable(),
                houseEntities.getTotalElements()
        );
    }

    @PutMapping("houses/{houseId}/status")
    //@PreAuthorize("hasRole('ADMIN')")
    public HouseDTO changeTheStatusOf(@PathVariable String houseId){
        return houseMapper.map(houseService.changeTheStatusOfChecking(houseId));
    }


}
