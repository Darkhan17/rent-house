package kz.kbtu.renthouse.controller;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import kz.kbtu.renthouse.domain.dto.exception.RentException;
import kz.kbtu.renthouse.domain.dto.hobby.AssignHobbiesDTO;
import kz.kbtu.renthouse.domain.dto.user.UpdateUserDTO;
import kz.kbtu.renthouse.domain.dto.user.UserDTO;
import kz.kbtu.renthouse.mapper.UserMapper;
import kz.kbtu.renthouse.repository.entity.HouseEntity;
import kz.kbtu.renthouse.repository.entity.QHouseEntity;
import kz.kbtu.renthouse.repository.entity.QUser;
import kz.kbtu.renthouse.repository.entity.User;
import kz.kbtu.renthouse.service.HobbyService;
import kz.kbtu.renthouse.service.IUserService;
import kz.kbtu.renthouse.util.ContextUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final IUserService userService;
    private final UserMapper userMapper;
    private final HobbyService hobbyService;
    @GetMapping("")
    public ResponseEntity<Iterable<UserDTO>> getUsers(
            @QuerydslPredicate(root = User.class) Predicate predicate,
            @RequestParam (required = false) String cityId,
            @RequestParam (required = false) String hobbyId
    ) {
        QUser qInstance = QUser.user;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qInstance.isActive.eq(true));
        if (cityId != null)
            builder.and(qInstance.city.id.eq(cityId));

        if (hobbyId != null) {
            var hobby = hobbyService.getHobbyById(hobbyId);
            builder.and(qInstance.hobbies.contains(hobby));
        }

        return ResponseEntity.ok(
                userMapper.map(
                        userService.getUsers(builder.and(predicate).getValue())
                )
        );
    }

    @GetMapping("profile")
    public ResponseEntity<UserDTO> getUserProfile() {
        return ResponseEntity.ok(userMapper.map(userService.getUserById(ContextUtils.getUserDetailsImpl().getId())));
    }



    @GetMapping("{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String userId) throws Exception {
        UserDTO userDTO = userMapper.map(userService.getUserById(userId));
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("{userId}")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable String userId,
            @RequestBody UpdateUserDTO updateUserDTO
    ) {
        User user = userService.updateUser(userId, updateUserDTO);
        return ResponseEntity.ok(userMapper.map(user));
    }
    @PostMapping("{userId}/hobbies")
    public ResponseEntity<UserDTO> updateHobbies(
            @PathVariable String userId,
            @RequestBody AssignHobbiesDTO assignHobbiesDTO
    ) throws RentException {
        UserDTO userDTO = userMapper.map(userService.assignHobbiesToUser(userId, assignHobbiesDTO));
        return ResponseEntity.ok(userDTO);
    }

}
