package mg.imwa.config.securityConfig;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationFilter customAuthenticationFilter;

    @Bean
    public   SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

      return http.addFilterAt(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
              .authorizeHttpRequests().anyRequest().authenticated()
              .and().build();

    }
    @Bean public PasswordEncoder passwordEncoder(){
       return NoOpPasswordEncoder.getInstance();
    }
}
