package kz.kbtu.renthouse.controller;


import kz.kbtu.renthouse.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("admin")
public class AdminController {


    private final UserService userService;

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void deactivateUser(@RequestParam String userId) {
        userService.deactivateUser(userId);
    }
}
