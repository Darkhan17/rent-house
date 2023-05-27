package kz.kbtu.renthouse.domain.dto.user;

import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import kz.kbtu.renthouse.repository.entity.Gender;
import kz.kbtu.renthouse.repository.entity.Hobby;
import kz.kbtu.renthouse.repository.entity.Role;
import kz.kbtu.renthouse.repository.entity.SocialMediaProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String id;
    private String email;
    private LocalDate createdAt;
    private String photo;
    private Set<Hobby> hobbies;
    private Set<SocialMediaProfile> socialMediaProfiles;
    private String description;
    private String phoneNumber;
    private boolean isActive;
    private Role role;
    private Gender gender;
}
