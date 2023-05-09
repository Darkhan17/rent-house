package kz.kbtu.renthouse.service;


import kz.kbtu.renthouse.domain.dto.address.CreateCity;
import kz.kbtu.renthouse.repository.CityRepository;
import kz.kbtu.renthouse.repository.entity.address.City;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final CityRepository cityRepository;

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public City createCity(CreateCity createCity) {
        return cityRepository.save(new City(createCity.getName()));
    }
}
