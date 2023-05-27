package kz.kbtu.renthouse.repository.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity(name = "social-media-networks")
@Getter
@Setter
@NoArgsConstructor
public class SocialMediaProfile {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private String link;
}
