package mg.imwa.config.securityConfig;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;
@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
    // private final AuthenticationManager authenticationManager;
    //  public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
    //    this.authenticationManager = authenticationManager;
    //}
    // @Override
    //  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    //   String username = request.getParameter("username");
    //    String password = request.getParameter("password");
    //    log.info(" Username = "+username+" , password =   ",password);
    //   UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);
    //    return authenticationManager.authenticate(authenticationToken);
    //}
    //  @Override
    //    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

    //      User admin = (User)authResult.getPrincipal();
    //    Algorithm algorithm = Algorithm.HMAC256("Aj!30071999".getBytes());

            //    String accessToken = JWT.create()
            //  .withSubject(admin.getUsername())
            //    .withExpiresAt(new Date(System.currentTimeMillis()*10*60*1000))
            //    .withIssuer(request.getRequestURI().toString())
            //     .withClaim("roles",admin.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
    //   .sign(algorithm);

            //    String refreshToken = JWT.create()
            //    .withSubject(admin.getUsername())
            //     .withExpiresAt(new Date(System.currentTimeMillis()*30*60*1000))
            //  .withIssuer(request.getRequestURI().toString())
            //    .sign(algorithm);

            // response.setHeader("access_token",accessToken);
    //response.setHeader("refresh_token",refreshToken);

    // }

    // @Override
  //  protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException{
    //      log.error(" LOGIN FAILED ");
    //     super.unsuccessfulAuthentication(request, response, failed);
    //  }
}
