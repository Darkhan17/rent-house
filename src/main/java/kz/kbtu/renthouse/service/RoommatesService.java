package kz.kbtu.renthouse.service;

import kz.kbtu.renthouse.domain.dto.user.AddToSavedRoommates;
import kz.kbtu.renthouse.repository.entity.SavedRoommates;

import java.util.List;

public interface RoommatesService {
    void addToSaved(AddToSavedRoommates addToSavedRoommates);

    void deleteFromSaved(String roommateId);

    List<SavedRoommates> getAllRoommates(String id);
}
