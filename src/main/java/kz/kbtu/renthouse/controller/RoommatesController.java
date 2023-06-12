package kz.kbtu.renthouse.controller;


import kz.kbtu.renthouse.domain.dto.user.AddToSavedRoommates;
import kz.kbtu.renthouse.domain.dto.rommates.SavedRoommateDTO;
import kz.kbtu.renthouse.mapper.RoommateMapper;
import kz.kbtu.renthouse.service.RoommatesService;
import kz.kbtu.renthouse.util.ContextUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("roommates")
public class RoommatesController {

    private final RoommatesService roommatesService;
    private final RoommateMapper roommateMapper;
    @GetMapping
    public List<SavedRoommateDTO> getSaveRoommates() {
        return roommateMapper.map(
                roommatesService.getAllRoommates(ContextUtils.getUserDetailsImpl().getId())
        );
    }
    @PostMapping()
    public void addToFavourite(
            @RequestBody AddToSavedRoommates addToSavedRoommates
    ){
        roommatesService.addToSaved(addToSavedRoommates);
    }


    @DeleteMapping("{roommateId}")
    public void deleteFromSaved(
            @PathVariable String roommateId
    ) {
        roommatesService.deleteFromSaved(roommateId);
    }
}
