package mg.imwa.config.securityConfig.authentications;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;
@Data
@AllArgsConstructor
public class CustomAuthentication implements Authentication {
    private String key;
    private boolean authenticated;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
    @Override
    public Object getCredentials() {
        return null;
    }
    @Override
    public Object getDetails() {
        return null;
    }
    @Override
    public Object getPrincipal() {
        return null;
    }
    @Override
    public boolean isAuthenticated(){
        return authenticated;
    }
    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }
    @Override
    public String getName() {
        return null;
    }
}
