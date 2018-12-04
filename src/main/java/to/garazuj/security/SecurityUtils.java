package to.garazuj.security;

import org.springframework.security.core.context.SecurityContextHolder;
import to.garazuj.model.User;
import to.garazuj.security.services.UserPrinciple;

public final class SecurityUtils {

    public static User getCurrentUser() {
        return ((UserPrinciple) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal()).getUser();
    }

}
