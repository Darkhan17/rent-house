package kz.kbtu.renthouse.service;

public interface IAuthService {
    boolean checkOtp(String email, String otpCode);

    void sendOtp(String email);
}
