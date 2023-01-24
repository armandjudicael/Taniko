package mg.imwa.config.securityConfig.filters;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.imwa.config.securityConfig.authenticationManager.CustomAuthenticationManager;
import mg.imwa.config.securityConfig.authentications.CustomAuthentication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@AllArgsConstructor
public class CustomAuthenticationFilter extends OncePerRequestFilter {
    private final CustomAuthenticationManager customAuthenticationManager;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String key = request.getHeader("key");
        CustomAuthentication ca = new CustomAuthentication(key,false);
        Authentication a = customAuthenticationManager.authenticate(ca);
        if (a.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(a);
            filterChain.doFilter(request,response);
        }else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
