//package mg.imwa.config.securityConfig.filters;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import lombok.Setter;
//import mg.imwa.config.securityConfig.authenticationManager.CustomKeyAuthenticationManager;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Setter
//public class ApiKeyFilter extends OncePerRequestFilter {
//    private CustomKeyAuthenticationManager customAuthenticationManager;
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        doFilter(request,response,filterChain);
//    }
//}
//