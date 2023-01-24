package mg.imwa.config.securityConfig.authenticationManager;
import lombok.AllArgsConstructor;
import mg.imwa.config.securityConfig.authenticationProvider.CustomAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {
    private final CustomAuthenticationProvider provider;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (provider.supports(authentication.getClass()))
        return provider.authenticate(authentication);
        throw new BadCredentialsException(" Authentication provider not supported ");
    }

}
