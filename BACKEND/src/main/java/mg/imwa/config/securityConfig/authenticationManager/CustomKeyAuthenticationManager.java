//package mg.imwa.config.securityConfig.authenticationManager;
//
//import lombok.AllArgsConstructor;
//import mg.imwa.config.securityConfig.authenticationProvider.CustomAuthenticationProvider;
//import mg.imwa.config.securityConfig.authenticationProvider.CustomKeyAuthenticationProvider;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//
//@AllArgsConstructor
//public class CustomKeyAuthenticationManager implements AuthenticationManager {
//    private final CustomKeyAuthenticationProvider provider;
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        if (provider.supports(authentication.getClass()))
//            return provider.authenticate(authentication);
//        throw new BadCredentialsException(" Authentication provider not supported ");
//    }
//}
//