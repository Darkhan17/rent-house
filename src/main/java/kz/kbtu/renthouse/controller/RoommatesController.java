package kz.kbtu.renthouse.controller;


import kz.kbtu.renthouse.domain.dto.user.AddToSavedRoommates;
import kz.kbtu.renthouse.service.RoommatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("roommates")
public class RoommatesController {

    private final RoommatesService roommatesService;

    @PostMapping
    public void addToFavourite(
            @RequestBody AddToSavedRoommates addToSavedRoommates
    ){
        roommatesService.addToSaved(addToSavedRoommates);
    }
}
