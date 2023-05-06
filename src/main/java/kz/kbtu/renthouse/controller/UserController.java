package kz.kbtu.renthouse.controller;


import kz.kbtu.renthouse.domain.dto.user.UpdateUserDTO;
import kz.kbtu.renthouse.domain.dto.user.UserDTO;
import kz.kbtu.renthouse.mapper.UserMapper;
import kz.kbtu.renthouse.repository.entity.User;
import kz.kbtu.renthouse.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final IUserService userService;
    private final UserMapper userMapper;
    @GetMapping("")
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok(userMapper.map(userService.getUsers()));
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
}
