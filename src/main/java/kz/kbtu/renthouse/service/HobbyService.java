package kz.kbtu.renthouse.service;

import kz.kbtu.renthouse.domain.dto.hobby.CreateHobbyDTO;
import kz.kbtu.renthouse.repository.entity.Hobby;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HobbyService {

    Hobby createHouse(CreateHobbyDTO createHobbyDTO);

    List<Hobby> getHobbies();

    Hobby getHobbyById(String hobbyId);

    List<Hobby> getHobbyByIds(List<String> hobbyIds);
}
