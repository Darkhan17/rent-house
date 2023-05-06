package kz.kbtu.renthouse.util;


import kz.kbtu.renthouse.domain.dto.auth.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ContextUtils {

    public static UserDetailsImpl getUserDetailsImpl() {
        return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
