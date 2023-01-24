//package mg.imwa.config.securityConfig.authenticationProvider;
//
//import mg.imwa.config.securityConfig.authentications.ApiKeyAuthentication;
//import mg.imwa.config.securityConfig.authentications.CustomAuthentication;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.stereotype.Component;
//
//
//@Component
//public class CustomKeyAuthenticationProvider implements AuthenticationProvider {
//    @Value("${key}")
//    private String key;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        ApiKeyAuthentication ca = (ApiKeyAuthentication) authentication;
//        String headerKey = ca.getKey();
//        if (key.equals(headerKey)) return new ApiKeyAuthentication(true,key);
//        throw new BadCredentialsException(" Wrong key ");
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return ApiKeyAuthentication.class.equals(authentication);
//    }
//}
//