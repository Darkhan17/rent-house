package kz.kbtu.renthouse.service;


import kz.kbtu.renthouse.domain.dto.exception.RentException;
import kz.kbtu.renthouse.domain.dto.user.AddToSavedRoommates;
import kz.kbtu.renthouse.repository.SavedRoommatesRepository;
import kz.kbtu.renthouse.repository.UserRepository;
import kz.kbtu.renthouse.repository.entity.SavedRoommates;
import kz.kbtu.renthouse.repository.entity.User;
import kz.kbtu.renthouse.util.ContextUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoommatesServiceImpl implements RoommatesService {


    private final UserRepository userRepository;
    private final SavedRoommatesRepository savedRoommatesRepository;


    @Override
    public void addToSaved(AddToSavedRoommates addToSavedRoommates) {
        User user = getUser(ContextUtils.getUserDetailsImpl().getId());
        User addToSaved = getUser(addToSavedRoommates.getUserId());
        savedRoommatesRepository.save(new SavedRoommates(user, addToSaved));
    }

    @Override
    public void deleteFromSaved(String roommateId) {
        savedRoommatesRepository.deleteById(roommateId);
    }

    @Override
    public List<SavedRoommates> getAllRoommates(String id) {
        return savedRoommatesRepository.findByUserId(id);
    }

    private User getUser(String userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new RentException("User not found", HttpStatus.NOT_FOUND.value())
        );
    }
}
