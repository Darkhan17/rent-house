package kz.kbtu.renthouse.service;


import kz.kbtu.renthouse.domain.dto.CreateHouseDTO;
import kz.kbtu.renthouse.domain.dto.UpdateHouseDTO;
import kz.kbtu.renthouse.domain.dto.exception.RentException;
import kz.kbtu.renthouse.mapper.HouseMapper;
import kz.kbtu.renthouse.repository.HouseRepository;
import kz.kbtu.renthouse.repository.entity.HouseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HouseService {

    private final HouseRepository houseRepository;
    private final HouseMapper houseMapper;

    public HouseEntity getHouseById(String houseId) {
        return houseRepository.findById(houseId).orElseThrow(
                () -> new RentException("House not found", HttpStatus.NOT_FOUND.value())
        );
    }

    public HouseEntity createHouse(CreateHouseDTO createHouseDTO) {
        HouseEntity house = houseMapper.map(createHouseDTO);
        return houseRepository.save(house);
    }

    public HouseEntity updateHouse(String houseId, UpdateHouseDTO updateHouseDTO) {
        HouseEntity house = getHouseById(houseId);
        houseMapper.map(house, updateHouseDTO);
        return houseRepository.save(house);
    }

    public void deleteHouseById(String houseId) {
        houseRepository.deleteById(houseId);
    }
}
