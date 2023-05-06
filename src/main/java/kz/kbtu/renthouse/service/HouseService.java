package kz.kbtu.renthouse.service;


import com.querydsl.core.types.Predicate;
import kz.kbtu.renthouse.domain.dto.AddToSavedHouseDTO;
import kz.kbtu.renthouse.domain.dto.CreateHouseDTO;
import kz.kbtu.renthouse.domain.dto.HouseDTO;
import kz.kbtu.renthouse.domain.dto.UpdateHouseDTO;
import kz.kbtu.renthouse.domain.dto.auth.UserDetailsImpl;
import kz.kbtu.renthouse.domain.dto.exception.RentException;
import kz.kbtu.renthouse.mapper.AddressMapper;
import kz.kbtu.renthouse.mapper.HouseMapper;
import kz.kbtu.renthouse.repository.AddressRepository;
import kz.kbtu.renthouse.repository.HouseRepository;
import kz.kbtu.renthouse.repository.PhotoRepository;
import kz.kbtu.renthouse.repository.SavedHouseRepository;
import kz.kbtu.renthouse.repository.entity.HouseEntity;
import kz.kbtu.renthouse.repository.entity.Photo;
import kz.kbtu.renthouse.repository.entity.SavedHouse;
import kz.kbtu.renthouse.repository.entity.User;
import kz.kbtu.renthouse.repository.entity.address.AddressEntity;
import kz.kbtu.renthouse.util.ContextUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
    private final SavedHouseRepository savedHouseRepository;
    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;

    public HouseEntity getHouseById(String houseId) {
        return houseRepository.findById(houseId).orElseThrow(
                () -> new RentException("House not found", HttpStatus.NOT_FOUND.value())
        );
    }

    public HouseEntity createHouse(CreateHouseDTO createHouseDTO) {
        UserDetailsImpl userDetails = ContextUtils.getUserDetailsImpl();
        String userid = userDetails.getId();
        User user = userService.getUserById(userid);

        HouseEntity house = houseMapper.map(createHouseDTO, user);

        AddressEntity address = addressMapper.map(createHouseDTO.getAddress());
        address.setHouse(house);
        address = addressRepository.save(address);

        house.setAddress(address);
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
        HouseEntity house = getHouseById(houseId);
        if (!house.getAuthor().getId().equals(ContextUtils.getUserDetailsImpl().getId()))
            throw new RentException("Access denied", HttpStatus.FORBIDDEN.value());
        houseRepository.deleteById(houseId);
    }

    public Page<HouseEntity> getPagedAllHouse(Predicate predicate, Pageable pageable) {
        return houseRepository.findAll(predicate, pageable);
    }

    public Page<HouseEntity> getSavedHouses(Pageable pageable) {
        String userId = ContextUtils.getUserDetailsImpl().getId();
        Page<SavedHouse> savedHouses = savedHouseRepository.findSavedHouseByUser_Id(userId, pageable);
        return new PageImpl<>(
                savedHouses.stream().map(SavedHouse::getHouse).toList(),
                savedHouses.getPageable(),
                savedHouses.getTotalElements()
        );
    }

    public Page<HouseEntity> getMyHouses(Pageable pageable) {
        String userId = ContextUtils.getUserDetailsImpl().getId();
        return houseRepository.getByAuthor_Id(userId, pageable);
    }

    public void addToSavedHouse(AddToSavedHouseDTO addToSavedHouseDTO) {
        User user = userService.getUserById(ContextUtils.getUserDetailsImpl().getId());
        HouseEntity house = getHouseById(addToSavedHouseDTO.getHouseId());

        if (savedHouseRepository.existsByUser_IdAndHouse_Id(user.getId(), house.getId()))
            return;

        SavedHouse savedHouse = new SavedHouse(user, house);
        savedHouseRepository.save(savedHouse);
    }
}
