package kz.kbtu.renthouse.service;

import kz.kbtu.renthouse.domain.dto.exception.RentException;
import kz.kbtu.renthouse.domain.dto.user.CreateUserDTO;
import kz.kbtu.renthouse.domain.dto.user.UpdateUserDTO;
import kz.kbtu.renthouse.repository.entity.User;

import java.util.List;

public interface IUserService {

    User creatUser(CreateUserDTO createUserDTO);

    User getUserById(String userId) throws RentException;

    User getUserByEmail(String userId) throws RentException;


    boolean existsByEmail(String email);

    List<User> getUsers();

    User updateUser(String userId, UpdateUserDTO updateUserDTO);
}
