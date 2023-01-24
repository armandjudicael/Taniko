package mg.imwa.config.securityConfig.authenticationProvider;
import mg.imwa.config.securityConfig.authentications.CustomAuthentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{

    @Value("${key}")
    private String key;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
      CustomAuthentication ca = (CustomAuthentication) authentication;
      String headerKey = ca.getKey();
      if (key.equals(headerKey)) return new CustomAuthentication(null,true);
      throw new BadCredentialsException(" Wrong key ");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthentication.class.equals(authentication);
    }
}
