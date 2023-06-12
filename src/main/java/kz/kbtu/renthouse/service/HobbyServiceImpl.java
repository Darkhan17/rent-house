package kz.kbtu.renthouse.service;

import kz.kbtu.renthouse.domain.dto.exception.RentException;
import kz.kbtu.renthouse.domain.dto.hobby.CreateHobbyDTO;
import kz.kbtu.renthouse.mapper.HobbyMapper;
import kz.kbtu.renthouse.repository.HobbyRepository;
import kz.kbtu.renthouse.repository.HouseRepository;
import kz.kbtu.renthouse.repository.entity.Hobby;
import kz.kbtu.renthouse.repository.entity.HouseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HobbyServiceImpl implements HobbyService{

    private final HobbyRepository hobbyRepository;
    private final HobbyMapper hobbyMapper;

    @Override
    public Hobby createHouse(CreateHobbyDTO createHobbyDTO) {
        Hobby hobby = hobbyMapper.map(createHobbyDTO);
        return hobbyRepository.save(hobby);
    }

    @Override
    public List<Hobby> getHobbies() {
        return hobbyRepository.findAll();
    }

    @Override
    public Hobby getHobbyById(String hobbyId) {
        return hobbyRepository.findById(hobbyId).orElseThrow(
                () -> new RentException("hobby not found", HttpStatus.NOT_FOUND.value())
        );
    }

    @Override
    public List<Hobby> getHobbyByIds(List<String> hobbyIds) {
        return hobbyRepository.findAllById(hobbyIds);
    }
}
