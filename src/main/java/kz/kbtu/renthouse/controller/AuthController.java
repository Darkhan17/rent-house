package kz.kbtu.renthouse.controller;

import jakarta.validation.Valid;
import kz.kbtu.renthouse.domain.dto.auth.*;
import kz.kbtu.renthouse.domain.dto.token.JwtResponse;
import kz.kbtu.renthouse.domain.dto.user.CreateUserDTO;
import kz.kbtu.renthouse.domain.dto.user.UserDTO;
import kz.kbtu.renthouse.mapper.UserMapper;
import kz.kbtu.renthouse.repository.entity.User;
import kz.kbtu.renthouse.service.IAuthService;
import kz.kbtu.renthouse.service.IUserService;
import kz.kbtu.renthouse.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final IUserService userService;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final IAuthService authService;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws Exception {

        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(userDetails);

        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .toList();

        //RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        return ResponseEntity.ok(new JwtResponse(jwt, null, userDetails.getId(),
                userDetails.getUsername(), userDetails.getEmail(), roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        // Create new user's account
        User user = userService.creatUser(
                new CreateUserDTO(
                        signUpRequest.getEmail().toLowerCase(),
                        passwordEncoder.encode(signUpRequest.getPassword()),
                        signUpRequest.getPhoneNumber(),
                        signUpRequest.getGender(),
                        signUpRequest.getPhoto(),
                        signUpRequest.getCityId()
                )
        );
        return ResponseEntity.ok(userMapper.map(user));
    }

    @PostMapping("otp")
    public void sendOtp(
            @Valid @RequestBody OtpRequest otpRequest
    ) {
        authService.sendOtp(otpRequest.getEmail());
    }


    @PostMapping("otp/check")
    public boolean checkOtp(
            @Valid @RequestBody CodeRequest codeRequest
    ) {
        return authService.checkOtp(codeRequest.getEmail(), codeRequest.getCode());
    }


    @GetMapping("is-exist")
    public boolean isExistsByEmail(@RequestParam String email) {
        return userService.isExistsByEmail(email.toLowerCase());
    }

}
