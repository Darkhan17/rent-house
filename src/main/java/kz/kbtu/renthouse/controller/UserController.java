package kz.kbtu.renthouse.controller;


import jakarta.validation.Valid;
import kz.kbtu.renthouse.domain.dto.user.CreateUserDTO;
import kz.kbtu.renthouse.domain.dto.user.UserDTO;
import kz.kbtu.renthouse.mapper.UserMapper;
import kz.kbtu.renthouse.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final IUserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid CreateUserDTO createUserDTO) {
        UserDTO userDTO = userMapper.map(userService.creatUser(createUserDTO));
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String userId) throws Exception {
        UserDTO userDTO = userMapper.map(userService.getUserById(userId));
        return ResponseEntity.ok(userDTO);
    }


}
