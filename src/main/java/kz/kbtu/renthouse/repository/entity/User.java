package kz.kbtu.renthouse.repository.entity;


import jakarta.persistence.*;
import jakarta.persistence.Table;
import kz.kbtu.renthouse.repository.entity.address.City;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "users")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column()
    private String photo;

    @OneToMany
    private Set<Hobby> hobbies;

    @OneToMany
    private Set<SocialMediaProfile> socialMediaProfiles;

    private String description;

    @Column
    @NumberFormat
    private String phoneNumber;

    private boolean isActive;

    private Role role;

    private Gender gender;

    @ManyToOne
    private City city;
}
