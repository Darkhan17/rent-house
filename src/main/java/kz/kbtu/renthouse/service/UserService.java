package kz.kbtu.renthouse.service;

import kz.kbtu.renthouse.domain.dto.exception.RentException;
import kz.kbtu.renthouse.domain.dto.user.CreateUserDTO;
import kz.kbtu.renthouse.domain.dto.user.UpdateUserDTO;
import kz.kbtu.renthouse.mapper.UserMapper;
import kz.kbtu.renthouse.repository.UserRepository;
import kz.kbtu.renthouse.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public User creatUser(CreateUserDTO createUserDTO) {

        if (userRepository.existsByEmail(createUserDTO.getEmail()))
            throw new RentException("User with such email already exist", HttpStatus.CONFLICT.value());

        User user = userMapper.map(createUserDTO);
        Set<Role> roles = new HashSet<>();

        /*
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);

         */

        return userRepository.save(user);
    }

    @Override
    public User getUserById(String userId) throws RentException {
        return userRepository.findById(userId).orElseThrow(
                () -> new RentException("User not found", HttpStatus.NOT_FOUND.value())
        );
    }

    @Override
    public User getUserByEmail(String email) throws RentException {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new RentException("User not found", HttpStatus.NOT_FOUND.value())
        );
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(String userId, UpdateUserDTO updateUserDTO) {
        User user = getUserById(userId);
        userMapper.mapNonNullValues(user, updateUserDTO);
        return userRepository.save(user);
    }

    @Override
    public void deactivateUser(String userId) {
        User user = getUserById(userId);
        user.setActive(false);
        userRepository.save(user);
    }

}
