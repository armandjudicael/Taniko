package mg.imwa.config.securityConfig;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@AllArgsConstructor
public class SecurityConfig {
    @Bean
    public   SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      return http.authorizeHttpRequests().anyRequest().authenticated().and().build();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
