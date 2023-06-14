package kz.kbtu.renthouse.service;


import com.querydsl.core.types.Predicate;
import kz.kbtu.renthouse.domain.dto.AddToSavedHouseDTO;
import kz.kbtu.renthouse.domain.dto.CreateHouseDTO;
import kz.kbtu.renthouse.domain.dto.FilterResponseDTO;
import kz.kbtu.renthouse.domain.dto.UpdateHouseDTO;
import kz.kbtu.renthouse.domain.dto.auth.UserDetailsImpl;
import kz.kbtu.renthouse.domain.dto.exception.RentException;
import kz.kbtu.renthouse.mapper.AddressMapper;
import kz.kbtu.renthouse.mapper.HouseMapper;
import kz.kbtu.renthouse.repository.*;
import kz.kbtu.renthouse.repository.entity.*;
import kz.kbtu.renthouse.repository.entity.address.AddressEntity;
import kz.kbtu.renthouse.repository.entity.address.City;
import kz.kbtu.renthouse.util.ContextUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


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
    private final CityRepository cityRepository;

    public HouseEntity getHouseById(String houseId) {
        return houseRepository.findById(houseId).orElseThrow(
                () -> new RentException("House not found", HttpStatus.NOT_FOUND.value())
        );
    }

    @Transactional
    public HouseEntity createHouse(CreateHouseDTO createHouseDTO) {
        UserDetailsImpl userDetails = ContextUtils.getUserDetailsImpl();
        String userid = userDetails.getId();
        User user = userService.getUserById(userid);

        HouseEntity house = houseMapper.map(createHouseDTO, user);
        City city = cityRepository.findById(createHouseDTO.getAddress().getCityId()).orElseThrow(
                ()-> new RentException("City not found", HttpStatus.NOT_FOUND.value())
        );


        AddressEntity address = addressMapper.map(createHouseDTO.getAddress());
        address.setHouse(house);
        address.setCity(city);
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
                savedHouses.stream().map(SavedHouse::getHouse).collect(Collectors.toList()),
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

    public void deleteFromSaved(String houseId) {
        SavedHouse house = savedHouseRepository.findByHouse_IdAndUser_Id(houseId, ContextUtils.getUserDetailsImpl().getId());
        savedHouseRepository.delete(house);
    }

    public HouseEntity changeTheStatusOfChecking(String houseId) {
        HouseEntity house = getHouseById(houseId);
        house.setChecked(true);
        return houseRepository.save(house);
    }

    public HouseEntity changeStatus(String id) {
        HouseEntity house = getHouseById(id);
        house.setActive(!house.isActive());
        return houseRepository.save(house);
    }

    public FilterResponseDTO getHouseFilters(String cityId) {
        BigDecimal maxPrice = houseRepository.findMaxPrice(cityId).orElse(new BigDecimal(0));
        BigDecimal minPrice = houseRepository.findMinPrice(cityId).orElse(new BigDecimal(0));
        Integer maxValueOfResidence = houseRepository.findMaxNumberOfResidence(cityId).orElse(0);
        Integer minValueOfResidence = houseRepository.findMinNumberOfResidence(cityId).orElse(0);
        List<TypeOfHouse> typeOfHouseList  = houseRepository.findDistinctByTypeOfHouse(cityId);
        return FilterResponseDTO
                .builder()
                .priceMaxValue(maxPrice)
                .priceMinValue(minPrice)
                .maxNumberOfResidence(maxValueOfResidence)
                .minNumberOfResidence(minValueOfResidence)
                .typeOfHouseList(typeOfHouseList)
                .build();
    }
}
