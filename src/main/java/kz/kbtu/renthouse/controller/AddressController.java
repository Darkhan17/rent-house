package kz.kbtu.renthouse.controller;


import kz.kbtu.renthouse.domain.dto.address.CreateCity;
import kz.kbtu.renthouse.repository.entity.address.City;
import kz.kbtu.renthouse.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("cities")
@RestController
public class AddressController {

    private final AddressService addressService;

    @GetMapping()
    public List<City> getCities() {
        return addressService.getAllCities();
    }

    @PostMapping
    public City createCity(@RequestBody CreateCity createCity) {
        return addressService.createCity(createCity);
    }

}
