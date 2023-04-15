package kz.kbtu.renthouse.service;

import kz.kbtu.renthouse.domain.dto.exception.RentException;
import kz.kbtu.renthouse.domain.dto.user.CreateUserDTO;
import kz.kbtu.renthouse.repository.entity.User;

public interface IUserService {

    User creatUser(CreateUserDTO createUserDTO);

    User getUserById(String userId) throws RentException;

    User getUserByEmail(String userId) throws RentException;


    boolean existsByEmail(String email);

}
