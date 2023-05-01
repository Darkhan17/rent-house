package kz.kbtu.renthouse.service;


import com.querydsl.core.types.Predicate;
import kz.kbtu.renthouse.domain.dto.CreateHouseDTO;
import kz.kbtu.renthouse.domain.dto.UpdateHouseDTO;
import kz.kbtu.renthouse.domain.dto.exception.RentException;
import kz.kbtu.renthouse.mapper.HouseMapper;
import kz.kbtu.renthouse.repository.HouseRepository;
import kz.kbtu.renthouse.repository.PhotoRepository;
import kz.kbtu.renthouse.repository.UserRepository;
import kz.kbtu.renthouse.repository.entity.HouseEntity;
import kz.kbtu.renthouse.repository.entity.Photo;
import kz.kbtu.renthouse.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class HouseService {

    private final HouseRepository houseRepository;
    private final HouseMapper houseMapper;
    private final UserService userService;
    private final PhotoRepository photoRepository;

    public HouseEntity getHouseById(String houseId) {
        return houseRepository.findById(houseId).orElseThrow(
                () -> new RentException("House not found", HttpStatus.NOT_FOUND.value())
        );
    }

    public HouseEntity createHouse(CreateHouseDTO createHouseDTO) {
        User user = userService.getUserById(createHouseDTO.getAuthorId());
        HouseEntity house = houseMapper.map(createHouseDTO, user);
        house = houseRepository.save(house);
        Set<Photo> photoSet = new HashSet<>();
        for (String link : createHouseDTO.getPhotos()) {
            photoSet.add(new Photo(link, house));
        }
        photoRepository.saveAll(photoSet);
        return house;
    }

    public HouseEntity updateHouse(String houseId, UpdateHouseDTO updateHouseDTO) {
        HouseEntity house = getHouseById(houseId);
        houseMapper.map(house, updateHouseDTO);
        return houseRepository.save(house);
    }

    public void deleteHouseById(String houseId) {
        houseRepository.deleteById(houseId);
    }

    public Page<HouseEntity> getPagedAllHouse(Predicate predicate, Pageable pageable) {
        return houseRepository.findAll(predicate, pageable);
    }
}
