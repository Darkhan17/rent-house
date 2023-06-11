package kz.kbtu.renthouse.controller;


import kz.kbtu.renthouse.domain.dto.hobby.CreateHobbyDTO;
import kz.kbtu.renthouse.repository.entity.Hobby;
import kz.kbtu.renthouse.service.HobbyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("hobbies")
public class HobbiesController {

    private final HobbyService hobbyService;

    @PostMapping
    public ResponseEntity<Hobby> createHobby(
            @RequestBody CreateHobbyDTO createHobbyDTO
    ) {
        return ResponseEntity.ok(hobbyService.createHouse(createHobbyDTO));
    }

    @GetMapping
    public ResponseEntity<List<Hobby>> getHobbies() {
        return ResponseEntity.ok(
                hobbyService.getHobbies()
        );
    }
}
