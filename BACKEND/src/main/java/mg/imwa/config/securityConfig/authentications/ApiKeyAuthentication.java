//package mg.imwa.config.securityConfig.authentications;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import java.util.Collection;
//
//@AllArgsConstructor
//@Data
//public class ApiKeyAuthentication  implements Authentication {
//
//    private boolean authenticated;
//    private String key;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//
//    @Override
//    public Object getCredentials() {
//        return null;
//    }
//
//    @Override
//    public Object getDetails() {
//        return null;
//    }
//
//    @Override
//    public Object getPrincipal() {
//        return null;
//    }
//
//    @Override
//    public boolean isAuthenticated() {
//        return authenticated;
//    }
//
//    @Override
//    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
//   this.authenticated = isAuthenticated;
//    }
//
//    @Override
//    public String getName() {
//        return null;
//    }
//}
//