package kz.kbtu.renthouse.repository.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Table(name = "otp-codes")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Code {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;


    private String email;
    private String otpCode;

    public Code(String email, String otpCode) {
        this.email = email;
        this.otpCode = otpCode;
    }
}
