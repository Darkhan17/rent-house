package kz.kbtu.renthouse.service;

import kz.kbtu.renthouse.domain.dto.exception.RentException;
import kz.kbtu.renthouse.repository.CodeRepository;
import kz.kbtu.renthouse.repository.entity.Code;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final CodeRepository codeRepository;
    private final UserService userService;
    private final EmailService emailService;
    private final Random random;

    @Override
    public boolean checkOtp(String email, String otpCode){
        return getCode(email).getOtpCode().equals(otpCode);
    }

    @Override
    public void sendOtp(String email) {
        if (userService.existsByEmail(email.toLowerCase()))
            throw new RentException("User already registered", HttpStatus.CONTINUE.value());

        String code = String.format("%04d", random.nextInt(10000));
        Optional<Code> userCode = codeRepository.findByEmail(email);
        Code newCode = userCode.orElse(new Code(email, code));
        newCode.setOtpCode(code);
        codeRepository.save(newCode);
        emailService.sendSimpleMessage(email, "otp", "code: " + code);
    }

    private Code getCode(String email) {
        return codeRepository.findByEmail(email).orElseThrow(
                ()-> new RentException("Otp code not found", HttpStatus.NOT_FOUND.value())
        );
    }

}
