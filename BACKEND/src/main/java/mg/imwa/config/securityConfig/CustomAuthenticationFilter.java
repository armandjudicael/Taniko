package mg.imwa.config.securityConfig;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;
@Slf4j
@Component
@AllArgsConstructor
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    private final CustomAuthenticationManager customAuthenticationManager;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 1 - Create an authentication object wich is not yet authenticated
        // 2 - Delegate the authentication object to the manager
        // 3 - Get back the authentication for the manager
        // 4 - if the object is authenticated then send th request to the next filter in the chain

        String key = request.getHeader("key");
        CustomAuthentication ca = new CustomAuthentication(key,false);

        Authentication a = customAuthenticationManager.authenticate(ca);

        if (a.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(a);
            filterChain.doFilter(request,response);
        }
    }
}
